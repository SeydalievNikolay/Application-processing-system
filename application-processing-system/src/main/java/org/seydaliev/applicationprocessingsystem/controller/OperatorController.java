package org.seydaliev.applicationprocessingsystem.controller;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    private final ApplicationService applicationService;

    public OperatorController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('OPERATOR')")
    public Page<ApplicationDto> getApplications(Pageable pageable, @RequestParam String name) {
        return applicationService.getApplicationsForOperator(pageable, name);
    }

    @GetMapping("/applications/{id}")
    @PreAuthorize("hasRole('OPERATOR')")
    public ApplicationDto getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/applications/{id}/accept")
    @PreAuthorize("hasRole('OPERATOR')")
    public ApplicationDto acceptApplication(@PathVariable Long id) {
        return applicationService.acceptApplication(id);
    }

    @PutMapping("/applications/{id}/reject")
    @PreAuthorize("hasRole('OPERATOR')")
    public ApplicationDto rejectApplication(@PathVariable Long id) {
        return applicationService.rejectApplication(id);
    }
}
