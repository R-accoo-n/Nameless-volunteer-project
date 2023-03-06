package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User class
 */

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    List<User> findByRole(UserRole role);
//
    Optional<User> findByEmail(String email);
//
//    Optional<User> findById(UUID id);
}
