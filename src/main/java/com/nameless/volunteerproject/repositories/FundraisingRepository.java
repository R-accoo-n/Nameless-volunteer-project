package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.Fundraising;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Fundraising class
 */

@Repository
public interface FundraisingRepository extends JpaRepository<Fundraising, UUID> {

}
