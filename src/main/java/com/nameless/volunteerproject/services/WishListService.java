package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.WishList;
import com.nameless.volunteerproject.models.WishListItem;
import com.nameless.volunteerproject.repositories.WIshListRepository;
import com.nameless.volunteerproject.repositories.WishListItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WishListService {
    @Autowired
    private WIshListRepository wishListRepository;
    @Autowired
    private WishListItemRepository wishListItemRepository;
    @Autowired
    private FundraisingService fundraisingService;

    public WishList addToWishFirstTime(UUID id, String sessionToken) {
        WishList wishlist = new WishList();
        WishListItem item = new WishListItem();

        item.setDate(new Date());
        System.out.println("id "+id);
        Fundraising fundraising=fundraisingService.findFundraisingById(id);

        item.setFundraising(fundraisingService.findFundraisingById(id));
        System.out.println("our "+fundraisingService.findFundraisingById(id));
        wishlist.getItems().add(item);
        wishlist.setSessionToken(sessionToken);
        wishlist.setDate(new Date());
        return wishListRepository.save(wishlist);
    }
    public WishList addToExistingWishList(UUID id, String sessionToken) {

        WishList wishList = wishListRepository.findBySessionToken(sessionToken);
        Fundraising p = fundraisingService.findFundraisingById(id);
        Boolean fundraisingDoesExistInTheCart = false;
        if (wishList != null) {
            Set<WishListItem> items = wishList.getItems();
            for (WishListItem item : items) {
                if (item.getFundraising().equals(p)) {
                    fundraisingDoesExistInTheCart = true;
                    break;
                }

            }
        }
        if(!fundraisingDoesExistInTheCart && (wishList != null))
        {
            WishListItem item1 = new WishListItem();
            item1.setDate(new Date());
            item1.setFundraising(p);
            wishList.getItems().add(item1);
            return wishListRepository.saveAndFlush(wishList);
        }
        return null;
    }

    public WishList getWishListBySessionTokent(String sessionToken) {

        return  wishListRepository.findBySessionToken(sessionToken);
    }


    public WishList removeItemWishList(UUID id, String sessionToken) {
        WishList WishList = wishListRepository.findBySessionToken(sessionToken);
        Set<WishListItem> items = WishList.getItems();
        WishListItem item = null;
        for(WishListItem item1 : items) {
            if(item1.getId().equals(id)) {
                item = item1;
            }
        }
        items.remove(item);
        wishListItemRepository.delete(item);
        WishList.setItems(items);
        return wishListRepository.save(WishList);
    }

    public void clearWishList(String sessionToken) {
        WishList sh = wishListRepository.findBySessionToken(sessionToken);
        wishListRepository.delete(sh);

    }
}
