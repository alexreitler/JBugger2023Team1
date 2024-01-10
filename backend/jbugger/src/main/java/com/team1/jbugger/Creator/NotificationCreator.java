package com.team1.jbugger.Creator;

import com.team1.jbugger.Entity.*;
import com.team1.jbugger.Enums.*;
import com.team1.jbugger.User_call_helper.User_Information;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class NotificationCreator
{
    public Notifications createWelcomeNotification(Users receiver) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.WELCOME_NEW_USER))
                .users(new HashSet<>() {{
                    add(receiver);
                }})
                .UrlOrBugId("")
                .message(String.format("Hello! Your Account Info is: %s.",
                        receiver.UserInformationWithoutPassword()))
                .build();
    }
    public Notifications createUserUpdatedNotification(Users updatedReceiver, Users updaterReceiver, User_Information oldInfo) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.USER_UPDATED))
                .users(new HashSet<>() {{
                    add(updatedReceiver);
                    add(updaterReceiver);
                }})
                .UrlOrBugId("")
                .message(String.format("Old Account Info: %s, New Account Info: %s.",
                        oldInfo.UserInformationWithPassword(),
                        updatedReceiver.UserInformationWithPassword()))
                .build();
    }
    public Notifications createUserDeletedNotification(Collection<Users> receivers, Users deletedUser) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.USER_DELETED))
                .users(new HashSet<>() {{
                    this.addAll(receivers);
                }})
                .UrlOrBugId("")
                .message(String.format("User Deleted. Account Info: %s.",
                        deletedUser.UserInformationWithPassword()))
                .build();
    }
    public Notifications createBugStatusUpdateNotification(Bugs updatedBug) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.BUG_STATUS_UPDATED))
                .users(new HashSet<>() {{
                    add(updatedBug.getUserCreated());
                    add(updatedBug.getUserAssigned());
                }})
                .UrlOrBugId("")
                .message(String.format("Status of the Bug %s has been modified!",
                        updatedBug.getIdBug()))
                .build();
    }
    public Notifications createBugUpdateNotification(Bugs bug, Boolean bugWasAdded) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.BUG_UPDATED))
                .users(new HashSet<>() {{
                    add(bug.getUserCreated());
                    add(bug.getUserAssigned());
                }})
                .UrlOrBugId("")
                .message(String.format("Bug %s was %s!",
                        bug.getIdBug(), bugWasAdded ? "added" : "updated"))
                .build();
    }
    public Notifications createBugClosedNotification(Bugs closedBug) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.BUG_CLOSED))
                .users(new HashSet<>() {{
                    add(closedBug.getUserCreated());
                    add(closedBug.getUserAssigned());
                }})
                .UrlOrBugId("")
                .message(String.format("Bug %s is closed!",
                        closedBug.bugInfo()))
                .build();
    }
    public Notifications createUserDeactivatedNotification(Collection<Users> receivers, Users deactivatedUser) {
        return Notifications.builder()
                .type(String.valueOf(NotificationType.USER_DEACTIVATED))
                .users(new HashSet<>() {{
                    this.addAll(receivers);
                }})
                .UrlOrBugId("")
                .message(String.format("User has been deactivated. Account Info: %s.",
                        deactivatedUser.UserInformationWithPassword()))
                .build();
    }
}
