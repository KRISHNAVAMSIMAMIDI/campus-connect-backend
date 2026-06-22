package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.campusconnect.backend.entity.Application;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {
                 @Query(value = """
            SELECT a.*
            FROM applications a
            JOIN recruitments r ON r.id = a.recruitment_id
            WHERE r.club_id = :clubId
            ORDER BY a.id DESC
            """, nativeQuery = true)
    List<Application> findByClubId(@Param("clubId") Long clubId);
}
