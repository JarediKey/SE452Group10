package com.group10.se452_g10.payment;

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
public class PaymentRecordTest {


    @Autowired
    private PaymentRecordRepository repository;


    @Test
    @Transactional
    public void testCreationPaymentRecord() {

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setId(1L);
        paymentRecord.setTerm("2023");
        paymentRecord.setCourseFee(3456F);
        long beforeCount = repository.count();


        PaymentRecord paymentRecord1 = repository.save(paymentRecord);
        assertNotNull(paymentRecord1.getId());
        var afterCount = repository.count();
        assertEquals(beforeCount + 1, afterCount);

    }

    @Test
    public void testPaymentRecordCreationWithNullValues() {
        try {
            PaymentRecord paymentRecord = new PaymentRecord();
            paymentRecord.setTerm("2023");
            paymentRecord.setCourseFee(null);
        } catch (NullPointerException e) {
            // expected exception was thrown, test passed
            assertEquals("courseFee is marked non-null but is null", e.getMessage());
        }
    }


    @Test
    public void testDeletePaymentRecord() {

//        courseRepository.deleteAll();
        repository.deleteAll();

        PaymentRecord paymentRecord = new PaymentRecord( );
        paymentRecord.setTerm("2023");
        paymentRecord.setCourseFee(5678F);
        PaymentRecord savedPaymentMethod = repository.save(paymentRecord);

        long count1 = repository.count();
        repository.delete(paymentRecord);

        long count2 = repository.count();
        assertEquals(count1 - 1, count2);


        PaymentRecord paymentRecord1 = new PaymentRecord();
        paymentRecord1.setTerm("2024");
        paymentRecord1.setCourseFee(63834F);
        PaymentRecord savedPaymentMethod1 = repository.save(paymentRecord1);

        long count3 = repository.count();
        assertEquals(count2 + 1, count3);

        PaymentRecord savedPaymentMethod2 = repository.save(paymentRecord);
        long count4 = repository.count();

        assertEquals(count3 + 1, count4);

        repository.deleteAll();

        long count5 = repository.count();
        assertEquals(count5, 0);

    }

    @Test
    public void testReadPaymentRecord() {

        repository.deleteAll();

        PaymentRecord paymentRecord = new PaymentRecord( );
        paymentRecord.setTerm("2023");
        paymentRecord.setCourseFee(5678F);


        PaymentRecord savedPaymentMethod = repository.save(paymentRecord);



        Optional<PaymentRecord> verifiedPayment = repository.findById(paymentRecord.getId());
        assertEquals(savedPaymentMethod.getTerm(), verifiedPayment.get().getTerm());
        assertEquals(savedPaymentMethod.getCourseFee(), verifiedPayment.get().getCourseFee());


    }



    @Test
    public void testUpdatePaymentRecord() {

        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setTerm("2024");
        paymentRecord.setCourseFee(5678F);


        PaymentRecord savedCourse = repository.save(paymentRecord);
        Long savedTableId = savedCourse.getId();



        PaymentRecord paymentRecord1 = new PaymentRecord();
        paymentRecord.setTerm("2024");
        paymentRecord1.setId(savedTableId);

        paymentRecord1.setCourseFee(200.0f);
        Float updatedCourseFee = 200.0f;
        PaymentRecord updatedSave = repository.save(paymentRecord1);
        assertNotNull(updatedSave);
        assertEquals(savedTableId, updatedSave.getId());
        assertEquals(updatedCourseFee, updatedSave.getCourseFee());

    }


}
