package com.develop.internetshop.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
}