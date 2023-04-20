package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.WishList;
import com.nameless.volunteerproject.models.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
}
