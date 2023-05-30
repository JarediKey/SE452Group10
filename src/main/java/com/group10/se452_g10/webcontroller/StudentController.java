package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepo repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("student", repo.findAll());
        if (session.getAttribute("student") == null) {
            model.addAttribute("student", new Student());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("student", session.getAttribute("student"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "student/list";
    }

    @GetMapping("/edit/{firstname}")
    public String get(@PathVariable("firstname") String name, Model model, HttpSession session) {
        session.setAttribute("student", repo.findByFirstName(name));
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/students";
    }

//    @PostMapping
//    public String save(@ModelAttribute Student course, HttpSession session) {
//        if (course.getId() == 0)
//            repo.save(course);
//        else {
//            var editCourse = repo.findById(course.getId()).get();
//            editCourse.setDept(course.getDept());
//            editCourse.setNum(course.getNum());
//            editCourse.setDescription(course.getDescription());
//            repo.save(editCourse);
//            session.setAttribute("course", null);
//        }
//        return "redirect:/course";
//    }

    //    @PostMapping
//    public String validatedSave(@ModelAttribute Student course) {
//        if (course.getId() == 0)
//            repo.save(course);
//        else {
//            var editCourse = repo.findById(course.getId()).get();
//            editCourse.setDept(course.getDept());
//            editCourse.setNum(course.getNum());
//            repo.save(editCourse);
//        }
//        return "course/edit";
//    }

}


