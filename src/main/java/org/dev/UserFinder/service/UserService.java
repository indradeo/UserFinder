package org.dev.UserFinder.service;

import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    String login();
    User register(User user);

    List<User> searchUser(User user);

    User updateUser(User user ) ;

    User findById(Integer id);

    String deleteById(Integer id);

    List<User> findAll();
}
