package org.seydaliev.applicationprocessingsystem.service.impl;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.exception.ApplicationNotFoundException;
import org.seydaliev.applicationprocessingsystem.model.Application;
import org.seydaliev.applicationprocessingsystem.repository.ApplicationRepository;
import org.seydaliev.applicationprocessingsystem.service.ApplicationService;
import org.seydaliev.applicationprocessingsystem.service.mapper.ApplicationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public ApplicationDto createApplication(ApplicationDto applicationDto) {
        Application application = applicationMapper.toEntity(applicationDto);
        application.setStatus("отправлено");
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public Page<ApplicationDto> getApplication(Pageable pageable, String status) {
        Page<Application> applications = applicationRepository.findAll(pageable);
        if (status != null) {
            applications = applicationRepository.findByStatus(status, pageable);
        }
        return applications.map(applicationMapper::toDto);
    }

    public ApplicationDto getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found"));
        return applicationMapper.toDto(application);
    }

    public ApplicationDto acceptApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(()-> new ApplicationNotFoundException("Application not found"));
        application.setStatus("принято");
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public ApplicationDto rejectApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
        application.setStatus("отклонено");
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public boolean isUserOwner(Long applicationId, Long userId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));
        return application.getUser().getId().equals(userId);
    }

    public Page<ApplicationDto> getApplicationsForOperator(Pageable pageable, String name) {
        Page<Application> applications = applicationRepository.findByStatusAndUser_NameContaining("отправлено", name, pageable);
        return applications.map(applicationMapper::toDto);
    }
}
