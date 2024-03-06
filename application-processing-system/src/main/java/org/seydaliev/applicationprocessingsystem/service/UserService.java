package org.seydaliev.applicationprocessingsystem.service;

import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDTO);
    UserDto assignRole(Long userId);
    List<UserDto> getUsers();
    Page<UserDto> getUsersforPage(Pageable pageable);
    UserDto assignOperatorRole(Long userId);
}
