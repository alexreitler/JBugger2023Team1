package com.team1.jbugger.Service;

import com.team1.jbugger.Entity.Roles;
import com.team1.jbugger.Entity.Users;
import com.team1.jbugger.Repository.UsersRepository;
import com.team1.jbugger.Repository.RolesRepository;
import com.team1.jbugger.Events.UserUpdate;
import com.team1.jbugger.Enums.RoleType;
import com.team1.jbugger.Events.UserDeactivated;
import com.team1.jbugger.Mapper.UsersMapper;
import com.team1.jbugger.User_call_helper.Encapsulate_Request;
import com.team1.jbugger.User_call_helper.Deactivate;
import com.team1.jbugger.User_call_helper.Update_Response;
import com.team1.jbugger.User_call_helper.User_Information;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private final RolesRepository roleRepository;
    @Autowired
    private final ApplicationEventPublisher eventPublisher;

    public Update_Response update(String username, Encapsulate_Request request, String updaterName) throws Exception {

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User with username " + username + " not found"));

        Users updater = userRepository.findByUsername(updaterName)
                .orElseThrow(() -> new Exception("User with username " + updaterName + " not found"));


        User_Information oldUserInfo = User_Information.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled_or_not(user.getStatus())
                .roles(user.getRoles())
                .build();

        user.setMobileNumber(request.getMobileNumber());
        user.setEmail(request.getEmail());
        user.setRoles(generateRoles(request.getRoles()));
        userRepository.save(user);

        eventPublisher.publishEvent(UserUpdate.builder()
                .user_updated(user)
                .user_new(updater)
                .previous_info(oldUserInfo)
                .build());

        return Update_Response.builder().user(user).build();
    }

    public Deactivate deactivate(String username) throws Exception{
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User with username " + username + " not found"));

        Deactivate dr = new Deactivate();

        if (user.getStatus()) {
            user.setStatus(false);
            userRepository.save(user);
            dr.setResponse("User with username " + username + " has been deactivated");

            eventPublisher.publishEvent(UserDeactivated.builder()
                    .deactivatedUser(user)
                    .build());
        } else {
            dr.setResponse("User with username " + username + " is already deactivated");
        }

        return dr;
    }

    public List<Roles> generateRoles(List<String> rolesData) {
        List<Roles> rolesList = new ArrayList<>();
        for (String role : rolesData) {
            rolesList.add(
                    roleRepository.findByType(RoleType.valueOf(role))
                            .orElseThrow(() -> new RuntimeException(role + " role not found"))
            );
        }
        return rolesList;
    }

}

