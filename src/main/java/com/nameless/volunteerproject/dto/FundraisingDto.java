package com.nameless.volunteerproject.dto;

import com.nameless.volunteerproject.enums.FundraisingType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FundraisingDto {
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
    private String cardNumber;
    private String description;
    private boolean isActive;
    private boolean isSelected;
    private FundraisingType socialType;

}
