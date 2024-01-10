package com.team1.jbugger.Controller;

import com.team1.jbugger.Authentication.AuthenticationResponse;
import com.team1.jbugger.Authentication.RegisterRequest;
import com.team1.jbugger.Entity.Users;
import com.team1.jbugger.Repository.UsersRepository;
import com.team1.jbugger.Service.AuthenticationService;
import com.team1.jbugger.Service.UsersService;
import com.team1.jbugger.User_call_helper.Deactivate;
import com.team1.jbugger.User_call_helper.Encapsulate_Request;
import com.team1.jbugger.User_call_helper.Update_Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private final UsersRepository userRepository;

    private final UsersService userService;
    private final AuthenticationService authService;

    @PostMapping("/add")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Update_Response> update(@PathVariable String username, @RequestBody Encapsulate_Request request) {
        try {
            return ResponseEntity.ok(userService.update(username, request, SecurityContextHolder.getContext().getAuthentication().getName()));
        } catch (Exception e) {
            // Handle the exception as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deactivate/{username}")
    public ResponseEntity<Deactivate> deactivate(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.deactivate(username));
        } catch (Exception e) {
            // Handle the exception as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> userList = new ArrayList<>(userRepository.findAll());

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            // Handle the exception as needed
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        Optional<Users> userData = userRepository.findByUsername(username);
        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/updateUserById/{id}")
    public ResponseEntity<Users> updateUserById(@PathVariable Long id, @RequestBody Users newUserData) {
        Optional<Users> oldUserData = userRepository.findById(id);

        if (oldUserData.isPresent()) {
            Users updatedUserData = oldUserData.get();
            updatedUserData.setFirstName(newUserData.getFirstName());
            updatedUserData.setLastName(newUserData.getLastName());
            updatedUserData.setMobileNumber(newUserData.getMobileNumber());
            updatedUserData.setEmail(newUserData.getEmail());
            updatedUserData.setUsername(newUserData.getUsername());

            Users userObj = userRepository.save(updatedUserData);
            return new ResponseEntity<>(userObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

