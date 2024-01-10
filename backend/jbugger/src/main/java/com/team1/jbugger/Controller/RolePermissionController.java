package com.team1.jbugger.Controller;

import com.team1.jbugger.Enums.PermissionType;
import com.team1.jbugger.Enums.RoleType;
import com.team1.jbugger.Role_Permission_Helper.AddResponse;
import com.team1.jbugger.Role_Permission_Helper.RemoveResponse;
import com.team1.jbugger.Service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/right")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @PostMapping("/{role}/add/{permission}")
    public ResponseEntity<AddResponse> addPermissionToRole(
            @PathVariable RoleType role,
            @PathVariable PermissionType permission
    ) {
        return ResponseEntity.ok(rolePermissionService.addPermissionToRole(permission, role));
    }

    @DeleteMapping("/{role}/delete/{permission}")
    public ResponseEntity<RemoveResponse> removePermissionFromRole(
            @PathVariable RoleType role,
            @PathVariable PermissionType permission
    ) {
        return ResponseEntity.ok(rolePermissionService.removePermissionFromRole(permission, role));
    }
}

