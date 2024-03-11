package org.seydaliev.applicationprocessingsystem.service.impl;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.model.Application;
import org.seydaliev.applicationprocessingsystem.repository.ApplicationRepository;
import org.seydaliev.applicationprocessingsystem.service.AdminService;
import org.seydaliev.applicationprocessingsystem.service.mapper.ApplicationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public AdminServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public Page<ApplicationDto> getApplicationsByStatus(Pageable pageable, String status) {
        Page<Application> applications = applicationRepository.findByStatus(status, pageable);
        return applications.map(applicationMapper::toDto);
    }

}
