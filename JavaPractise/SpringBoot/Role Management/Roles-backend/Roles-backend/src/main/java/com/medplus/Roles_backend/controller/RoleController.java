package com.medplus.Roles_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medplus.Roles_backend.domain.RoleLocation;
import com.medplus.Roles_backend.domain.RoleResponse;
import com.medplus.Roles_backend.dto.ApiResponse;
import com.medplus.Roles_backend.service.RoleServiceInterface;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    @Autowired
    private RoleServiceInterface roleService;
    
   


    @GetMapping("/{id}/available-roles")
    public ResponseEntity<ApiResponse<List<String>>> getAvailableRoles(@PathVariable("id") String userId) {
        List<String> roles = roleService.getAvailableRoles(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Available roles fetched successfully", roles));
    }

    @GetMapping("/{id}/assigned-roles")
    public ResponseEntity<ApiResponse<List<String>>> getAssignedActiveRoles(@PathVariable("id") String userId) {
        List<String> roles = roleService.getAssignedActiveRoles(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Assigned active roles fetched successfully", roles));
    }

    @GetMapping("/{id}/active-role-details")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getActiveRoleDetails(@PathVariable("id") String userId) {
        List<RoleResponse> roles = roleService.getActiveRoleDetails(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Active role details fetched successfully", roles));
    }

    @PostMapping("/{id}/assign-roles")
    public ResponseEntity<ApiResponse<Map<String, Object>>> assignRoles(@PathVariable("id") String userId,
                                                                        @Valid @RequestBody List<RoleLocation> request) {
        Map<String, Object> result = roleService.assignRoles(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Roles assigned successfully", result));
    }

    @PostMapping("/{id}/disable-roles-by-location")
    public ResponseEntity<ApiResponse<Map<String, Object>>> disableRolesByLocation(@PathVariable("id") String userId,
                                                                                   @RequestBody List<RoleLocation> payload) {
        Map<String, Object> result = roleService.disableRolesByLocation(userId, payload);
        return ResponseEntity.ok(new ApiResponse<>(true, "Roles disabled successfully", result));
    }
}
