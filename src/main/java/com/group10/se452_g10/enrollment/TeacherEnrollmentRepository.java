package com.group10.se452_g10.enrollment;

import com.group10.se452_g10.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherEnrollmentRepository extends JpaRepository<TeacherEnrollment, Long> {

    @Query(value = "Select course from TeacherEnrollment t where t.TeacherId = :id",nativeQuery = true)
    List<Course> getAllCoursesByTeacherId(@Param("id") String id);
}