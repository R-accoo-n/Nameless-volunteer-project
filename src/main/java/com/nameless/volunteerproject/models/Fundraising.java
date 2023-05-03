package com.nameless.volunteerproject.models;

import com.nameless.volunteerproject.enums.FundraisingType;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *Fundraising class for volunteers and militaries
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Fundraising {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )

    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String whom;
    private Double sum;
    private String fundraisingName;
    private String cardNumber;
    private String description;
    private boolean isActive;
    private FundraisingType socialType;
    private String photo;

}
