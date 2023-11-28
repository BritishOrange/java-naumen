package com.develop.internetshop.entities.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * UserRepository
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findUserByEmail(String email);
}