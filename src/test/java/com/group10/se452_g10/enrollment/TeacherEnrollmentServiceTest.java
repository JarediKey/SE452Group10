package com.group10.se452_g10.enrollment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.CourseRepository;
import com.group10.se452_g10.course.GPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeacherEnrollmentServiceTest {
    private List<Course> courses;

    @Autowired
    private MockMvc mockMvc;
    @Mock
 private TeacherEnrollmentRepository teacherRepo;
    @Mock
    private CourseRepository courseRepo;

    @InjectMocks
    private TeacherEnrollmentService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnrollTeacherInCourse_TeacherNotFound(){
        when(teacherRepo.findById(1L)).thenReturn(Optional.empty());

        TeacherEnrollment teacherEnrollment = new TeacherEnrollment();
        teacherEnrollment.setTeacherId("1");
        teacherEnrollment.setCourseId("1");

        ResponseEntity<String> response = service.enrollTeacherInCourse(teacherEnrollment);

        verify(teacherRepo, times(1)).findById(1L);
        verify(courseRepo, never()).findById(anyLong());
        verify(teacherRepo, never()).save(any());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Teacher not found with id: 1", response.getBody());
    }




}