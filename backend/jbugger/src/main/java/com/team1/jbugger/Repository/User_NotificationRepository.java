package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.User_Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_NotificationRepository extends JpaRepository<User_Notification, Long> {
}
