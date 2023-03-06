package com.nameless.volunteerproject.models;

import com.nameless.volunteerproject.emuns.FundraisingType;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class Fundraising {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private UUID userId;
    private String whom;
    private Double sum;
    private String fundraisingName;
    private int cardNumber;
    private String description;
    private boolean isActive;
    private FundraisingType type;

}
