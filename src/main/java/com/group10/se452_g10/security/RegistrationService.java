//package com.group10.se452_g10.security;
//
//import com.group10.se452_g10.account.AdminRepo;
//import com.group10.se452_g10.account.Student;
//import com.group10.se452_g10.account.StudentRepo;
//import com.group10.se452_g10.account.TeacherRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class RegistrationService {
//
//    @Autowired
//    private AdminRepo adminRepo;
//
//    @Autowired
//    private StudentRepo studentRepo;
//
//    @Autowired
//    private TeacherRepo teacherRepo;
//
//
//    private PasswordEncoder encoder;
//
//
//    @PostMapping("/signup")
//    public String registerUser(Student student) {
//        if (studentRepo.existsByUsername(student.getUsername())
//
//        ) {
//            return "Error: Username is already taken!";
//        }
//
//        if (studentRepo.existsByEmail(student.getEmail())) {
//            return "Error: Email is already in use!";
//        }
//
//
//        return "User registered successfully!";
//    }
//}
