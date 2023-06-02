package com.group10.se452_g10.webcontroller;


import com.group10.se452_g10.account.Admin;
import com.group10.se452_g10.account.AdminRepo;
import com.group10.se452_g10.account.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepo adminRepo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("admins", adminRepo.findAll());
        if (session.getAttribute("admin") == null) {
            model.addAttribute("admin", new Admin());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("admin", session.getAttribute("admin"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "admins/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        adminRepo.deleteById(id);
        return "redirect:/admins";
    }
}
