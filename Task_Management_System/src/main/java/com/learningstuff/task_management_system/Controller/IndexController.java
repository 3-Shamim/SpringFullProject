package com.learningstuff.task_management_system.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "")
    public String showIndexPage()
    {
        return "template/index";
    }

    @RequestMapping(value = "admin")
    public String showAdminPage()
    {
        return "template/admin";
    }
}
