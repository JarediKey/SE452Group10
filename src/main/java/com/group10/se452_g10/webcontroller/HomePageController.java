package com.group10.se452_g10.webcontroller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home")
    public String index(Model model) {
        return "login/home";
    }
    @GetMapping("/admins")
    public String Admin(Model model){

        return "login/admin/Admin";
    }
    @GetMapping("/teacher")
    public String Teacher(Model model){
        return "login/teacher/teacher";
    }
    @GetMapping("/student")
    public String Student(Model model){
        return "login/student/index";
    }

}
