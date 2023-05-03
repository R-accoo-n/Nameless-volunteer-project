package com.nameless.volunteerproject.models;

import com.nameless.volunteerproject.models.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social_media")
public class SocialMedia {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String platform;
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SocialMedia(String platform, String link) {
        this.platform = platform;
        this.link = link;
    }

}

