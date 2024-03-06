package org.seydaliev.applicationprocessingsystem.service.mapper;

import org.mapstruct.Mapper;
import org.seydaliev.applicationprocessingsystem.dto.RoleDto;
import org.seydaliev.applicationprocessingsystem.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
