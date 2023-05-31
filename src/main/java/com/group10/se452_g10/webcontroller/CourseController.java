package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.CourseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("courses", repo.findAll());
        if (session.getAttribute("course") == null) {
            model.addAttribute("course", new Course());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("course", session.getAttribute("course"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "course/list";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("course", repo.findById(id));
        return "redirect:/course";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/course";
    }

    @PostMapping
    public String save(@ModelAttribute Course course, HttpSession session) {
        if (course.getId() == 0)
            repo.save(course);
        else {
            var editCourse = repo.findById(course.getId()).get();
            editCourse.setDept(course.getDept());
            editCourse.setNum(course.getNum());
            //editCourse.setDescription(course.getDescription());
            repo.save(editCourse);
            session.setAttribute("course", null);
        }
        return "redirect:/course";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Course course) {
        if (course.getId() == 0)
            repo.save(course);
        else {
            var editCourse = repo.findById(course.getId()).get();
            editCourse.setDept(course.getDept());
            editCourse.setNum(course.getNum());
            repo.save(editCourse);
        }
        return "course/edit";
    }

}
