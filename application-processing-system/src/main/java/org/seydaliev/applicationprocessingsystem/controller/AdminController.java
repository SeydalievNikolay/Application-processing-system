package org.seydaliev.applicationprocessingsystem.controller;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.service.AdminService;
import org.seydaliev.applicationprocessingsystem.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }


    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUsers(Pageable pageable) {
        return userService.getUsersforPage(pageable);
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ApplicationDto> getApplications(Pageable pageable, @RequestParam String status) {
        return adminService.getApplicationsByStatus(pageable, status);
    }

    @PutMapping("/users/{userId}/roles/operator")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto assignOperatorRole(@PathVariable Long userId) {
        return userService.assignOperatorRole(userId);
    }
}
