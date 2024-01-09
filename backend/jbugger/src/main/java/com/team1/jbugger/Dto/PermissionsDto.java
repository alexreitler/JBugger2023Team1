package com.team1.jbugger.Dto;
import com.team1.jbugger.Enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionsDto {
    private int idPermission;
    private PermissionType type;
    private String description;
}
