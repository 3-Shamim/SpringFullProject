package com.learningstuff.onlineaddressbook.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learningstuff.onlineaddressbook.Model.AddressBook;
import com.learningstuff.onlineaddressbook.Model.Role;
import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Service.AddressBookService;
import com.learningstuff.onlineaddressbook.Service.RoleService;
import com.learningstuff.onlineaddressbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class testController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AddressBookService addressBookService;

    @RequestMapping(value = "role/{role}")
    @JsonIgnore
    public Role role(@PathVariable String role)
    {
        return roleService.findByRole(role);
    }

    @RequestMapping(value = "/users")
    public List<User> users()
    {
        return userService.getAllUser();
    }
    @RequestMapping(value = "/addresses")
    public List<AddressBook> addressBooks()
    {

        return addressBookService.getAllAddress();
    }





}
