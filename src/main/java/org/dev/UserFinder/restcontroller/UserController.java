package org.dev.UserFinder.restcontroller;

import jakarta.validation.Valid;
import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "This is Login Page";
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {

        System.out.println("ready to insert User");
        User user1 = userService.register(user);
        System.out.println("User inserted "+user1);
        return user1;
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestBody User user){

        return userService.searchUser(user);
    }
}
