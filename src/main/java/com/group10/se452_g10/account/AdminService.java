package com.group10.se452_g10.account;

import java.util.List;

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
@RequestMapping("/api/admin")
@Log4j2
public class AdminService {

    @Autowired
    private AdminRepo repo;


    @GetMapping
    @Operation(summary = "Returns all the Admins in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation= Admin.class))})
    public List<Admin> list(){
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();
    }

    @PostMapping
    public void save(@RequestBody Admin admin) {
        log.traceEntry("enter save", admin);
        repo.save(admin);
        log.traceExit("exit save", admin);
    }

    @DeleteMapping
    public void delete(Long code) {
        log.traceEntry("Enter delete", code);
        repo.deleteById(code);
        log.traceExit("Exit delete");
    }

}
