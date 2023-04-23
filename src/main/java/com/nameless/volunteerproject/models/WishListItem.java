package com.nameless.volunteerproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity(name = "wishlistitem")
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fundraising_id", nullable=false, updatable=false)
    private Fundraising fundraising;
}
