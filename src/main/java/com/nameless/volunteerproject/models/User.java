package com.nameless.volunteerproject.models;

import com.nameless.volunteerproject.enums.UserRole;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String surname;
    private String name;
    private String email;
    private String phoneNumber;
    private String userName;

    private String password;

    private String socialMedia;

    private boolean showRealName;

    private boolean isApproved;

    private boolean isBlocked;

    private String photo;

    @OneToMany
    private List<Fundraising> fundraisings;
    @OneToMany
    private List<FundraisingRequest> requests;
    @OneToMany
    private List<Fundraising>selected;

    /**
     *User constructor for military and volunteer roles
     *
     * @param id user's id
     * @param surname user's surname
     * @param name user's name
     * @param email user's email
     * @param userName user's username
     * @param role user's role
     */

    public User(UUID id, String surname, String name, String email,
                String userName, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userName = userName;
        this.role = role;
    }

    /**
     * Constructor for user role
     *
     * @param id user's id
     * @param surname user's surname
     * @param name user's name
     * @param phoneNumber user's phone number
     * @param role = user's role
     */

    public User(UUID id, String surname, String name, String phoneNumber, UserRole role) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     * Constructor for administrator role
     *
     * @param id administrator's id
     * @param surname administrator's surname
     * @param name administrator's name
     * @param email administrator's email
     */

    public User(UUID id, String surname, String name, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
    }
}