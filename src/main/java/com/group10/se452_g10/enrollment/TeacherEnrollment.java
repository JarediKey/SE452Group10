package com.group10.se452_g10.enrollment;
import com.google.gson.JsonArray;
import com.group10.se452_g10.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
//import org.hibernate.annotations.Table;

@Data
@Entity(name = "teacher_enrollments")
@NoArgsConstructor
@Table(name = "teacher_enrollments", uniqueConstraints={
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

    public TeacherEnrollment(String TeacherId, String CourseId, String CourseName) {
        this.TeacherId = TeacherId;
        this.CourseId = CourseId;
        this.CourseName = CourseName;
    }

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CourseId")
    private List<Course> course = new ArrayList<>();


}
