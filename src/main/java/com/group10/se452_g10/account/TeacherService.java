package com.group10.se452_g10.account;

import java.util.List;
import java.util.Optional;

import com.group10.se452_g10.course.Course;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;
@RestController
@RequestMapping("/api/teachers")
@Log4j2
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping
    @Operation(summary = "Returns all the Teachers in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation= Teacher.class))})
    public List<Teacher> list(){
        log.traceEntry("Enter list");
        var retval = teacherRepo.findAll();
        log.traceExit("Exit list", retval);
        return teacherRepo.findAll();
    }

    @PostMapping
    public void save(@RequestBody Teacher stud) {
        log.traceEntry("enter save", stud);
        teacherRepo.save(stud);
        log.traceExit("exit save", stud);
    }

    @DeleteMapping
    public void delete(Teacher stud) {
        log.traceEntry("Enter delete", stud);
        teacherRepo.delete( stud);
        log.traceExit("Exit delete");
    }


    @RequestMapping(value="/findteacher", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Teacher> findTeacher(@RequestParam("Id") long teacherId) {
        return teacherRepo.findById(teacherId);
    }






}
