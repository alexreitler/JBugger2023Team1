package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.User_NotificationDto;
import com.team1.jbugger.Entity.User_Notification;

public class User_NotificationMapper {
    public static User_NotificationDto mapToUser_NotificationDto(User_Notification userNotification)
    {
        return new User_NotificationDto(
                userNotification.getNotification(),
                userNotification.getDate(),
                userNotification.getUser()
        );
    }
    public static User_Notification mapToUser_Notification(User_NotificationDto userNotificationDto)
    {
        return new User_Notification(
                userNotificationDto.getNotification(),
                userNotificationDto.getDate(),
                userNotificationDto.getUser()
        );
    }
}
