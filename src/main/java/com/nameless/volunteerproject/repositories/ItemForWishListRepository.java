package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.ItemForWishList;
import com.nameless.volunteerproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemForWishListRepository extends JpaRepository<ItemForWishList, Long> {
    public boolean existsByUser_IdAndFundraising_Id(UUID userId, UUID fundraisingId);
    public ItemForWishListRepository findByUserAndFundraising(User userId, Fundraising fundraisingId);
}
