package org.dev.UserFinder.service;

import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.exception.UserNotFoundException;
import org.dev.UserFinder.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     Optional<User> isUserPresent = userRepo.findUserByName(username);

     if(isUserPresent.isPresent()){
         User user=isUserPresent.get();
         return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), Collections.emptyList());

     }
        throw new UserNotFoundException("Invalid Username ! ");
    }
}
