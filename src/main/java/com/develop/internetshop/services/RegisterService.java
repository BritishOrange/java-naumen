package com.develop.internetshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.entities.User.UserRepository;
import com.develop.internetshop.entities.User.UserType;

/**
 * RegisterService
 */
@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String validatePostValues(
        String name, String email, String password, String confirmPassword
    ) {
        User user = userRepository.findUserByEmail(email);
        
        if (user != null) return "user with this email exists";
        if (name == "") return "name is empty";
        if (password.compareTo(confirmPassword) != 0) return "passwords do not match";

        return "";
    } 
    
    public void registerUser(
        String name, String email, String password, String confirmPassword
    ) {
        User user = new User(null, name, email, password, UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
