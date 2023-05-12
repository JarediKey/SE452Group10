package com.group10.se452_g10.enrollment;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/student-enrollment")
@Tag(name = "StudentEnrollment", description = "Courses enrolled by the students")
@Log4j2
public class StudentEnrollmentService {
    @Autowired
    private StudentEnrollmentRepository repo;

    @GetMapping
    public List<StudentEnrollment> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @PostMapping("/save")
    public StudentEnrollment save(@RequestBody StudentEnrollment student) {
        log.traceEntry("enter save", student);
        var result = repo.save(student);
        log.traceExit("exit save", student);
        return result;
    }

    @PostMapping("/valid")
    public ResponseEntity<StudentEnrollment> saveValidated(@Valid @RequestBody StudentEnrollment student) {
        log.traceEntry("enter save", student);
        repo.save(student);
        log.traceExit("exit save", student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}
