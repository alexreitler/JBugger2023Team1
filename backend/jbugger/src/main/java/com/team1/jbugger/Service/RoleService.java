package com.team1.jbugger.Service;



import com.team1.jbugger.Dto.PermissionsDto;
import com.team1.jbugger.Dto.RolesDto;
import com.team1.jbugger.Entity.Permissions;
import com.team1.jbugger.Entity.Roles;
import com.team1.jbugger.Enums.PermissionType;
import com.team1.jbugger.Enums.RoleType;
import com.team1.jbugger.Mapper.PermissionsMapper;
import com.team1.jbugger.Mapper.RolesMapper;
import com.team1.jbugger.Repository.PermissionsRepository;
import com.team1.jbugger.Repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RolesRepository rolesRepository;
    private final PermissionsRepository permissionRepository;

    public List<RolesDto> findAllRoles() {
        List<Roles> roles = rolesRepository.findAll();
        return roles.stream()
                .map(RolesMapper::mapToRolesDto)
                .collect(Collectors.toList());
    }

    public void initializeRolesRepository() {
        for (RoleType roleType : RoleType.values()) {
            var role = Roles.builder()
                    .type(roleType)
                    .permissions(generatePermissions(roleType))
                    .build();
            rolesRepository.save(role);
        }
    }

    private List<Permissions> generatePermissions(RoleType roleType) {
        switch (roleType) {
            case ADM:
                return generateAdminPermissions();
            case PM:
                return generateProjectManagerPermissions();
            case TM:
                return generateTestManagerPermissions();
            case DEV:
                return generateDeveloperPermissions();
            case TEST:
                return generateTesterPermissions();
        }
        return new ArrayList<>();
    }

    private List<Permissions> generateAdminPermissions() {
        return generatePermissionsList(
                PermissionType.USER_MANAGEMENT,
                PermissionType.BUG_MANAGEMENT,
                PermissionType.BUG_CLOSE,
                PermissionType.PERMISSION_MANAGEMENT
        );
    }

    private List<Permissions> generateProjectManagerPermissions() {
        return generatePermissionsList(PermissionType.USER_MANAGEMENT);
    }

    private List<Permissions> generateTestManagerPermissions() {
        return generatePermissionsList(PermissionType.BUG_MANAGEMENT, PermissionType.BUG_CLOSE);
    }

    private List<Permissions> generateDeveloperPermissions() {
        return generatePermissionsList(PermissionType.BUG_MANAGEMENT);
    }

    private List<Permissions> generateTesterPermissions() {
        return generatePermissionsList(PermissionType.BUG_CLOSE);
    }

    private List<Permissions> generatePermissionsList(PermissionType... permissionTypes) {
        return Set.of(permissionTypes)
                .stream()
                .map(permissionType -> permissionRepository.findByType(permissionType)
                        .orElseThrow(() -> new RuntimeException(permissionType.name() + " Permission not found")))
                .collect(Collectors.toList());
    }
}

