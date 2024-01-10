package com.team1.jbugger.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String username;
    private String password;
    private boolean status;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<User_Notification> user_notifications = new ArrayList<>();

    @OneToMany(mappedBy = "userCreated", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bugs> bug_created = new ArrayList<>();

    @OneToMany(mappedBy = "userAssigned", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bugs> bug_assigned = new ArrayList<>();

    @OneToMany(mappedBy = "user", targetEntity = Comments.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "User_Roles",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole")
    )
    private List<Roles> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_notification",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "notificationId")
    )
    private Set<Notifications> notifications = new HashSet<>();

    public Users(int idUser, String firstName, String lastName, String mobileNumber, String email, String username, String password, boolean status) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
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

    public boolean getStatus() {
        return this.status;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getType().toString()));
        }
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String UserInformationWithoutPassword() {
        return String.format("User Information: FirstName = '%s, LastName = %s', Email = '%s', Username = '%s', Telephone Number = '%s', Roles = '%s'",
                firstName, lastName, email, username, mobileNumber, roles.toString());
    }
    public String UserInformationWithPassword() {
        return String.format("User Information: FirstName = '%s, LastName = %s', Email = '%s', Username = '%s', Password = '%s', Telephone Number = '%s', Roles = '%s'",
                firstName, lastName, email, username, password, mobileNumber, roles.toString());
    }
}
