package com.team1.jbugger.User_call_helper;
import com.team1.jbugger.Entity.Roles;
import lombok.Builder;

import java.util.List;

@Builder
public class User_Information {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String username;
    private String password;
    private boolean enabled_or_not;
    private List<Roles> roles;


    public String User_Information_Format() {
        return String.format("Name = '%s %s', Email = '%s', Username = '%s', Password = '%s', Phone Number = '%s', Roles = '%s'", firstName, lastName, email, username, password, mobileNumber, roles.toString());
    }
}
