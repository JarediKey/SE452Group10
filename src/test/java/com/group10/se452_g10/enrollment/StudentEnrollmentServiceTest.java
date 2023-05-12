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
        student.setEmail("stu_1@depaul.edu");
        Student resStudent = studentRepo.save(student);

        Course course = new Course();
        course.setDept("SE");
        course.setNum("453");
        Course resCourse = courseRepo.save(course);

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudentId(resStudent.getId());
        enrollment.setCourseId(resCourse.getId());
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
    StudentEnrollment student = new StudentEnrollment();
       // student.setEmail("stu_2@depaul.edu");
       // Student resStudent = studentRepo.save(student);

        String beforeContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int beforeSize = new JSONArray(beforeContent).length();

        // Save the new course into db and verify the status code.

        mockMvc.perform(post("/api/student-enrollment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        // List course and check if the size is incremented by 1
        String afterContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int afterSize = new JSONArray(afterContent).length();

        assertEquals(beforeSize + 1, afterSize);
    }



    @Test
    void testDelete() throws Exception {

        String beforeContent = mockMvc.perform(get("/api/student-enrollment").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<StudentEnrollment> beforeEnrollment = new Gson().fromJson(beforeContent,
                new TypeToken<List<StudentEnrollment>>(){}.getType());
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