package org.seydaliev.applicationprocessingsystem.controller;

import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }
    @PutMapping("/users/{id}/assign-operator")
    public UserDto assignOperatorRole(@PathVariable Long id) {
        return userService.assignRole(id);
    }
}
