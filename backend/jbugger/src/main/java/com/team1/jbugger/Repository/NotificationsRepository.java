package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    //List<Notifications> findByCreatedTimeBefore(LocalDateTime date);
}
