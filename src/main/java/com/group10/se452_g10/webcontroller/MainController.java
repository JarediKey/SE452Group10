package com.group10.se452_g10.webcontroller;

import com.group10.se452_g10.account.AdminRepo;
import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.account.TeacherRepo;
import com.group10.se452_g10.utility.Web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AdminRepo adminRepo;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = Web.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "admin/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "welcomePage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = Web.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        if(userInfo.contains("ROLE_ADMIN"))
        {
            return "adminPage";
        }

        if(userInfo.contains("ROLE_STUDENT"))
        {
            model.addAttribute("student", principal.getName());

            Iterator<Student> studentIterator = studentRepo.findAll().iterator();
            List<Student> students = new ArrayList<Student>();
            Student curP = null;
            while (studentIterator.hasNext()) {
                curP = studentIterator.next();
                if(curP.getFirstName().equals(principal.getName()))
                {
                    students.add(curP);

                }
            }

            model.addAttribute("Patients", students);

            ////////////////////////////////////////////////////////////

            return "DoctorPortal";
        }



        if(userInfo.contains("ROLE_TEACHER"))
        {
            return "tacherPortal";
        }



        return "403Page";
    }
}
