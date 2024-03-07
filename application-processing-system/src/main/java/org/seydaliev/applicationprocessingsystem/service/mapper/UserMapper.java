package org.seydaliev.applicationprocessingsystem.service.mapper;


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
        userDto.setRoles(mapRolesToString(user.getRoles()));
        return userDto;
    }
    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setRoles(mapRolesToRoleSet(userDto.getRoles()));
        return user;
    }

    private Set<String> mapRolesToString(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public Set<Role> mapRolesToRoleSet(Set<String> roles) {
        return roles.stream()
                .flatMap(roleName -> roleRepository.findByName(roleName).stream())
                .collect(Collectors.toSet());
    }
}
