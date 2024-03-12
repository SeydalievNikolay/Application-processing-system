package org.seydaliev.applicationprocessingsystem.controller;

import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ApplicationDto createApplication(@RequestBody ApplicationDto applicationDto) {
        return applicationService.createApplication(applicationDto);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Page<ApplicationDto> getApplications(Pageable pageable, @RequestParam (required = false) String status) {
        return applicationService.getApplication(pageable,status);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') and @applicationService.isUserOwner(#id, authentication.principal.id)")
    public ApplicationDto getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}/accept")
    @PreAuthorize("hasRole('OPERATOR')")
    public ApplicationDto acceptApplication(@PathVariable Long id) {
        return applicationService.acceptApplication(id);
    }
    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('OPERATOR')")
    public ApplicationDto rejectApplication(@PathVariable Long id) {
        return applicationService.rejectApplication(id);
    }
}
