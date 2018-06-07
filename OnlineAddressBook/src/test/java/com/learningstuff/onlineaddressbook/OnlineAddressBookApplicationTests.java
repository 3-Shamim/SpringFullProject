package com.learningstuff.onlineaddressbook;

import com.learningstuff.onlineaddressbook.Model.AddressBook;
import com.learningstuff.onlineaddressbook.Model.Role;
import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Service.AddressBookService;
import com.learningstuff.onlineaddressbook.Service.RoleService;
import com.learningstuff.onlineaddressbook.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineAddressBookApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AddressBookService addressBookService;

    private static boolean oneTimeSetUpDone = false;

    @Before
    public void init()
    {
        if(!oneTimeSetUpDone){

            roleService.createRole(new Role("ADMIN"));
            roleService.createRole(new Role("USER"));

            List<Role> userRole = new ArrayList<>();

            userRole.addAll(roleService.getAllRole());

            userService.createUser(new User("admin@mail.com","admin","123456", userRole));

            userRole.clear();

            userRole.add(roleService.findByRole("USER"));

            userService.createUser(new User("user@mail.com","user","123456", userRole));

            User user = userService.findByEmail("admin@mail.com");


            AddressBook addressBook = new AddressBook("Name1", "HomeAddress1","CurrentAddress1","01555---","mail@mail.com", "");
       /* addressBooks.add(new AddressBook("HomeAddress2", "CurrentAddress2","01555---","mail@mail.com"));
        addressBooks.add(new AddressBook("HomeAddress3", "CurrentAddress3","01555---","mail@mail.com"));*/

            addressBookService.addAddressBook(user, addressBook);

            oneTimeSetUpDone = true;

        }


    }


	@Test
	public void contextLoads() {

        User user = userService.findByEmail("admin@mail.com");

        assertNotNull(user);

//        assertEquals(user.getEmail(), "admin@mail.com");

        User user1 = userService.findByEmail("user@mail.com");
        assertNotNull(user1);


        Role role = roleService.findByRole("ADMIN");
        assertNotNull(role);


//        AddressBook addressBook = addressBookService.findUserAddressBook(user);

//        assertNotNull(addressBook);


//        System.out.println(user.getRoles());

//        System.out.println(user1.getRoles());

//        assertNotNull(user.getRoles());
//        assertNotNull(user1.getRoles());


    }


}
