package org.seydaliev.applicationprocessingsystem.service.impl;

import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.exception.RoleNotFoundException;
import org.seydaliev.applicationprocessingsystem.exception.UserNotFoundException;
import org.seydaliev.applicationprocessingsystem.model.Role;
import org.seydaliev.applicationprocessingsystem.model.User;
import org.seydaliev.applicationprocessingsystem.repository.RoleRepository;
import org.seydaliev.applicationprocessingsystem.repository.UserRepository;
import org.seydaliev.applicationprocessingsystem.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements org.seydaliev.applicationprocessingsystem.service.UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;



    public Page<UserDto> getUsersforPage(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    public UserDto assignOperatorRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Role operatorRole = roleRepository.findByName("OPERATOR")
                .orElseThrow(() -> new RoleNotFoundException("Role OPERATOR not found"));

        user.getRoles().add(operatorRole);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto createUser(UserDto userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto assignRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        Role role = roleRepository.findByName("оператор")
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

}
