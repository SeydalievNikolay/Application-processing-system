package org.seydaliev.applicationprocessingsystem.service.mapper;

import org.mapstruct.Mapper;
import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.model.Application;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDto toDto(Application application);
    Application toEntity(ApplicationDto applicationDto);
}
