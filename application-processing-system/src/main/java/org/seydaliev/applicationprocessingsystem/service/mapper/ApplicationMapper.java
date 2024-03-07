package org.seydaliev.applicationprocessingsystem.service.mapper;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.model.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationDto toDto(Application application) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(application.getId());
        applicationDto.setStatus(application.getStatus());
        applicationDto.setText(application.getText());
        applicationDto.setPhoneNumber(application.getPhoneNumber());
        applicationDto.setName(application.getName());
        applicationDto.setCreatedAt(application.getCreatedAt());
        return applicationDto;
    }

    public Application toEntity(ApplicationDto applicationDto) {
        Application application = new Application();
        application.setId(applicationDto.getId());
        application.setStatus(applicationDto.getStatus());
        application.setText(applicationDto.getText());
        application.setPhoneNumber(applicationDto.getPhoneNumber());
        application.setName(applicationDto.getName());
        application.setCreatedAt(applicationDto.getCreatedAt());
        return application;
    }
}
