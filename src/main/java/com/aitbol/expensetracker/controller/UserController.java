package com.aitbol.expensetracker.controller;

import com.aitbol.expensetracker.model.dto.UserDto;
import com.aitbol.expensetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    UserController(UserService service) {
        this.userService = service;
    }

    // localhost:8080/user/{username}
    @RequestMapping(method = RequestMethod.GET, value = "user/{username}", produces = { "application/json" })
    public UserDto getUser(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

}
