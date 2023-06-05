package com.group10.se452_g10.webcontroller;


import com.group10.se452_g10.Se452Group10Application;
import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.payment.*;
import jakarta.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/payments")
public class PaymentController {


    @Autowired
    private PaymentMethodRepository paymentMethodRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CardDetailsRepository cardDetailsRepo;


    Logger logger = LoggerFactory.getLogger(Se452Group10Application.class);
    List<Student> studentList;

    @GetMapping
    public String form(Model model, HttpSession session) {
        model.addAttribute("info", null);

        String name = getUserDetails(model);

        studentList = studentRepo.findAll();
        Object f = model.getAttribute("username");
        String n = f.toString();
        Student student = new Student();
        for (Student s : studentList) {
            if (s.getUsername().equals(n)) {
                student = s;
            }
        }

        List<CardDetails> cardDetailsList = cardDetailsRepo.findAll();

        List<CardDetails> resultList = new ArrayList<>();

        for (CardDetails cardDetails : cardDetailsList) {

            if (cardDetails.getStudent().getId() == student.getId()) {
                resultList.add(cardDetails);

            }
        }

        logger.info("Details found with student ID -" + student.getId());

        model.addAttribute("cardDetails", resultList);

        model.addAttribute("currentStudent", student.getId());
        model.addAttribute("currentStudent1", student.getId());

        session.setAttribute("currentStudent", student);

        model.addAttribute("payment", new PaymentMethod());
        if (resultList.size() == 0) {

            model.addAttribute("currentStudent", "");
            session.setAttribute("currentStudent", "");

            logger.error("No details found for the given student id :" + student.getId());
        }


        return "payment/create";
    }


    @GetMapping("/view")
    public String list(Model model, HttpSession session) {
        model.addAttribute("info", null);

        model.addAttribute("payments", paymentMethodRepo.findAll());

        return "payment/list";
    }


    @GetMapping("/{student_id}")
    public String findByStudentId(@PathVariable(value = "student_id") Long student_id, Model model, HttpSession session) {

        model.addAttribute("info", "Payment History");

        var list = paymentMethodRepo.findAll();
        ArrayList<PaymentMethod> resultList = new ArrayList<>();

        Student student = studentRepo.findById(student_id)
                .orElseThrow(() -> new CardDetailsController.Message("Student not found for this id :: " + student_id));

        for (PaymentMethod paymentMethod : list) {

            if (paymentMethod.getStudent().getId().equals(student.getId())) {

                resultList.add(paymentMethod);


            }
        }
        model.addAttribute("payments", resultList);


        if (resultList.size() == 0) {
            logger.error("No payment details found for given id");
            throw new PaymentMethodController.Message("No payment details found for given id");
        }
        logger.info("Details found for the given student id");

        return "payment/list";

    }

    public String getUserDetails(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
            // Add more user details to the model as needed
        }

        return "userDetails";
    }


    @PostMapping
    public String save(@ModelAttribute PaymentMethod payment, Model model, HttpSession session) {
        Object f = session.getAttribute("currentStudent");
        f.toString();

        String n = f.toString();
        Student student = null;
        for (Student s : studentList) {
            if (s.getId().toString().equals(n)) {
                student = s;
            }
        }
        UUID uuid = UUID.randomUUID();
        payment.setTransactionId(uuid.toString());
//        payment.setStudent(student);
        paymentMethodRepo.save(payment);
        model.addAttribute("info", "Payment successful!!");


        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        paymentMethodRepo.deleteById(id);
        model.addAttribute("payments", paymentMethodRepo.findAll());

        model.addAttribute("info", "Payment deleted!!");

        return "payment/list";
    }


    @PostMapping("/addCard")
    public String saveCard(@ModelAttribute CardDetails cardDetails, Model model, HttpSession session) {

        Object f = session.getAttribute("currentStudent");
        f.toString();

        String n = f.toString();
        Student student = null;
        for (Student s : studentList) {
            if (s.getId().toString().equals(n)) {
                student = s;
            }
        }

        cardDetailsRepo.save(cardDetails);
        model.addAttribute("info", "card added successful!!");

        return "redirect:/payments";
    }

    @GetMapping("/deleteCard/{id}")
    public String deleteCard(@PathVariable("id") Long id, Model model, HttpSession session) {
        cardDetailsRepo.deleteById(id);

        model.addAttribute("info", "Payment deleted!!");

        return "redirect:/payments";
    }


}
