package org.dev.UserFinder.restcontroller;

import org.dev.UserFinder.entity.User;
import org.dev.UserFinder.service.MyUserDetailsService;
import org.dev.UserFinder.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User user){
        this.doAuthentication(user.getUsername(),user.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails);
    }


    private void doAuthentication(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(username, password);
            manager.authenticate(token);
        }catch(BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Credentials");
        }

    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public String exceptionHandler() {

        return "Invalid Username and Password !!";
    }
}
