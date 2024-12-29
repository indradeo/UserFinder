package org.dev.UserFinder.config;

import org.dev.UserFinder.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {


    private final MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder encoder;
   public AppConfig(MyUserDetailsService myUserDetailsService){
        this.userDetailsService=myUserDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(encoder);

    }


}
                                                    