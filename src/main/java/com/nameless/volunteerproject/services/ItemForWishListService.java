package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.*;
import com.nameless.volunteerproject.repositories.ItemForWishListRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.repositories.WIshListRepository;
import com.nameless.volunteerproject.repositories.ItemForWishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemForWishListService {
    @Autowired
    private WIshListRepository wishListRepository;
    @Autowired
    private ItemForWishListRepository itemForWishListRepository;
    @Autowired
    private FundraisingService fundraisingService;

    @Autowired
    private UserRepository userRepository;

    public boolean checkExistingItemInWishList(UUID userId, UUID fundraisingId){
        return itemForWishListRepository.existsByUser_IdAndFundraising_Id(userId, fundraisingId);
    }

    public void addToWishlist(UUID userId, UUID fundraisingId) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findUserById(userId);
        Fundraising fundraising = fundraisingService.findFundraisingById(fundraisingId);

        // check if item already exists in wishlist
//        if (itemForWishListRepository.existsByUser_IdAndFundraising_Id(userId, fundraisingId)) {
//            removeFromWishlist(userId, fundraisingId);
//        }

        ItemForWishList itemForWishList = new ItemForWishList();
        itemForWishList.setUser(user);
        itemForWishList.setFundraising(fundraising);
        itemForWishListRepository.save(itemForWishList);
    }

    public void removeFromWishlist(UUID userId, UUID fundraisingId) {
        User user = userRepository.findUserById(userId);
        Fundraising fundraising = fundraisingService.findFundraisingById(fundraisingId);
//
        ItemForWishList itemForWishList = (ItemForWishList) itemForWishListRepository.findByUserAndFundraising(user, fundraising);
//        if (wishListItem == null) {
//            throw new RuntimeException("Item not found in wishlist");
//        }

        itemForWishListRepository.delete(itemForWishList);
    }


//    public WishList addToWishFirstTime(UUID id, String sessionToken) {
//        WishList wishlist = new WishList();
//        WishListItem item = new WishListItem();
//
//        item.setDate(new Date());
//        System.out.println("id "+id);
//        Fundraising fundraising=fundraisingService.findFundraisingById(id);
//
//        item.setFundraising(fundraisingService.findFundraisingById(id));
//        System.out.println("our "+fundraisingService.findFundraisingById(id));
//        wishlist.getItems().add(item);
//        wishlist.setSessionToken(sessionToken);
//        wishlist.setDate(new Date());
//        return wishListRepository.save(wishlist);
//    }
//    public WishList addToExistingWishList(UUID id, String sessionToken) {
//
//        WishList wishList = wishListRepository.findBySessionToken(sessionToken);
//        Fundraising p = fundraisingService.findFundraisingById(id);
//        Boolean fundraisingDoesExistInTheCart = false;
//        if (wishList != null) {
//            Set<WishListItem> items = wishList.getItems();
//            for (WishListItem item : items) {
//                if (item.getFundraising().equals(p)) {
//                    fundraisingDoesExistInTheCart = true;
//                    break;
//                }
//
//            }
//        }
//        if(!fundraisingDoesExistInTheCart && (wishList != null))
//        {
//            WishListItem item1 = new WishListItem();
//            item1.setDate(new Date());
//            item1.setFundraising(p);
//            wishList.getItems().add(item1);
//            return wishListRepository.saveAndFlush(wishList);
//        }
//        return null;
//    }
//
//    public WishList getWishListBySessionTokent(String sessionToken) {
//
//        return  wishListRepository.findBySessionToken(sessionToken);
//    }
//
//
//    public WishList removeItemWishList(UUID id, String sessionToken) {
//        WishList WishList = wishListRepository.findBySessionToken(sessionToken);
//        Set<WishListItem> items = WishList.getItems();
//        WishListItem item = null;
//        for(WishListItem item1 : items) {
//            if(item1.getId().equals(id)) {
//                item = item1;
//            }
//        }
//        items.remove(item);
//        WishList.setItems(items);
//        return wishListRepository.save(WishList);
//    }

    public void clearWishList(String sessionToken) {
        WishList sh = wishListRepository.findBySessionToken(sessionToken);
        wishListRepository.delete(sh);

    }
}
