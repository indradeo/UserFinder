package org.dev.UserFinder.service;

import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.exception.NoUniqueFoundException;
import org.dev.UserFinder.exception.UserNotFoundException;
import org.dev.UserFinder.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public PasswordEncoder encoder;

    @Override
    public String login() {
        return "";
    }

    @Override
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User user1=null;
        try{
           user1= userRepo.save(user);
        }catch(Exception e){
           throw new NoUniqueFoundException("Username and email should be unique");
        }
        return user1;


    }

    @Override
    public List<User> searchUser(User user) {
        return userRepo.search(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getUsername());
    }

    @Override
    public User updateUser(User user) {
       Optional<User> isUserPresent= userRepo.findById(user.getId());
       if(isUserPresent.isPresent()){
           user.setPassword(encoder.encode(user.getPassword()));
           return userRepo.save(user);
       }
        throw new UserNotFoundException("Can't Update user with id "+user.getId()+", User is not exist ");
    }

    @Override
    public User findById(Integer id) {
        Optional<User> isUserPresent= userRepo.findById(id);
        if(isUserPresent.isPresent()){
            return isUserPresent.get();
        }
        throw new UserNotFoundException("User Not Found with id "+id+"!!");
    }

    @Override
    public String deleteById(Integer id) {
        boolean isExist=userRepo.existsById(id);
        if(isExist){
            userRepo.deleteById(id);
            return "User Deleted ";
        }
        throw new UserNotFoundException("User not present, Cannot be delete !");
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

}
