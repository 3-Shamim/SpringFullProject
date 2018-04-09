package com.learningstuff.librarymanagentsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/LMS")
public class homeController {

    @RequestMapping(value = "home")
    public String home(Model model)
    {
        model.addAttribute("title", "Home");
        return "home";
    }
    @RequestMapping(value = "about")
    public String about(Model model)
    {
        model.addAttribute("title", "About");

        return "about";
    }
    @RequestMapping(value = "login")
    public String login(Model model)
    {
        model.addAttribute("title", "Login");

        return "login";
    }
    @RequestMapping(value = "404error")
    public String error(Model model)
    {
        model.addAttribute("title","404 Error");
        return "404error";
    }
}
