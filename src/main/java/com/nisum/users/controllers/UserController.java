package com.nisum.users.controllers;

import com.nisum.users.models.dto.UserRequest;
import com.nisum.users.models.dto.UserResponse;
import com.nisum.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/api/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
