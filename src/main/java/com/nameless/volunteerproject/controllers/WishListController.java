package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.WishList;
import com.nameless.volunteerproject.services.WishListService;
import lombok.AllArgsConstructor;
import org.apache.juli.WebappProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class WishListController {
    @Autowired
    private final WishListService wishListService;

//    @ModelAttribute
//    public void populateModel(Model model, HttpServletRequest request) {
//        String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
//        String sessionTokenwishList = (String) request.getSession(true).getAttribute("sessiontTokenWishList");
//        if(sessionTokenwishList == null) {
//            model.addAttribute("whishList", new WishList());
//
//        }
//        else {
//            model.addAttribute("whishList", wishlistService.getWishListBySessionTokent(sessionTokenwishList));
//        }
//        model.addAttribute("categories",productService.getAllCategories());
//
//        model.addAttribute("brands",productService.getAllBrands());
//        model.addAttribute("featured",productService.getProductWithBigestDiscount());
//    }

    @GetMapping("/addToWishlist/{userId}/{fundraisingId}")
    public String addToWishList(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId, HttpServletRequest request){
        String sessionToken = (String) request.getSession(true).getAttribute("sessiontTokenWishList");
        if (sessionToken == null) {
            sessionToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("sessiontTokenWishList", sessionToken);
            wishListService.addToWishFirstTime(fundraisingId, sessionToken);
        } else {
            wishListService.addToExistingWishList(fundraisingId, sessionToken);
        }
        return "redirect:/user/home/{userId}";
    }

    @GetMapping("/removeWishListItem/{userId}/{fundraisingId}")
    public String removeItem(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontTokenWishList");
        System.out.println("got here ");
        wishListService.removeItemWishList(fundraisingId, sessionToken);
        return "redirect:/user/home/{userId}";
    }

    @GetMapping("/clearWishList/{userId}/{fundraisingId}")
    public String clearShoopiString(@PathVariable("userId")UUID userId, @PathVariable("fundraisingId")UUID fundraisingId, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontTokenWishList");
        request.getSession(false).removeAttribute("sessiontTokenWishList");
        wishListService.clearWishList(sessionToken);
        return "redirect:/user/home/{userId}";
    }

}
