package com.group10.se452_g10.enrollment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.CourseRepository;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser(authorities = {"ADMIN"})
@ActiveProfiles("test")
class StudentEnrollmentServiceTest {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    CourseRepository courseRepo;
    @Autowired
    StudentEnrollmentRepository studentEnrollmentRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        studentRepo.deleteAll();
        courseRepo.deleteAll();
        studentEnrollmentRepo.deleteAll();

        Student student = new Student();
        student.setEmail("stu_1@depau.edu");
        student.setUsername("stu_1");
        student.setPassword("stu_1");
        Student resStudent = studentRepo.save(student);

        Course course = new Course();
        course.setDept("SE");
        course.setNum("453");
        Course resCourse = courseRepo.save(course);

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(resStudent);
        enrollment.setCourse(resCourse);
        studentEnrollmentRepo.save(enrollment);
    }

    @Test
    void list() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/student-enrollment")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        int size = new JSONArray(result.getResponse().getContentAsString()).length();
        assertEquals(1, size);
    }

    @Test
    void save() throws Exception {
       /* Student student1 = new Student();
        student1.setEmail("stu_3@depau.edu");
        student1.setUsername("stu_3");
        student1.setPassword("stu_3");
        Student resStudent = studentRepo.save(student1);

        Course course = new Course();
        course.setDept("CS");
        course.setNum("453");
        Course resCourse = courseRepo.save(course);

        StudentEnrollment student = new StudentEnrollment();
        //student1.setEmail("stu_3@depaul.edu");
       // Student resStudent1 = studentRepo.save(student1);
        student.setStudent(resStudent);
        student.setCourse(resCourse);
        studentEnrollmentRepo.save(student);*/
        Student s_1 = new Student();
        s_1.setUsername("a.jose");
        s_1.setPassword("a.jose");
        s_1.setEmail("a.jose@depaul.edu");
        s_1.setAddress("Chicago");
        s_1.setFirstName("Ayyub");
        s_1.setLastName("Jose");
        s_1.setGender("M");
        s_1.setId(1L);
        Student resStudent = studentRepo.save(s_1);

        Student s_2 = new Student();
        s_2.setUsername("k.jone");
        s_2.setPassword("k.jose");
        s_2.setEmail("k.jose@depaul.edu");
        s_2.setAddress("France");
        s_2.setFirstName("Jannine");
        s_2.setLastName("Jone");
        s_2.setGender("F");
        Student resStudent1 = studentRepo.save(s_2);

        Course course = new Course();
        course.setDept("ART");
        course.setNum("101");
        course.setName("Introduction to Painting");
        Course resCourse = courseRepo.save(course);

        Course course1 = new Course();
        course.setDept("CS");
        course.setNum("102");
        course.setName("Introduction to Java");
        Course resCourse1 = courseRepo.save(course1);

        StudentEnrollment student = new StudentEnrollment();
        //student1.setEmail("stu_3@depaul.edu");
        // Student resStudent1 = studentRepo.save(student1);
        student.setStudent(resStudent);
        student.setStudent(resStudent1);
        student.setCourse(resCourse);
        student.setCourse(resCourse1);
        studentEnrollmentRepo.save(student);

        String beforeContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int beforeSize = new JSONArray(beforeContent).length();

        // Save the new course into db and verify the status code.

        mockMvc.perform(post("/api/student-enrollment/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        // List course and check if the size is incremented by 1
        String afterContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int afterSize = new JSONArray(afterContent).length();

        assertEquals(beforeSize, afterSize);
    }


    @Test
    void testDelete() throws Exception {

        String beforeContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<StudentEnrollment> beforeEnrollment = new Gson().fromJson(beforeContent,
                new TypeToken<List<StudentEnrollment>>() {
                }.getType());
        int beforeSize = beforeEnrollment.size();

        long id = beforeEnrollment.stream().findAny().orElseThrow().getId();
        mockMvc.perform(delete("/api/student-enrollment/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String afterContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int afterSize = new JSONArray(afterContent).length();

        assertEquals(beforeSize - 1, afterSize);

    }
}