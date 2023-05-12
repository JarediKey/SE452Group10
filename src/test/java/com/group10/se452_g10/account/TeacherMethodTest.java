package com.group10.se452_g10.account;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
//@Sql({"/data-teacher-test.sql"})
public class TeacherMethodTest {

    @Autowired
    private TeacherRepo teacherRepo;

    @Test
    public void testCreationTeacher() {

        teacherRepo.deleteAll();
        long beforeCount = teacherRepo.count();


        Teacher s_1 = new Teacher();
        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");
        s_1.setId(1L);


        Teacher s_2 = new Teacher();
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
    public void testUpdateStudentRecord(){

        Teacher s_1 = new Teacher();

        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");

        Teacher s1_test = teacherRepo.save(s_1);
    }

    @Test
    public void testDeleteStudentRecord() {

        Teacher s_1 = new Teacher();

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


