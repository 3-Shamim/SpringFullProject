package com.learningstuff.librarymanagentsystem.Model.Dao;

import com.learningstuff.librarymanagentsystem.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoLocal {
    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("1","user1","email1","address1","015"));
        userList.add(new User("2","user2","email2","address2","014"));
        userList.add(new User("3","user3","email3","address3","016"));
        userList.add(new User("4","user4","email4","address4","013"));
        userList.add(new User("5","user5","email5","address5","017"));
    }

    public List<User> getAllUser()
    {
        return userList;
    }
    public void addUser(User user)
    {
        userList.add(user);
    }
    public User getById(int id)
    {
        User user = null;
        for (User u: userList) {
            if (u.getUserId().equals(id))
            {
                user = u;
            }
        }
        return user;
    }
    public void deleteUserById(User user)
    {
        userList.remove(user);
    }
}
