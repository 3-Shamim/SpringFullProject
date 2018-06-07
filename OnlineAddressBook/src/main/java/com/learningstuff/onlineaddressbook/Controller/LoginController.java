package com.learningstuff.onlineaddressbook.Controller;

import com.learningstuff.onlineaddressbook.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "view/Login";
    }

}
