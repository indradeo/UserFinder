package org.dev.UserFinder.service;

import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public String login() {
        return "";
    }

    @Override
    public User register(User user) {


        return userRepo.save(user);
    }

    @Override
    public List<User> searchUser(User user) {
        return userRepo.search(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getUsername());
    }

}
