package com.learningstuff.onlineaddressbook.Controller;


import com.learningstuff.onlineaddressbook.Model.Role;
import com.learningstuff.onlineaddressbook.Model.User;
import com.learningstuff.onlineaddressbook.Service.RoleService;
import com.learningstuff.onlineaddressbook.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "registration")
    public ModelAndView registration()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", new User());
        modelAndView.addObject("title", "Registration");
        modelAndView.setViewName("view/Registration");

        return modelAndView;
    }

    @PostMapping(value = "registration")
    public ModelAndView processRegistration(@ModelAttribute @Valid User user, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();


        if (bindingResult.hasErrors())
        {
            modelAndView.setViewName("view/Registration");
            return modelAndView;
        }

        if(userService.isUserPresent(user.getEmail()))
        {
            modelAndView.addObject("exist", true);
            modelAndView.setViewName("view/Registration");
            return modelAndView;
        }

        List<Role> roles = new ArrayList<>();

        roles.add(roleService.findByRole("USER"));

        user.setRoles(roles);

        userService.createUser(user);

        modelAndView.setViewName("redirect:/login");

        return modelAndView;
    }


}
