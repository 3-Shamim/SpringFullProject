package com.learningstuff.librarymanagentsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class userController {

    @RequestMapping(value = "")
    public String userPage(Model model)
    {
        model.addAttribute("title", "Dash Board");

        return "user/userDashBoard";
    }
}
