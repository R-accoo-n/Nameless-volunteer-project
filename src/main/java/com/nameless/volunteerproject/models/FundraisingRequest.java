package com.nameless.volunteerproject.models;


import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Fundraising request class for militaries
 */

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class FundraisingRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private UUID militaryId;
    private String requestName;
    private String whom;
    private boolean greyZone;
    private String description;
    private boolean isSatisfied;

}
