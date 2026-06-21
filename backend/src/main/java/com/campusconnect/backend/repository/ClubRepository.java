package com.campusconnect.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Club;

public interface ClubRepository
        extends JpaRepository<Club, Long> {

<<<<<<< HEAD
}
=======
    Optional<Club> findByAdminEmail(String adminEmail);

}
>>>>>>> abe41b61bddfffc221a8611442cf51a94731261e
