package com.team1.jbugger.Dto;
import com.team1.jbugger.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private int idUser;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String username;
    private String password;
    private boolean status;
    private List<Roles> roles = new ArrayList<>();

    public boolean getStatus() {
        return this.status;
    }

    public void setRoles(List<Roles> adminRoles) {
        this.roles = adminRoles;
    }
}
