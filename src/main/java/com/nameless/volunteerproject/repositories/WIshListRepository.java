package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.WishList;
import com.nameless.volunteerproject.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WIshListRepository extends JpaRepository<WishList, Long> {
    WishList findBySessionToken(String sessionToken);
}
