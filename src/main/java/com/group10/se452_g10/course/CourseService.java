package com.group10.se452_g10.course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course", description = "Everything about your Course")
@Log4j2
public class CourseService {

    @Autowired
    private CourseRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the Course in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=Course.class))})
    public List<Course> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();
    }

    @PostMapping("/save")
    @Operation(summary = "Save the Course and returns the Course id")
    public long save(Course course) {
        log.traceEntry("enter save", course);
        repo.save(course);
        log.traceExit("exit save", course);
        return course.getId();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete the Course")
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
