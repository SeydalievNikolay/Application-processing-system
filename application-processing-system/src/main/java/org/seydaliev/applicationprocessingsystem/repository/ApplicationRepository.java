package org.seydaliev.applicationprocessingsystem.repository;

import org.seydaliev.applicationprocessingsystem.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a WHERE a.status = :status")
    Page<Application> findByStatus(@Param("status") String status, Pageable pageable);

    @Query("SELECT a FROM Application a WHERE a.status = :status AND a.user.name LIKE %:name%")
    Page<Application> findByStatusAndUser_NameContaining(@Param("status") String status, @Param("name") String name, Pageable pageable);



}
