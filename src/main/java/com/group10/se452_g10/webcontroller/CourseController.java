package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.CourseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository repo;

    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        model.addAttribute("courses", repo.findAll());
        if (session.getAttribute("course") == null) {
            model.addAttribute("course", new Course());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("course", session.getAttribute("course"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "course/list_courses";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String createCourseForm(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "course/create_course";
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String createCourse(@ModelAttribute("course") Course course) {
        repo.save(course);
        return "redirect:/course/list";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        Course course = repo.findById(id).get();
        model.addAttribute("course", course);
        return "course/edit_course";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        course.setId(id);
        repo.save(course);
        return "redirect:/course/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/course/list";
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
