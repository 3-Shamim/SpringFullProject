package com.learningstuff.librarymanagentsystem.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class securityConfiguration extends WebSecurityConfigurerAdapter{
    private String admin = "ADMIN";
    private String user = "USER";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/LMS/**").permitAll()
                .antMatchers("/admin/**").hasRole(admin)
                .antMatchers("/user/**").hasRole(user)
                .and()
                .formLogin()
                .loginPage("/LMS/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new customAuthenticationSuccessHandler());
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles(admin)
                .and()
                .withUser("user").password("user").roles(user);
    }
}
