package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.account.Teacher;
import com.group10.se452_g10.account.TeacherRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepo repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("teachers", repo.findAll());
        if (session.getAttribute("teachers") == null) {
            model.addAttribute("teacher", new Teacher());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("teacher", session.getAttribute("teacher"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "teachers/list";
    }

    @GetMapping("/edit/{firstname}")
    public String get(@PathVariable("firstname") String name, Model model, HttpSession session) {
        session.setAttribute("teacher", repo.findByFirstName(name));
        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/teacher";
    }

}