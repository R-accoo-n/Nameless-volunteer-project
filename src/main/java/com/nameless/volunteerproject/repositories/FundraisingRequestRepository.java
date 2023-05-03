package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.FundraisingRequest;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for FundraisingRequest class
 */

@Repository
public interface FundraisingRequestRepository extends JpaRepository<FundraisingRequest, UUID> {
    FundraisingRequest findFundraisingRequestById(UUID uuid);
    List<FundraisingRequest>findByMilitaryId(UUID militaryId);
    List<FundraisingRequest>findFundraisingRequestsByIsSatisfiedIsFalse();
    List<FundraisingRequest>findFundraisingRequestsByUserId(UUID userId);
}
