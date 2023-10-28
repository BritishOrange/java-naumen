package com.develop.internetshop.entities.User;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * User
 */

@Entity
@Table(name = "user_table")

@Getter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 50)
    private final String firstName;

    @Column(name = "middle_name", length = 50)
    private final String middleName;

    @Column(name = "last_name", length = 50)
    private final String lastName;

    @Column(name = "mobile", length = 15)
    private final String mobile;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private final String email;

    @Column(name = "password_hash", length = 32, nullable = false)
    private final String passwordHash;

    @Column(name = "user_type")
    private final UserType type;

    @Column(name = "registered_at")
    private final Date registeredAt;

    @Column(name = "last_login")
    private final Date lastLogin;

}