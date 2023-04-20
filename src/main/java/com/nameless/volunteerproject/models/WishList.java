package com.nameless.volunteerproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String sessionToken;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private Set<WishListItem> items = new HashSet<WishListItem>();

    public WishList() {

    }

}
