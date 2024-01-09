package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.team1.jbugger.Enums.PermissionType;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Permissions")
public class Permissions {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPermission;
    @Getter
    private PermissionType type;
    @Getter
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<Roles> roles = new ArrayList<>();

    public Permissions(int idPermission, PermissionType type, String description)
    {
        this.idPermission = idPermission;
        this.type = type;
        this.description = description;
    }

    public void setRoles(List<Roles> roles)
    {
        this.roles = roles;
    }

}
