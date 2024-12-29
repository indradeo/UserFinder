package org.dev.UserFinder.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dev.UserFinder.service.MyUserDetailsService;
import org.dev.UserFinder.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader=request.getHeader("Authorization");
        String username=null;
        String token=null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token=authorizationHeader.substring(7);
            username=jwtUtil.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(token,userDetails)){

                UsernamePasswordAuthenticationToken token1=
                        new UsernamePasswordAuthenticationToken(userDetails,null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(token1);
            }
        }

        filterChain.doFilter(request,response);
    }
}
