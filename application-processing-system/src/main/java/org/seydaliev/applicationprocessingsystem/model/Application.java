package org.seydaliev.applicationprocessingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String text;
    private String phoneNumber;
    private String name;
    @ManyToOne
    private User user;
    private LocalDateTime createdAt;
}
