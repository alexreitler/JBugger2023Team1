package com.team1.jbugger.Service;

import com.team1.jbugger.Dto.PermissionsDto;
import com.team1.jbugger.Entity.Permissions;
import com.team1.jbugger.Enums.PermissionType;
import com.team1.jbugger.Repository.PermissionsRepository;
import com.team1.jbugger.Mapper.PermissionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionsRepository permissionsRepository;

    public void initializePermissionsRepository() {
        for (PermissionType permissionType : PermissionType.values()) {
            Permissions permission = new Permissions(0, permissionType, "");
            permissionsRepository.save(permission);
        }
    }

    public List<PermissionsDto> findAllPermissions() {
        List<Permissions> permissions = permissionsRepository.findAll();
        return permissions.stream()
                .map(PermissionsMapper::mapToPermissionsDto)
                .collect(Collectors.toList());
    }
}


