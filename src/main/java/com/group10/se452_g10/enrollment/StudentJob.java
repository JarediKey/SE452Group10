package com.group10.se452_g10.enrollment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "student_job")
@AllArgsConstructor
@NoArgsConstructor
public class StudentJob {
@Id
    Long id;
String StudentId;
    @Column(name = "partTimeJob")
String part_time;
    @Column(name = "fullTimeJob")
    String full_time;

}
