package com.group10.se452_g10.account;

import java.util.List;

import com.group10.se452_g10.course.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
@RestController
@RequestMapping("/api/teachers")
@Log4j2
public class TeacherService {



    @Autowired
    private TeacherRepo repo;


    @GetMapping
    @Operation(summary = "Returns all the Teachers in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation= Teacher.class))})
    public List<Teacher> list(){
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();
    }

    @PostMapping("/save")
    @Operation(summary = "Save the Course and returns the Teacher First name")
    public String save(@RequestBody Teacher teach) {
        log.traceEntry("enter save", teach);
        repo.save(teach);
        log.traceExit("exit save", teach);
        return teach.getFirstName();
    }

    @DeleteMapping
    @Operation(summary = "Delete the teacher")
    public void delete(Long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }



}
