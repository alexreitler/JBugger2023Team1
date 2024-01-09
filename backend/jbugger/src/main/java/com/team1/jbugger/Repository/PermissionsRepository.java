package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.History;
import com.team1.jbugger.Entity.Permissions;
import com.team1.jbugger.Enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    Optional<Permissions> findByType (PermissionType type);
}
