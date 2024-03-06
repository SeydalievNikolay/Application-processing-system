package org.seydaliev.applicationprocessingsystem.service;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    ApplicationDto createApplication(ApplicationDto applicationDto);
    Page<ApplicationDto> getApplication(Pageable pageable, String status);
    ApplicationDto getApplicationById(Long id);
    ApplicationDto acceptApplication(Long id);
    ApplicationDto rejectApplication(Long id);
    boolean isUserOwner(Long applicationId, Long userId);
    Page<ApplicationDto> getApplicationsForOperator(Pageable pageable, String name);
}
