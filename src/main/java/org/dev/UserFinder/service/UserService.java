package org.dev.UserFinder.service;

import org.dev.UserFinder.entity.User;

import java.util.List;

public interface UserService {

    String login();
    User register(User user);


    List<User> searchUser(User user);
}
