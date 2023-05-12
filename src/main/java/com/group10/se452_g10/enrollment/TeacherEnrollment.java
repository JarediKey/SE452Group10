package com.group10.se452_g10.enrollment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "teacher_enrollments")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher_enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"TeacherId", "CourseId", "CourseName"})
})
public class TeacherEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TeacherId", nullable = false, unique = true)
    private String TeacherId;

    @Column(name = "CourseId", nullable = false, unique = true)
    private String CourseId;

    @Column(name = "CourseName", nullable = false, unique = true)
    private String CourseName;

    @Column(name = "year", nullable = false, unique = false)
    private String year;

    @Column(name = "term", nullable = false, unique = false)
    private String term;
}
