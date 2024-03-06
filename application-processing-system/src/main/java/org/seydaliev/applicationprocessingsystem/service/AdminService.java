package org.seydaliev.applicationprocessingsystem.service;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Page<ApplicationDto> getApplicationsByStatus(Pageable pageable, String status);
}
