package com.team1.jbugger.Dto;
import com.team1.jbugger.Entity.Notifications;
import com.team1.jbugger.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User_NotificationDto {
    private Notifications notification;
    private LocalDate date;
    private Users user;
}
