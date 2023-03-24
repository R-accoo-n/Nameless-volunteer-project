package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.Donations;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationsRepository extends JpaRepository<Donations, UUID> {
    List<Donations> findAllByUserID(UUID userID);
}
