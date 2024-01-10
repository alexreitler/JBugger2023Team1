package com.team1.jbugger.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notification")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idNotification;
    private String type;
    private String UrlOrBugId;
    private String message;

    @ManyToMany(
            targetEntity = Users.class,
            mappedBy = "notifications"
    )
    private Set<Users> users = new HashSet<>();

    public Notifications(int idNotification, String type, String urlOrBugId, String message) {
        this.idNotification = idNotification;
        this.type = type;
        UrlOrBugId = urlOrBugId;
        this.message = message;
    }

}
