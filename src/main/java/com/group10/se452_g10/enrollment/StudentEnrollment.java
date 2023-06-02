package com.group10.se452_g10.enrollment;

import com.group10.se452_g10.account.Student;
import com.group10.se452_g10.course.Course;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "student_enrollments")
@AllArgsConstructor
@NoArgsConstructor
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "Course", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "StudentId", nullable = false)
    private Student student;

    public StudentEnrollment(Course course, Student student) {
        this.course = course;
        this.student = student;

    }
}
