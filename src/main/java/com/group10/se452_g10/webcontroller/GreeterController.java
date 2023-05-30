package com.group10.se452_g10.webcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreeterController {
    @GetMapping("/api/greeting")
    public String greetingForm() {
        return "greeting";
    }

    @GetMapping("/api/greeting/{name}")
    public String greetingForm(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "greetme";
    }

}