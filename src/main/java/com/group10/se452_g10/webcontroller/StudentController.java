//package com.group10.se452_g10.webcontroller;
//
//import com.group10.se452_g10.account.Student;
//import com.group10.se452_g10.account.StudentRepo;
//import com.group10.se452_g10.course.CourseRepository;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/student")
//public class StudentController {
//    @Autowired
//    private StudentRepo studentRepo;
//    @Autowired
//    private CourseRepository courseRepository;
//
//
//    @GetMapping()
//    public String createStudentForm(Model model) {
//
//        // create student object to hold student form data
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student/create-student";
//
//    }
//
//
////        @GetMapping
////    public String list(Model model, HttpSession session) {
////        model.addAttribute("students", studentRepo.findAll());
////        if (session.getAttribute("student") == null) {
////            model.addAttribute("student", new Student());
////            model.addAttribute("btnAddOrModifyLabel", "Add");
////        } else {
////            model.addAttribute("student", session.getAttribute("student"));
////            model.addAttribute("btnAddOrModifyLabel", "Modify");
////        }
////        return "student/list";
////    }
//
//    @PostMapping
//    public String save(@ModelAttribute Student student, HttpSession session) {
//        if (student.getId() == 0)
//            studentRepo.save(student);
//        else {
//            var editStudent = studentRepo.findById(student.getId()).get();
//            editStudent.setFirstName(student.getFirstName());
//            editStudent.setLastName(student.getLastName());
//            editStudent.setGender(student.getGender());
//            editStudent.setEmail(student.getEmail());
//            editStudent.setDob(student.getDob());
//
//            //editStudent.setDescription(course.getDescription());
//            studentRepo.save(editStudent);
//            session.setAttribute("student", null);
//        }
//        return "redirect:/student";
//    }
//
//
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
//        studentRepo.deleteById(id);
//        return "redirect:/students";
//    }
//
//
//}
//
//
