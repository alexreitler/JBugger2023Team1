package com.team1.jbugger.Repository;
import com.team1.jbugger.Entity.Roles;
import com.team1.jbugger.Enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByType(RoleType type);
}
