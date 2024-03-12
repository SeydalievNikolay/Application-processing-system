package org.seydaliev.applicationprocessingsystem.controller;

import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return "User is saved";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }
    @PutMapping("/users/{id}/assign-operator")
    @PreAuthorize("hasRole('USER')")
    public UserDto assignOperatorRole(@PathVariable Long id) {
        return userService.assignRole(id);
    }
}
