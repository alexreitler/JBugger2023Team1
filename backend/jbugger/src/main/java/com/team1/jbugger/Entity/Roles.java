package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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


    public Roles(int idRole, String type) {
        this.idRole = idRole;
        this.type = type;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
