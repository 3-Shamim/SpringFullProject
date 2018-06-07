package com.learningstuff.onlineaddressbook.Service;

import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }


    public void createUser(User user)
    {
        user.setPassword(PasswordEncoder(user.getPassword()));
        userRepository.save(user);
    }

    private String PasswordEncoder(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return  encoder.encode(password);
    }

    public User findByEmail(String email) {

        return userRepository.getOne(email);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean isUserPresent(String email) {

        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isPresent())
        {
            return true;
        }

        return false;
    }

    public List<User> getUserByName(String name) {

        return userRepository.findUserByNameLike("%" + name + "%");
    }
}
