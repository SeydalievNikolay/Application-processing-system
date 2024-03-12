package org.seydaliev.applicationprocessingsystem.service;

import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDTO);
    UserDto assignRole(Long userId);
    List<UserDto> getUsers();
    Page<UserDto> getUsersforPage(Pageable pageable);
    UserDto assignOperatorRole(Long userId);
}
