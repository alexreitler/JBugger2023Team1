package com.team1.jbugger.Dto;
import com.team1.jbugger.Enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto
{
    private int idRole;
    private RoleType type;
}
