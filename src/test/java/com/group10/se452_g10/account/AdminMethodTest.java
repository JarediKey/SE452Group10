package com.group10.se452_g10.account;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class AdminMethodTest {

    @Autowired
    private AdminRepo adminRepo;



    @Test
    public void testCreationAdmin() {

        Admin s_1 = new Admin();
        s_1.setUsername("a.jose");
        long beforeCount = adminRepo.count();
        Admin s1_test = adminRepo.save(s_1);
        long afterCount = adminRepo.count();
        assertEquals(beforeCount + 1, afterCount);
//
//
//
//        Admin s_2 = new Admin();
//        s_2.setUsername("k.jone");
//        Admin s2_test = adminRepo.save(s_2);
//        assertNotNull(s2_test.getUsername());


    }


    @Test
    public void testDeleteAdminRecord() {

        Admin s_1 = new Admin();


        s_1.setUsername("a.jose");



        Admin s1_test = adminRepo.save(s_1);
        long count1 = adminRepo.count();
        adminRepo.delete(s1_test);
        long count2 = adminRepo.count();
        assertEquals(count1 - 1, count2);


        Admin s_2 = new Admin();
        s_2.setUsername("k.jone");


        Admin s2_test = adminRepo.save(s_2);
        long count3 = adminRepo.count();
        adminRepo.delete(s2_test);
        assertEquals(count2 + 1, count3);


        long count4 = adminRepo.count();
        assertEquals(count3 - 1, count4);
        adminRepo.deleteAll();

        long count5 = adminRepo.count();
        assertEquals(count5, 0);

    }

    @Test
    public void testReadAdminRecord() {



    }
}
