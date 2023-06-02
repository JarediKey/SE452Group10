package com.group10.se452_g10.account;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@WithMockUser(authorities = {"ADMIN"})
@ActiveProfiles("test")
public class TeacherMethodTest {

    @Autowired
    private TeacherRepo teacherRepo;

    @BeforeEach
    public void clearTable() {
        teacherRepo.deleteAll();
    }

    @Test
    public void testCreationTeacher() {

        long beforeCount = teacherRepo.count();

        Teacher s_1 = new Teacher();
        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");
        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");
        s_1.setId(1L);

        Teacher s_2 = new Teacher();
        s_2.setUsername("k.jone");
        s_2.setPassword("k.jose");
        s_2.setEmail("k.jose@depaul.edu");
        s_2.setAddress("France");
        s_2.setFirstName("Jannine");
        s_2.setLastName("Jone");
        s_2.setGender("F");

        Teacher s1_test = teacherRepo.save(s_1);
        long count_one = teacherRepo.count();

        Teacher s2_test = teacherRepo.save(s_2);
        long count_two = teacherRepo.count();

        assertNotNull(s1_test.getId());
        assertEquals(count_one + 1, count_two);

    }

    @Test
    public void testUpdateTeacherRecord() {

        Teacher s_1 = new Teacher();
        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");
        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");

        teacherRepo.save(s_1);

        long count_one = teacherRepo.count();
        assertEquals(1, count_one);
    }

    @Test
    public void testDeleteStudentRecord() {

        Teacher s_1 = new Teacher();
        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");
        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");
        s_1.setId(2L);

        Teacher s1_test = teacherRepo.save(s_1);
        long count1 = teacherRepo.count();
        teacherRepo.delete(s1_test);
        long count2 = teacherRepo.count();
        assertEquals(count1 - 1, count2);

        Teacher s_2 = new Teacher();
        s_2.setUsername("k.jone");
        s_2.setPassword("k.jose");
        s_2.setEmail("k.jose@depaul.edu");
        s_2.setAddress("France");
        s_2.setFirstName("Grant");
        s_2.setLastName("Jone");
        s_2.setGender("M");
        s_2.setId(8L);

        Teacher s2_test = teacherRepo.save(s_2);
        long count3 = teacherRepo.count();
        teacherRepo.delete(s2_test);
        long count4 = teacherRepo.count();
        assertEquals(count3 - 1, count4);


    }

}


