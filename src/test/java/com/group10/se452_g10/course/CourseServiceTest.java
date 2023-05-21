package com.group10.se452_g10.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
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
class CourseServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/api/courses").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void testSave() throws Exception {
        Course course = new Course();
        course.setDept("ART");
        course.setNum("101");
        course.setName("Introduction to Painting");

        String beforeContent = mockMvc.perform(get("/api/courses").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int beforeSize = new JSONArray(beforeContent).length();

        // Save the new course into db and verify the status code.
        mockMvc.perform(post("/api/courses/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());

        // List course and check if the size is incremented by 1
        String afterContent = mockMvc.perform(get("/api/courses").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        int afterSize = new JSONArray(afterContent).length();

        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    void testSearch() throws Exception {
        String keyword = "Introduction";
        mockMvc.perform(get("/api/courses/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("keyword", keyword))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void testDelete() throws Exception {
        List<Course> beforeList = courseRepository.findAll();

        Course newCourse = courseRepository.save(new Course("DEPT", "007", "DESC", 2023, Quarter.FALL));

        mockMvc.perform(delete("/api/courses/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", String.valueOf(newCourse.getId())))
                .andExpect(status().isOk());
        int afterSize = courseRepository.findAll().size();

        assertEquals(beforeList.size(), afterSize);
    }
}