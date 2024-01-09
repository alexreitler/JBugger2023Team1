package com.team1.jbugger.Mapper;

import com.team1.jbugger.Dto.PermissionsDto;
import com.team1.jbugger.Entity.Permissions;

public class PermissionsMapper {
    public static PermissionsDto mapToPermissionsDto(Permissions permission)
    {
        return new PermissionsDto(
                permission.getIdPermission(),
                permission.getType(),
                permission.getDescription()
        );
    }
    public static Permissions mapToPermissions(PermissionsDto permissionsDto)
    {
        return new Permissions(
                permissionsDto.getIdPermission(),
                permissionsDto.getType(),
                permissionsDto.getDescription()
        );
    }
}
