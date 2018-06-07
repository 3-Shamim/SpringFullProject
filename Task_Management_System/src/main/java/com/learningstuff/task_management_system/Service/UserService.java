package com.learningstuff.task_management_system.Service;

import com.learningstuff.task_management_system.Model.Role;
import com.learningstuff.task_management_system.Model.User;
import com.learningstuff.task_management_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }
    public void createAdmin(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Role userRole = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User findOne(String email)
    {
        return userRepository.getOne(email);
    }

}
