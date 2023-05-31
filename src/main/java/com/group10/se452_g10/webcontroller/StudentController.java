package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.course.Course;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepo repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("students", repo.findAll());
        if (session.getAttribute("student") == null) {
            model.addAttribute("student", new Student());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("student", session.getAttribute("student"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "student/list";
    }

    @PostMapping
    public String save(@ModelAttribute Student student, HttpSession session) {
        if (student.getId() == 0)
            repo.save(student);
        else {
            var editStudent = repo.findById(student.getId()).get();
            editStudent.setFirstName(student.getFirstName());
            editStudent.setLastName(student.getLastName());
            editStudent.setGender(student.getGender());
            editStudent.setEmail(student.getEmail());
            editStudent.setDob(student.getDob());

            //editStudent.setDescription(course.getDescription());
            repo.save(editStudent);
            session.setAttribute("student", null);
        }
        return "redirect:/student";
    }

    @GetMapping("/edit/{firstname}")
    public String get(@PathVariable("firstname") String name, Model model, HttpSession session) {
        session.setAttribute("student", repo.findByFirstName(name));
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/student";
    }


}


