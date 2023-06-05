package com.group10.se452_g10.enrollment;
import com.group10.se452_g10.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {
@Query(value = "Select course from StudentEnrollment s where s.student = :id",nativeQuery = true)
List<Course> getAllCoursesByStudentId(@Param("id") String id);

}
