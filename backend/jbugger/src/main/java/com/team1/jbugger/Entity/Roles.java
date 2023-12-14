package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue
    private int idRole;
    private String type;
    @ManyToMany(mappedBy = "roles")
    private List<Users> users = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "Roles_Permissions",
            joinColumns = @JoinColumn(name = "idRole"),
            inverseJoinColumns = @JoinColumn(name = "idPermission")
    )
    private List<Permissions> permissions = new ArrayList<>();


    public Roles(String type) {
        this.type = type;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
