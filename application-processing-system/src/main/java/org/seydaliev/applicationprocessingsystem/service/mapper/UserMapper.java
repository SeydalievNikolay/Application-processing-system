package org.seydaliev.applicationprocessingsystem.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.model.Role;
import org.seydaliev.applicationprocessingsystem.model.User;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToString")
    UserDto toDto (User user);
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToString")
    User toEntity(UserDto userDto);

    @Named("mapRolesToString")
    static Set<String> mapRolesToString(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    @Named("mapRolesToRoleSet")
    static Set<Role> mapRolesToRoleSet(Set<String> roles) {
        return roles.stream()
                .map(String::)
                .collect(Collectors.toSet());
    }
}
