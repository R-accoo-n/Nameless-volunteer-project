package com.nameless.volunteerproject.models;

import com.nameless.volunteerproject.emuns.UserRole;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
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
    private String fatherName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    boolean showRealName;
    @OneToMany
    private List<Fundraising> fundraisings;
    @OneToMany
    private List<FundraisingRequest> requests;

    /**
     *User constructor for military and volunteer roles
     *
     * @param id user's id
     * @param surname user's surname
     * @param name user's name
     * @param fatherName user's father's name
     * @param email user's email
     * @param userName user's username
     * @param role user's role
     */

    public User(UUID id, String surname, String name, String fatherName, String email,
                String userName, UserRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fatherName = fatherName;
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
     * @param fatherName user's father's name
     * @param phoneNumber user's phone number
     * @param role = user's role
     */

    public User(UUID id, String surname, String name, String fatherName, String phoneNumber, UserRole role) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     * Constructor for administrator role
     *
     * @param id administrator's id
     * @param surname administrator's surname
     * @param name administrator's name
     * @param fatherName administrator's father name
     * @param email administrator's email
     */

    public User(UUID id, String surname, String name, String fatherName, String email) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.email = email;
    }
}