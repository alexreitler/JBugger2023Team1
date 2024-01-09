package com.team1.jbugger.Service;

import com.team1.jbugger.Dto.PermissionsDto;
import com.team1.jbugger.Dto.RolesDto;
import com.team1.jbugger.Enums.PermissionType;
import com.team1.jbugger.Enums.RoleType;
import com.team1.jbugger.Role_Permission_Helper.AddResponse;
import com.team1.jbugger.Role_Permission_Helper.RemoveResponse;
import com.team1.jbugger.Entity.Roles;
import com.team1.jbugger.Entity.Permissions;
import com.team1.jbugger.Mapper.PermissionsMapper;
import com.team1.jbugger.Repository.RolesRepository;
import com.team1.jbugger.Repository.PermissionsRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@Service
public class RolePermissionService {
    private final PermissionsRepository permissionsRepository;
    private final RolesRepository rolesRepository;

    public RolePermissionService(PermissionsRepository permissionsRepository, RolesRepository rolesRepository) {
        this.permissionsRepository = permissionsRepository;
        this.rolesRepository = rolesRepository;
    }

    public AddResponse addPermissionToRole(PermissionType permission, RoleType role) {
        Optional<Permissions> permissionEntity = permissionsRepository.findByType(permission);
        Permissions permissionResult = permissionEntity.orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        Optional<Roles> roleEntity = rolesRepository.findByType(role);
        Roles roleResult = roleEntity.orElseThrow(() -> new EntityNotFoundException("Role not found"));

        roleResult.getPermissions().add(permissionResult);
        rolesRepository.save(roleResult);

        return AddResponse.builder().role(role).permission(permission).build();
    }

    public RemoveResponse removePermissionFromRole(PermissionType permission, RoleType role) {
        Optional<Permissions> permissionEntity = permissionsRepository.findByType(permission);
        Permissions permissionResult = permissionEntity.orElseThrow(() -> new EntityNotFoundException("Permission not found"));

        Optional<Roles> roleEntity = rolesRepository.findByType(role);
        Roles roleResult = roleEntity.orElseThrow(() -> new EntityNotFoundException("Role not found"));

        roleResult.getPermissions().remove(permissionResult);
        rolesRepository.save(roleResult);

        return RemoveResponse.builder().role(role).permission(permission).build();
    }
}
