package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


/**
 * Repository for Fundraising class
 */

@Repository
public interface FundraisingRepository extends JpaRepository<Fundraising, UUID> {
    List<Fundraising> findBySocialTypeAndIsActive(FundraisingType type, boolean isActive);
    List<Fundraising>findAllByIsActiveTrue();
    List<Fundraising> findByUserIdAndIsActiveFalse(UUID userId);
    List<Fundraising> findByIsActive(boolean isActive);
    Fundraising findFundraisingById(UUID fundraisingId);
}
