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
@Table(name = "Notification")
public class Notifications {
    @Id
    @GeneratedValue
    private int idNotification;
    private String type;
    private String UrlOrBugId;
    private String message;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Notification> user_notifications = new ArrayList<>();

    public Notifications(int idNotification, String type, String urlOrBugId, String message) {
        this.idNotification = idNotification;
        this.type = type;
        UrlOrBugId = urlOrBugId;
        this.message = message;
    }

    public void setUser_notifications(List<User_Notification> user_notifications) {
        this.user_notifications = user_notifications;
    }
}
