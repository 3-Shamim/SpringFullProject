package com.movietickets.movietickets.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class HomeController {

    @RequestMapping(value = "home")
    private String home(Model model)
    {
        model.addAttribute("title", "Dash Board");
        return "AdminFeatures/Home";
    }


}
