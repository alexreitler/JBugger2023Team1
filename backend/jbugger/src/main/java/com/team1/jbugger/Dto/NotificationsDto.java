package com.team1.jbugger.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDto {
    private int idNotification;
    private String type;
    private String UrlOrBugId;
    private String message;
}
