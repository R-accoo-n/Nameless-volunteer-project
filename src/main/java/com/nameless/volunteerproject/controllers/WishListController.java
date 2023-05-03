package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.services.ItemForWishListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class WishListController {
    @Autowired
    private final ItemForWishListService itemForWishListService;

    @PostMapping("/addToWishlist/{userId}/{fundraisingId}")
    public String addToWishList(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId) throws ChangeSetPersister.NotFoundException {
//        String sessionToken = (String) request.getSession(true).getAttribute("sessiontTokenWishList");
//        if (sessionToken == null) {
//            sessionToken = UUID.randomUUID().toString();
//            request.getSession().setAttribute("sessiontTokenWishList", sessionToken);
//            wishListService.addToWishFirstTime(fundraisingId, sessionToken);
//        } else {
//            wishListService.addToExistingWishList(fundraisingId, sessionToken);
//        }
        System.out.println("user Id= "+userId);
        System.out.println("fundraising Id= "+fundraisingId);
        itemForWishListService.addToWishlist(userId, fundraisingId);
        return "redirect:/user/home/{userId}";
    }

    @PostMapping("/checkWishListItem/{userId}/{fundraisingId}")
    @ResponseBody
    public boolean checkWishListItem(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId) {
        return itemForWishListService.checkExistingItemInWishList(userId, fundraisingId);
    }


    @PostMapping("/removeWishListItem/{userId}/{fundraisingId}")
    public String removeItem(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId) {
        itemForWishListService.removeFromWishlist(userId, fundraisingId);
        return "redirect:/user/home/{userId}";
    }

    @GetMapping("/clearWishList/{userId}/{fundraisingId}")
    public String clearShoopiString(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontTokenWishList");
        request.getSession(false).removeAttribute("sessiontTokenWishList");
        itemForWishListService.clearWishList(sessionToken);
        return "redirect:/user/home/{userId}";
    }

}
