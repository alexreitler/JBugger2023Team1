package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue
    private int idUser;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String username;
    private String password;
    private String status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Notification> user_notifications = new ArrayList<>();
    @OneToMany(mappedBy = "userCreated", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bugs> bug_created = new ArrayList<>();
    @OneToMany(mappedBy = "userAssigned", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bugs> bug_assigned = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "User_Roles",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole")
    )
    private List<Roles> roles = new ArrayList<>();

    public Users(String firstName, String lastName, String mobileNumber, String email, String username, String password, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public void setNotifications(List<User_Notification> user_notifications) {
        this.user_notifications = user_notifications;
    }

    public void setBug_created(List<Bugs> bug_created) {
        this.bug_created = bug_created;
    }

    public void setBug_assigned(List<Bugs> bug_assigned) {
        this.bug_assigned = bug_assigned;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
