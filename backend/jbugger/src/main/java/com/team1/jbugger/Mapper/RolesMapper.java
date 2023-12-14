package com.team1.jbugger.Mapper;


import com.team1.jbugger.Dto.RolesDto;
import com.team1.jbugger.Entity.Roles;

public class RolesMapper {
    public static RolesDto mapToRolesDto(Roles role)
    {
        return new RolesDto(
                role.getIdRole(),
                role.getType()
        );
    }
    public static Roles mapToRoles(RolesDto rolesDto)
    {
        return new Roles(
                rolesDto.getIdRole(),
                rolesDto.getType()
        );
    }
}
