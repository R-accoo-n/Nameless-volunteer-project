package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<User, UUID> {
    List<User> findUsersByIsApprovedIsFalse();
}
