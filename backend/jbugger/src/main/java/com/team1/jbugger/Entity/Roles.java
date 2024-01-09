package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.*;
import com.team1.jbugger.Enums.RoleType;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRole;

    private RoleType type;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Roles_Permissions",
            joinColumns = @JoinColumn(name = "idRole"),
            inverseJoinColumns = @JoinColumn(name = "idPermission")
    )
    private List<Permissions> permissions = new ArrayList<>();


    public Roles(int idRole, RoleType type) {
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
