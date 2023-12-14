package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notifications")
public class Notifications {
    @Id
    @GeneratedValue
    private int idNotification;
    private String type;
    private String UrlOrBugId;
    private String message;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Notification> user_notifications = new ArrayList<>();

    public Notifications(String type, String urlOrBugId, String message) {
        this.type = type;
        UrlOrBugId = urlOrBugId;
        this.message = message;
    }

    public void setUser_notifications(List<User_Notification> user_notifications) {
        this.user_notifications = user_notifications;
    }
}
