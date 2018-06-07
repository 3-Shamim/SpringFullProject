package com.learningstuff.onlineaddressbook.Service;

import com.learningstuff.onlineaddressbook.Model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Optional<com.learningstuff.onlineaddressbook.Model.User> optionalUser = userService.findUserByEmail(email);

        

        optionalUser.orElseThrow(()-> new UsernameNotFoundException("User Not Found !"));

        CustomUserDetails customUserDetails = optionalUser.map(user -> new CustomUserDetails(user)).get();


        return customUserDetails;
    }
}
