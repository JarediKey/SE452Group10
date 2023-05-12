package com.group10.se452_g10.enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(name = "student_enrollments")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"StudentId", "CourseId"})
})
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CourseId", nullable = false, unique = true)
    private Long CourseId;

    @Column(name = "StudentId", nullable = false, unique = true)
    private Long StudentId;

   // @Column(name = "CourseId", nullable = false, unique = true)
    //private Long CourseId;

    @Column(name = "hours", nullable = false, unique = false)
    private int hours;

    @Column(name = "year", nullable = false, unique = false)
    private String year;

    @Column(name = "term", nullable = false, unique = false)
    private String term;
}
