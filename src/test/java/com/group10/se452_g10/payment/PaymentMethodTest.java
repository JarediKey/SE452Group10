package com.group10.se452_g10.payment;


import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import org.aspectj.lang.annotation.Before;
//import org.h2.message.DbException;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Sql({"/data-h2-test.sql"})
public class PaymentMethodTest {

    @Autowired
    private PaymentMethodRepository repository;

    @Autowired
    StudentRepo studentRepo;

//    @BeforeEach
//    public void setup() {
////        Student student1 = new Student();
////        student1.setFirstName("A12");
////        student1.setLastName("B34");
////        studentRepo.save(student1);
//    }


    @Test
    @Transactional
    public void testCreationPaymentMethod() {
        studentRepo.deleteAll();
        repository.deleteAll();
        long beforeCount = repository.count();


        PaymentMethod paymentMethod = new PaymentMethod();
        Student s1 = new Student();
//        s1.setId(1224L);
        s1.setFirstName("A12");
        s1.setLastName("B34");

        studentRepo.save(s1);
        long a = studentRepo.count();


        List<Student> studentList = studentRepo.findAll();
        Student s= studentList.get(0);

//        Student s = studentRepo.findById("1").get();

        paymentMethod.setStudent(s);
        paymentMethod.setDate(LocalDate.now());
        paymentMethod.setAmount(4500.0f);
        paymentMethod.setTypeOfMethod(PaymentMethodType.DEBIT_CARD.getMethod());
        paymentMethod.setTransactionId("adknf2324234");
        paymentMethod.setRemarks("Test payment");
        repository.save(paymentMethod);
        var afterCount = repository.count();
        assertEquals(beforeCount + 1, afterCount);





//        Student student1 = new Student();
//        student1.setFirstName("A12");
//        student1.setLastName("B34");
////        student1.setId(345L);
//        studentRepo.save(student1);
//        long a = studentRepo.count();
////        long beforeCount = repository.count();
//        PaymentMethod paymentMethod = new PaymentMethod();
//        Student s = studentRepo.findById("1").get();
//        paymentMethod.setStudent(s);
//        paymentMethod.setDate(LocalDate.now());
//        paymentMethod.setAmount(100.0f);
//        paymentMethod.setTypeOfMethod(PaymentMethodType.CREDIT_CARD.getMethod());
//        paymentMethod.setTransactionId("123456");
//        paymentMethod.setRemarks("Test payment");
//        PaymentMethod savedPaymentMethod = repository.save(paymentMethod);
//        assertNotNull(savedPaymentMethod.getId());
//        var afterCount = repository.count();
//        assertEquals(beforeCount + 1, afterCount);

    }


    @Test
    public void testPaymentMethodCreationWithNullValues() {
        try {
            studentRepo.deleteAll();
            repository.deleteAll();
            PaymentMethod paymentMethod = new PaymentMethod();

            Student student1 = new Student();
            student1.setFirstName("A12");
            student1.setLastName("B34");
            studentRepo.save(student1);

            List<Student> studentList = studentRepo.findAll();
            Student s= studentList.get(0);
            paymentMethod.setStudent(null);
            paymentMethod.setDate(LocalDate.now());
            paymentMethod.setAmount(100.0f);
            paymentMethod.setTypeOfMethod(PaymentMethodType.CREDIT_CARD.getMethod());
            paymentMethod.setTransactionId("123456");
            paymentMethod.setRemarks("Test payment");
            PaymentMethod savedPaymentMethod = repository.save(paymentMethod);

        } catch (Exception e) {
            // expected exception was thrown, test passed..studentId is marked
            String s = "could not execute statement; SQL [n/a]; constraint [null]";
            int a =s.length();
            a=e.getMessage().length();


//            assertEquals(true, e.getMessage().compareTo(s));
            assertEquals(s, e.getMessage());


        }
    }



    @Test
    public void testDeletePaymentMethod(){
        studentRepo.deleteAll();
        repository.deleteAll();
        PaymentMethod paymentMethod = new PaymentMethod();
        Student s1 = new Student();
//        s1.setId(1224L);
        s1.setFirstName("A12");
        s1.setLastName("B34");

        studentRepo.save(s1);
        Student s = studentRepo.findById(1L).get();

        paymentMethod.setStudent(s);
        paymentMethod.setDate(LocalDate.now());
        paymentMethod.setAmount(4500.0f);
        paymentMethod.setTypeOfMethod(PaymentMethodType.DEBIT_CARD.getMethod());
        paymentMethod.setTransactionId("adknf2324234");
        paymentMethod.setRemarks("Test payment");
         repository.save(paymentMethod);

//        PaymentMethod paymentMethod = new PaymentMethod();
//        Student s1 = new Student();
//        s1.setId(1224L);
//        paymentMethod.setStudent(s1);
//        paymentMethod.setDate(LocalDate.now());
//        paymentMethod.setAmount(4500.0f);
//        paymentMethod.setTypeOfMethod(PaymentMethodType.DEBIT_CARD.getMethod());
//        paymentMethod.setTransactionId("adknf2324234");
//        paymentMethod.setRemarks("Test payment");
//        PaymentMethod savedPaymentMethod = repository.save(paymentMethod);

        long count1 = repository.count();
        repository.delete(paymentMethod);
        long count2 = repository.count();

        assertEquals(count1-1,count2);

        PaymentMethod paymentMethod1 = new PaymentMethod();
        Student s2 = new Student();
//        s2.setId(1243334L);
        studentRepo.save(s2);

        Student s3 = studentRepo.findById(1L).get();

        paymentMethod1.setStudent(s3);
//        paymentMethod1.setStudent(s2);
        paymentMethod1.setDate(LocalDate.now());
        paymentMethod1.setAmount(4500.0f);
        paymentMethod1.setTypeOfMethod(PaymentMethodType.NET_BANKING.getMethod());
        paymentMethod1.setTransactionId("adknf232423422");
        paymentMethod1.setRemarks("Test payment");
        PaymentMethod savedPaymentMethod1 = repository.save(paymentMethod1);

        long count3 = repository.count();

        assertEquals(count2+1,count3);

        PaymentMethod savedPaymentMethod2 = repository.save(paymentMethod);

        long count4 = repository.count();

        assertEquals(count3+1,count4);

        repository.deleteAll();
        long count5 = repository.count();

        assertEquals(count5,0);


    }

    @Test
    public void testReadPaymentMethod(){
        studentRepo.deleteAll();
        repository.deleteAll();

        PaymentMethod paymentMethod = new PaymentMethod();
        Student s = new Student();
        s.setFirstName("A12");
        s.setLastName("B34");
//        s.setId(1244L);
        studentRepo.save(s);

        List<Student> studentList = studentRepo.findAll();
        Student s3= studentList.get(0);

//        Student s3 = studentRepo.findById("1").get();

        paymentMethod.setStudent(s3);
//        paymentMethod.setStudent(s);
        paymentMethod.setDate(LocalDate.now());
        paymentMethod.setAmount(4500.0f);
        paymentMethod.setTypeOfMethod(PaymentMethodType.DEBIT_CARD.getMethod());
        paymentMethod.setTransactionId("adknf2324234");
        paymentMethod.setRemarks("Test payment");

        PaymentMethod savedPaymentMethod = repository.save(paymentMethod);

        Optional<PaymentMethod> verifiedPayment =repository.findById(paymentMethod.getId());
        assertEquals(savedPaymentMethod.getId(),verifiedPayment.get().getId());
        assertEquals(savedPaymentMethod.getAmount(),paymentMethod.getAmount());
        assertEquals(savedPaymentMethod.getTransactionId(),paymentMethod.getTransactionId());


    }

    @Test
    public void testUniqueValuesVerification(){

        try {
            studentRepo.deleteAll();
            repository.deleteAll();
            Student s1 = new Student();
            s1.setId(122L);
            PaymentMethod paymentMethod = new PaymentMethod(null, s1, LocalDate.now(), 8908F, "lk", "dhfjvjhkj987", null);
            PaymentMethod paymentMethod1 = new PaymentMethod(null, s1, LocalDate.now(), 8908F, "lk", "dhfjvjhkj987", null);
            repository.save(paymentMethod);
            repository.save(paymentMethod1);
        } catch (Exception e) {
            // expected exception was thrown, test passed
            String str ="could not execute statement; SQL [n/a]; constraint ";
            assertEquals(str.substring(0,40), e.getMessage().substring(0,40),"Test Passed");

        }
    }

    @Test
    public void testUpdatePaymentMethod() {
        studentRepo.deleteAll();
        repository.deleteAll();
        PaymentMethod paymentMethod = new PaymentMethod();



        Student s1 = new Student();
        s1.setFirstName("A12");
        s1.setLastName("B34");

        s1.setId(1L);
        studentRepo.save(s1);
        List<Student> studentList =studentRepo.findAll();
        Student student = studentList.get(0);


        paymentMethod.setStudent(student);
        paymentMethod.setDate(LocalDate.now());
        paymentMethod.setAmount(100.0f);
        paymentMethod.setTypeOfMethod("Credit Card");
        paymentMethod.setTransactionId("123456");
        paymentMethod.setRemarks("Test payment");

        PaymentMethod savedPaymentMethod = repository.save(paymentMethod);
        Long savedPaymentMethodId = savedPaymentMethod.getId();

        PaymentMethod updatedPaymentMethod = new PaymentMethod();
        updatedPaymentMethod.setId(savedPaymentMethodId);
//        Student s2 = new Student();
//        s2.setId(2L);
//        s2.setFirstName("A12");
//        s2.setLastName("B34");
//        studentRepo.save(s1);
        List<Student> studentList2 =studentRepo.findAll();
        Student student2 = studentList2.get(0);
        updatedPaymentMethod.setStudent(student2);
        updatedPaymentMethod.setDate(LocalDate.now().plusDays(1));
        updatedPaymentMethod.setAmount(200.0f);
        updatedPaymentMethod.setTypeOfMethod("Debit Card");
        updatedPaymentMethod.setTransactionId("789012");
        updatedPaymentMethod.setRemarks("Updated payment");

        PaymentMethod updatedPaymentMethodResult = repository.save(updatedPaymentMethod);
        assertNotNull(updatedPaymentMethodResult);
        assertEquals(savedPaymentMethodId, updatedPaymentMethodResult.getId());
        assertEquals(updatedPaymentMethod.getStudent(), updatedPaymentMethodResult.getStudent());
        assertEquals(updatedPaymentMethod.getDate(), updatedPaymentMethodResult.getDate());
        assertEquals(updatedPaymentMethod.getAmount(), updatedPaymentMethodResult.getAmount());
        assertEquals(updatedPaymentMethod.getTypeOfMethod(), updatedPaymentMethodResult.getTypeOfMethod());
        assertEquals(updatedPaymentMethod.getTransactionId(), updatedPaymentMethodResult.getTransactionId());
        assertEquals(updatedPaymentMethod.getRemarks(), updatedPaymentMethodResult.getRemarks());


    }


}
