package com.team1.jbugger.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.team1.jbugger.Authentication.*;
import com.team1.jbugger.JwtTokens.JwtService;
import com.team1.jbugger.Entity.*;
import com.team1.jbugger.Enums.*;
import com.team1.jbugger.Repository.*;
import com.team1.jbugger.Events.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (checkPhoneNumberFormat(request.getMobileNumber()) && checkEmailFormat(request.getEmail())) {
            String generatedPass = generatePassword();
            String encodedPass = passwordEncoder.encode(generatedPass);
            var user = Users.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .mobileNumber(request.getMobileNumber())
                    .email(request.getEmail())
                    .username(generateUsername(request.getFirstName(), request.getLastName()))
                    .password(encodedPass)
                    .roles(generateRoles(request.getRoles()))
                    .status(true)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken((UserDetails) user);
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        } else throw new RuntimeException("Invalid Fields");
    }

    public List<Roles> generateRoles(List<String> rolesData) {
        List<Roles> roles = new ArrayList<>();
        for (String role : rolesData) {
            roles.add(
                    roleRepository.findByType(RoleType.valueOf(role))
                            .orElseThrow(() -> new RuntimeException(role + " role not found"))
            );
        }
        return roles;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken((UserDetails) user);
        eventPublisher.publishEvent(Login.builder()
                .loggedUser(user)
                .build());
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    private String generateUsername(String firstName, String lastName) {
        StringBuilder username = new StringBuilder();
        if (lastName.length() > 4) {
            for (int i = 0; i < 5; i++) {
                username.append(lastName.charAt(i));
            }
            username.append(firstName.charAt(0));
        } else {
            for (int i = 0; i < lastName.length(); i++) {
                username.append(lastName.charAt(i));
            }
            for (int i = 0; i < 6 - lastName.length(); i++) {
                username.append(firstName.charAt(i));
            }
        }
        String usr = username.toString();
        usr = usr.toLowerCase();
        int k = 1;
        while (!userRepository.findByUsername(usr).isEmpty()) {
            usr = usr.substring(0, 6) + k;
            k++;
        }
        return usr;
    }

    private String generatePassword() {
        List<String> characters = Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "!", "@", "#", "$", "%", "&", "*", "?", "!",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0");

        StringBuilder pass = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 31; i++)
        {
            String randomElement = characters.get(rand.nextInt(characters.size()));
            pass.append(randomElement);
        }
        System.out.println(pass);
        return pass.toString();
    }

    private Boolean checkPhoneNumberFormat(String number)
    {
        if (number.isEmpty()) return false;
        int indexSize = 0;

        if (number.startsWith("+49") || number.startsWith("+40"))
            indexSize = 3;

        else if (number.startsWith("0049") || number.startsWith("0040"))
            indexSize = 4;

        if (indexSize > 0)
        {
            if (number.subSequence(indexSize, number.length()).length() <= 11)
                return true;

            else
                return false;
        }
        else return false;
    }

    private Boolean checkEmailFormat(String email) {
        return email.endsWith("@msggroup.com");
    }
}
