package com.team1.jbugger.Service;

import com.team1.jbugger.Events.*;
import com.team1.jbugger.Entity.*;
import com.team1.jbugger.Enums.*;
import com.team1.jbugger.Repository.*;
import com.team1.jbugger.Creator.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    private final UsersRepository userRepository;
    @Autowired
    private final RolesRepository roleRepository;
    @Autowired
    private final PermissionsRepository permissionRepository;
    @Autowired
    private final NotificationsRepository notificationRepository;
    @Autowired
    private final NotificationCreator notificationCreator;
    @Autowired
    private final ApplicationEventPublisher eventPublisher;
    @EventListener

    public void handleUserLoginEvent(Login event) {
        Notifications notification = notificationCreator.createWelcomeNotification(event.getLoggedUser());
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleUserUpdatedEvent(UserUpdate event) {
        Notifications notification = notificationCreator.createUserUpdatedNotification(event.getUser_updated(), event.getUser_new(), event.getPrevious_info());
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleUserDeleteEvent(UserDeleted event) {
        Notifications notification = notificationCreator.createUserDeletedNotification(usersWithPermission(PermissionType.USER_MANAGEMENT), event.getDeletedUser());
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleUserDeactivatedEvent(UserDeactivated event) {
        Notifications notification = notificationCreator.createUserDeactivatedNotification(usersWithRole(RoleType.ADM), event.getDeactivatedUser());
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleBugStatusUpdateEvent(BugStatusUpdate event) {
        Notifications notification = notificationCreator.createBugStatusUpdateNotification(event.getUpdatedBug());
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleBugAddedEvent(AddBug event) {
        Notifications notification = notificationCreator.createBugUpdateNotification(event.getAddedBug(), Boolean.TRUE);
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleBugUpdateEvent(BugUpdate event) {
        Notifications notification = notificationCreator.createBugUpdateNotification(event.getUpdatedBug(), Boolean.FALSE);
        notificationRepository.save(notification);
    }
    @EventListener
    public void handleBugCloseEvent(BugFinished event) {
        Notifications notification = notificationCreator.createBugClosedNotification(event.getClosedBug());
        notificationRepository.save(notification);
    }
    public List<Roles> rolesWithPermission(PermissionType permissionType) {
        Permissions permission = permissionRepository.findByType(permissionType).orElseThrow();
        List<Roles> roles = new ArrayList<>();

        for(Roles role : roleRepository.findAll())
            if(role.getPermissions().contains(permission))
                roles.add(role);

        return roles;
    }
    public List<Users> usersWithPermission(PermissionType permissionType) {
        List<Roles> rolesWithPermission = rolesWithPermission(permissionType);
        List<Users> usersWithPermission = new ArrayList<>();

        for(Users user : userRepository.findAll())
            for(Roles role : rolesWithPermission)
                if(user.getRoles().contains(role))
                    usersWithPermission.add(user);

        return usersWithPermission;
    }
    public List<Users> usersWithRole(RoleType roleType) {
        Roles role = roleRepository.findByType(roleType).orElseThrow();
        List<Users> usersWithRole = new ArrayList<>();

        for(Users user : userRepository.findAll())
            if (user.getRoles().contains(role))
                usersWithRole.add(user);

        return usersWithRole;
    }
//    @Scheduled(cron = "0 0 0 * * *")
//    public void deleteOldNotifications() {
//        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
//        List<Notifications> notificationsToDelete = notificationRepository.findByCreatedTimeBefore(thirtyDaysAgo);
//        notificationRepository.deleteAll(notificationsToDelete);
//    }
}
