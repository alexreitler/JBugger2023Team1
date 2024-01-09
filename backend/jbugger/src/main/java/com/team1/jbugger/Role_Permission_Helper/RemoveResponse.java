package com.team1.jbugger.Role_Permission_Helper;

import com.team1.jbugger.Enums.PermissionType;
import com.team1.jbugger.Enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveResponse {
    RoleType role;
    PermissionType permission;
}