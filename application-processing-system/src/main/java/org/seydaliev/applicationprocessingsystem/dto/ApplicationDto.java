package org.seydaliev.applicationprocessingsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationDto {
    private Long id;
    private String status;
    private String text;
    private String phoneNumber;
    private String name;
    private LocalDateTime createdAt;
}
