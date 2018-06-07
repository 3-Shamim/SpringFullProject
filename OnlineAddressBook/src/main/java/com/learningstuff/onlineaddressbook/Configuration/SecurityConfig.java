package com.learningstuff.onlineaddressbook.Configuration;

import com.learningstuff.onlineaddressbook.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/index").hasAnyRole("ADMIN","USER")
                .antMatchers("/address").hasAnyRole("ADMIN","USER")
                .antMatchers("/addAddress").hasAnyRole("ADMIN","USER")
                .antMatchers("/addressEdit/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/addressDelete/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/users").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin@mail.com").password(password()).roles("ADMIN","USER")
                .and()
                .withUser("user@mail.com").password(password()).roles("USER");*/

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }


    /*private String password() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode("123456");
    }*/
}
