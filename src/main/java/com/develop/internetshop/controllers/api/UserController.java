package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.entities.User.UserRepository;

/**
* UserController
*/
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController extends BaseApiController<User, Long> {
    public UserController(UserRepository userRepository) {
        super(userRepository);
    }
}

