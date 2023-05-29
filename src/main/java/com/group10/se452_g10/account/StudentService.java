package com.group10.se452_g10.account;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/students")
@Log4j2
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    @Operation(summary = "Returns all the Students in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation= Student.class))})
    public List<Student> list(){
        log.traceEntry("Enter list");
        var retval = studentRepo.findAll();
        log.traceExit("Exit list", retval);
        return studentRepo.findAll();
    }


    @RequestMapping(value="/findstudent", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Student> findStudent(@RequestParam("Id") long studentId) {
        return studentRepo.findById(studentId);
    }


    @PostMapping
    public Student save(@RequestBody Student stud) {
        log.traceEntry("enter save", stud);
        studentRepo.save(stud);
        log.traceExit("exit save", stud);
        return stud;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.traceEntry("delete", id);
        studentRepo.deleteById(id);
        log.traceExit("delete", id);
    }


}
