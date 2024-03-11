package org.seydaliev.applicationprocessingsystem.service.mapper;

import org.seydaliev.applicationprocessingsystem.dto.RoleDto;
import org.seydaliev.applicationprocessingsystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
