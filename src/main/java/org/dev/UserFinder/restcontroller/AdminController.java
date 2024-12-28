package org.dev.UserFinder.restcontroller;

import org.dev.UserFinder.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    public String adimnLogin(@RequestBody User user){

        return "";
    }
}
