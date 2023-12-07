package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.User.User;
import com.develop.internetshop.entities.User.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
* UserController
*/
@RestController
@RequestMapping(path = "api/v1/user")

@Tag(name="Users", description="User REST controller")
public class UserController extends BaseApiController<User, String> {
    public UserController(UserRepository userRepository) {
        super(userRepository);
    }
}

