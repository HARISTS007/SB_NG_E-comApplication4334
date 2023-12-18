package com.tutor.Ecom.controller;

import com.tutor.Ecom.entity.User;
import com.tutor.Ecom.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/createUser"})
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Super Admin')")
    public String forAdmin() {
        return "This URL is only accessible to Admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the User";
    }
}
