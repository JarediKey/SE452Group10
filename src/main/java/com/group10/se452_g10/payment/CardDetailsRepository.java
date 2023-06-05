package com.group10.se452_g10.payment;

import com.group10.se452_g10.account.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails,Long> {

    @Query("SELECT cd FROM CardDetails cd WHERE cd.student = :student")
    ArrayList<CardDetails> findByStudent(@Param("student") Student student);
}
