package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Permissions")
public class Permissions {
    @Id
    @GeneratedValue
    private int idPermission;
    private String type;
    private String description;
    @ManyToMany(mappedBy = "permissions")
    private List<Roles> roles = new ArrayList<>();

    public Permissions(String type)
    {
        this.type = type;
    }

    public void setRoles(List<Roles> roles)
    {
        this.roles = roles;
    }
}
