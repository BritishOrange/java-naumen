package com.develop.internetshop.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}