package com.develop.internetshop.entities.User;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * UserDataLoader
 */
@Component
public class UserDataLoader {
    private final UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void loadData() {
        userRepository.saveAll(
            List.of(
                new User(
                    null, "Ivan",
                    "Ivanovich",
                    "Ivanov",
                    "+79999999999",
                    "ivanov@ivanov",
                    "6fds7-gdas7-fdsff7",
                    UserType.USER,
                    new Date(),
                    new Date()
                ),
                new User(
                    null, "Kirill",
                    "Petrovich",
                    "Petrov",
                    "+79999999998",
                    "peter@ru",
                    "6fadsds7-gdas7-fdsff7",
                    UserType.USER,
                    new Date(),
                    new Date()
                ),
                new User(
                    null, "Andrey",
                    "Vladimirovich",
                    "Veselkov",
                    "+79999999997",
                    "andrew@ru",
                    "6fadsds7-ggdsas7-fdsff7",
                    UserType.ADMIN,
                    new Date(),
                    new Date()
                )
            )
        );
    }
}
