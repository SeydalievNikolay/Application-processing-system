package org.seydaliev.applicationprocessingsystem.repository;

import org.seydaliev.applicationprocessingsystem.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
