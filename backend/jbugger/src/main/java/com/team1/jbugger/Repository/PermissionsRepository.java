package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.History;
import com.team1.jbugger.Entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
}
