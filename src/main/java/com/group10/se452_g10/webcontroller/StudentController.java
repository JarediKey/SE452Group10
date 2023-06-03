package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.account.StudentService;
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
    private StudentRepo studentRepo;

    private StudentService studentService;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("students", studentRepo.findAll());
        if (session.getAttribute("student") == null) {
            model.addAttribute("student", new Student());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("student", session.getAttribute("student"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "student/list";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        //create student object to hold student from data
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/create_student";

    }

    @PostMapping
    public String save(@ModelAttribute Student student, HttpSession session) {
        if (student.getId() == 0)
            studentRepo.save(student);
        else {
            var editStudent = studentRepo.findById(student.getId()).get();
            editStudent.setFirstName(student.getFirstName());
            editStudent.setLastName(student.getLastName());
            editStudent.setGender(student.getGender());
            editStudent.setEmail(student.getEmail());
            editStudent.setDob(student.getDob());

            //editStudent.setDescription(course.getDescription());
            studentRepo.save(editStudent);
            session.setAttribute("student", null);
        }
        return "redirect:/student";
    }

    @GetMapping("/edit/{firstname}")
    public String get(@PathVariable("firstname") String name, Model model, HttpSession session) {
        session.setAttribute("student", studentRepo.findByFirstName(name));
        return "redirect:/student";
    }

//    @PostMapping("/students/{id}")
//    public String updateStudent(@PathVariable Long id,
//                                @ModelAttribute("student") Student student,
//                                Model model) {
//
//        // get student from database by id
//        Student existingStudent = studentService.findStudent(id);
//        existingStudent.setId(id);
//        existingStudent.setFirstName(student.getFirstName());
//        existingStudent.setLastName(student.getLastName());
//        existingStudent.setEmail(student.getEmail());
//
//        // save updated student object
//        studentService.updateStudent(existingStudent);
//        return "redirect:/students";
//    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        studentRepo.deleteById(id);
        return "redirect:/student";
    }


}


