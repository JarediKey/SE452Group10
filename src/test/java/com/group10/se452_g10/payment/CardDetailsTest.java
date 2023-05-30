package com.group10.se452_g10.payment;


import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(authorities = {"ADMIN"})
public class CardDetailsTest {


    @Autowired
    private CardDetailsRepository cardDetailsRepo;

    @Autowired
    private StudentRepo studentRepo;

    @BeforeEach
    @Transactional
    public void clearTable() {
        cardDetailsRepo.deleteAll();
        studentRepo.deleteAll();
    }

    @AfterEach
    public void clearTableAfterEach() {
        cardDetailsRepo.deleteAll();
        studentRepo.deleteAll();
    }

    @Test
    public void testCreationCardDetails() {
        long beforeCount = cardDetailsRepo.count();

        Student s1 = new Student();
        s1.setFirstName("A12");
        s1.setLastName("B34");
        s1.setUsername("Student1");
        s1.setPassword("Password1");
        s1.setEmail("student1@depaul.edu");
        studentRepo.save(s1);
        List<Student> studentList = studentRepo.findAll();
        Student student= studentList.get(0);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setStudent(student);
        cardDetails.setExpMonth("12");
        cardDetails.setExpYear("2026");
        cardDetails.setNumber("123456242343");
        cardDetails.setType("Rupay");
        cardDetailsRepo.save(cardDetails);

        long afterCount = cardDetailsRepo.count();
        assertEquals(beforeCount + 1, afterCount);
    }



    @Test
    public void testPaymentMethodCreationWithStudentNull() {
        try {
            CardDetails cardDetails = new CardDetails();

            cardDetails.setStudent(null);
            cardDetails.setExpMonth("12");
            cardDetails.setExpYear("2026");
            cardDetails.setNumber("123456242343");
            cardDetails.setType("Rupay");
            cardDetailsRepo.save(cardDetails);

        } catch (Exception e) {
            // expected exception was thrown, test passed..studentId is marked
            String s = "student is marked non-null but is null";
            assertEquals(s, e.getMessage());

        }
    }

    @Test
    public void testPaymentMethodCreationWithNullValues() {
        try {
            Student s1 = new Student();
            s1.setUsername("Student1");
            s1.setPassword("Password1");
            s1.setEmail("student1@depaul.edu");
            s1.setFirstName("A12");
            s1.setLastName("B34");
            studentRepo.save(s1);
            List<Student> studentList = studentRepo.findAll();
            Student student= studentList.get(0);

            CardDetails cardDetails = new CardDetails();

            cardDetails.setStudent(student);
            cardDetails.setExpMonth(null);
            cardDetails.setExpYear("2026");
            cardDetails.setNumber("123456242343");
            cardDetails.setType("Rupay");
            cardDetailsRepo.save(cardDetails);
        } catch (Exception e) {
            // expected exception was thrown, test passed..studentId is marked
            String s = "expMonth is marked non-null but is null";
            assertEquals(s, e.getMessage());

        }
    }


    @Test
    public void testDeleteCardDetails(){
        long beforeCount = cardDetailsRepo.count();

        Student s1 = new Student();
        s1.setUsername("Student1");
        s1.setPassword("Password1");
        s1.setEmail("student1@depaul.edu");
        s1.setFirstName("A12");
        s1.setLastName("B34");
        studentRepo.save(s1);
        List<Student> studentList = studentRepo.findAll();
        Student student= studentList.get(0);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setStudent(student);
        cardDetails.setExpMonth("12");
        cardDetails.setExpYear("2026");
        cardDetails.setNumber("123456242343");
        cardDetails.setType("Rupay");
        cardDetailsRepo.save(cardDetails);

        long afterCount = cardDetailsRepo.count();
        assertEquals(beforeCount + 1, afterCount);

        cardDetailsRepo.delete(cardDetails);

        long afterCount1 = cardDetailsRepo.count();
        assertEquals(beforeCount , afterCount1);

    }


    @Test
    public void testReadCardDetails(){
        long beforeCount = cardDetailsRepo.count();

        Student s1 = new Student();
        s1.setUsername("Student1");
        s1.setPassword("Password1");
        s1.setEmail("student1@depaul.edu");
        s1.setFirstName("A12");
        s1.setLastName("B34");
        studentRepo.save(s1);
        List<Student> studentList = studentRepo.findAll();
        Student student= studentList.get(0);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setStudent(student);
        cardDetails.setExpMonth("12");
        cardDetails.setExpYear("2026");
        cardDetails.setNumber("123456242343");
        cardDetails.setType("Rupay");


        CardDetails savedCardDetails =  cardDetailsRepo.save(cardDetails);

        Optional<CardDetails> verifiedCardDetails = cardDetailsRepo.findById(savedCardDetails.getId());
        assertEquals(savedCardDetails.getId(),verifiedCardDetails.get().getId());
        assertEquals(savedCardDetails.getExpMonth(),verifiedCardDetails.get().getExpMonth());
        assertEquals(savedCardDetails.getExpYear(),verifiedCardDetails.get().getExpYear());
        assertEquals(savedCardDetails.getNumber(),verifiedCardDetails.get().getNumber());
        assertEquals(savedCardDetails.getType(),verifiedCardDetails.get().getType());
        assertEquals(savedCardDetails.getStudent(),verifiedCardDetails.get().getStudent());


    }

//    @Test
//    public void testForeignKey(){
//
//        studentRepo.deleteAll();
//        cardDetailsRepo.deleteAll();
//        long beforeCount = cardDetailsRepo.count();
//
//        Student s1 = new Student();
//        s1.setId(1224L);
//        s1.setFirstName("A12");
//        s1.setLastName("B34");
//        studentRepo.save(s1);
//        List<Student> studentList = studentRepo.findAll();
//        Student student= studentList.get(0);
//
//        CardDetails cardDetails = new CardDetails();
//        cardDetails.setStudent(student);
//        cardDetails.setExpMonth("12");
//        cardDetails.setExpYear("2026");
//        cardDetails.setNumber("123456242343");
//        cardDetails.setType("Rupay");
//        cardDetailsRepo.save(cardDetails);
//
//        long afterCount = cardDetailsRepo.count();
//        assertEquals(beforeCount + 1, afterCount);
//
//        studentRepo.deleteAll();
//
////        cardDetailsRepo.delete(cardDetails);
//
//        long afterCount1 = cardDetailsRepo.count();
//        assertEquals(beforeCount , afterCount1);
//
//
//    }

    @Test
    public void testUpdateCardDetails(){
        long beforeCount = cardDetailsRepo.count();

        Student s1 = new Student();
        s1.setUsername("Student1");
        s1.setPassword("Password1");
        s1.setEmail("student1@depaul.edu");
        s1.setFirstName("A12");
        s1.setLastName("B34");
        studentRepo.save(s1);
        List<Student> studentList = studentRepo.findAll();
        Student student= studentList.get(0);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setStudent(student);
        cardDetails.setExpMonth("12");
        cardDetails.setExpYear("2026");
        cardDetails.setNumber("123456242343");
        cardDetails.setType("Rupay");


        CardDetails savedCardDetails =  cardDetailsRepo.save(cardDetails);


        CardDetails updateCardDetails = new CardDetails();
        updateCardDetails.setId(savedCardDetails.getId());
        updateCardDetails.setStudent(savedCardDetails.getStudent());
        updateCardDetails.setType("MasterCard");
        updateCardDetails.setNumber("9887765433467");
        updateCardDetails.setExpYear("2030");
        updateCardDetails.setExpMonth("10");

        CardDetails savedUpdatedCardDetails = cardDetailsRepo.save(updateCardDetails);


        Optional<CardDetails> verifiedCardDetails = cardDetailsRepo.findById(savedUpdatedCardDetails.getId());
        assertEquals(savedUpdatedCardDetails.getId(),verifiedCardDetails.get().getId());
        assertEquals(savedUpdatedCardDetails.getExpMonth(),verifiedCardDetails.get().getExpMonth());
        assertEquals(savedUpdatedCardDetails.getExpYear(),verifiedCardDetails.get().getExpYear());
        assertEquals(savedUpdatedCardDetails.getNumber(),verifiedCardDetails.get().getNumber());
        assertEquals(savedUpdatedCardDetails.getType(),verifiedCardDetails.get().getType());
        assertEquals(savedUpdatedCardDetails.getStudent(),verifiedCardDetails.get().getStudent());
    }

}
