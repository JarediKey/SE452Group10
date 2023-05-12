package com.group10.se452_g10.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.account.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Sql({"/db-sql/test/data-course-test.sql"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GpaServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GpaRepository gpaRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepo studentRepository;

    private List<Course> courses;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        courses = courseRepository.findAll();
        students = studentRepository.findAll();
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/api/gpa").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(17)));
    }

    @Test
    public void testQueryByStudentId() throws Exception {
        long randomId = students.stream().findAny().orElseThrow().getId();
        String jsonResult = mockMvc.perform(post("/api/gpa/queryByStudentId")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", String.valueOf(randomId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<GPA> gpaList = new Gson().fromJson(jsonResult, new TypeToken<List<GPA>>() {
        }.getType());
        gpaList.forEach(gpa -> assertEquals(randomId, gpa.getStudent().getId()));
    }

    @Test
    public void testQueryByCourseId() throws Exception {
        long randomId = courses.stream().findAny().orElseThrow().getId();
        String jsonResult = mockMvc.perform(post("/api/gpa/queryByCourseId")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", String.valueOf(randomId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<GPA> gpaList = new Gson().fromJson(jsonResult, new TypeToken<List<GPA>>() {
        }.getType());
        gpaList.forEach(gpa -> assertEquals(randomId, gpa.getCourse().getId()));
    }

    @Test
    public void testSave() throws Exception {
        List<GPA> beforeList = gpaRepository.findAll();

        long studentId = students.stream().findAny().orElseThrow().getId();
        long courseId = courses.stream().findAny().orElseThrow().getId();
        mockMvc.perform(post("/api/gpa/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("studentId", String.valueOf(studentId))
                        .param("courseId", String.valueOf(courseId))
                        .param("grade", String.valueOf(99))
                        .param("gpa", String.valueOf(4.0)))
                .andExpect(status().isOk());

        List<GPA> afterList = gpaRepository.findAll();

        assertEquals(beforeList.size() + 1, afterList.size());
    }

    @Test
    public void testDelete() throws Exception {
        List<GPA> beforeList = gpaRepository.findAll();

        long randomId = beforeList.stream().findAny().orElseThrow().getId();
        mockMvc.perform(delete("/api/gpa/delete")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("id", String.valueOf(randomId)))
                .andExpect(status().isOk());

        List<GPA> afterList = gpaRepository.findAll();

        assertEquals(beforeList.size() - 1, afterList.size());
    }
}