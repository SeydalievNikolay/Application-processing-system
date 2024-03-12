package org.seydaliev.applicationprocessingsystem.service.mapper;


import jakarta.persistence.EntityNotFoundException;
import org.seydaliev.applicationprocessingsystem.dto.RoleDto;
import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.model.Role;
import org.seydaliev.applicationprocessingsystem.model.User;
import org.seydaliev.applicationprocessingsystem.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(mapRolesToRoleDtoSet(user.getRoles()));
        return userDto;
    }

    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setRoles(mapRoleDtoSetToRoleSet(userDto.getRoles()));
        return user;
    }

    private Set<RoleDto> mapRolesToRoleDtoSet(Set<Role> roles) {
        return roles.stream()
                .map(role -> {
                    RoleDto roleDto = new RoleDto();
                    roleDto.setId(role.getId());
                    roleDto.setName(role.getName());
                    return roleDto;
                })
                .collect(Collectors.toSet());
    }

    public Set<Role> mapRoleDtoSetToRoleSet(Set<RoleDto> roleDtos) {
        return roleDtos.stream()
                .map(roleDto -> roleRepository.findById(roleDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found")))
                .collect(Collectors.toSet());
    }
}
