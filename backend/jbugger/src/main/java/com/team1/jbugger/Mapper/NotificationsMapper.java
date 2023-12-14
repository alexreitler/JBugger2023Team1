package com.team1.jbugger.Mapper;


import com.team1.jbugger.Dto.NotificationsDto;
import com.team1.jbugger.Entity.Notifications;

public class NotificationsMapper {
    public static NotificationsDto mapToNotificationsDto(Notifications notification)
    {
        return new NotificationsDto(
                notification.getIdNotification(),
                notification.getType(),
                notification.getUrlOrBugId(),
                notification.getMessage()
        );
    }
    public static Notifications mapToNotifications(NotificationsDto notificationsDto)
    {
        return new Notifications(
                notificationsDto.getIdNotification(),
                notificationsDto.getType(),
                notificationsDto.getUrlOrBugId(),
                notificationsDto.getMessage()
        );
    }
}
