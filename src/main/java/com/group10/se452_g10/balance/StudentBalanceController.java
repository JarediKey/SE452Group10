package com.group10.se452_g10.balance;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-balances")
@Tag(name = "student balance",description = "Everything about cards")
public class StudentBalanceController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StudentBalanceController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Create a new student balance
    @PostMapping
    public ResponseEntity<StudentBalance> createStudentBalance(@RequestBody StudentBalance studentBalance) {
        StudentBalance createdBalance = mongoTemplate.save(studentBalance);
        return new ResponseEntity<>(createdBalance, HttpStatus.CREATED);
    }

    // Get student balance by studentId
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentBalance> getStudentBalanceByStudentId(@PathVariable Long studentId) {
        StudentBalance studentBalance = mongoTemplate.findOne(Query.query(Criteria.where("studentId").is(studentId)), StudentBalance.class);
        if (studentBalance != null) {
            return new ResponseEntity<>(studentBalance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a student balance
    @PutMapping("/{id}")
    public ResponseEntity<StudentBalance> updateStudentBalance(@PathVariable String id, @RequestBody StudentBalance studentBalance) {
        studentBalance.setId(id);
        StudentBalance updatedBalance = mongoTemplate.save(studentBalance);
        if (updatedBalance != null) {
            return new ResponseEntity<>(updatedBalance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a student balance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentBalance(@PathVariable String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), StudentBalance.class);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
