package com.group10.se452_g10.account;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WithMockUser(authorities = {"ADMIN"})
public class AdminMethodTest {

    @Autowired
    private AdminRepo adminRepo;

    @BeforeEach
    public void clearTable() {
        adminRepo.deleteAll();
    }

    @Test
    public void testCreationAdmin() {
        Admin s_1 = new Admin();
        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");
        long beforeCount = adminRepo.count();
        Admin s1_test = adminRepo.save(s_1);
        long afterCount = adminRepo.count();
        assertEquals(beforeCount + 1, afterCount);

    }

    @Test
    public void testDeleteAdminRecord() {
        Admin s_1 = new Admin();

        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");

        Admin s1_test = adminRepo.save(s_1);
        long count1 = adminRepo.count();
        adminRepo.delete(s1_test);
        long count2 = adminRepo.count();
        assertEquals(count1 - 1, count2);

        Admin s_2 = new Admin();
        s_2.setUsername("k.jone");
        s_2.setPassword("k.jose");
        s_2.setEmail("k.jose@depaul.edu");

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

}
