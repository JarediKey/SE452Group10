package com.group10.se452_g10.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM courses c " +
            "WHERE c.dept ILIKE %:keyword% " +
            "OR c.num ILIKE %:keyword% " +
            "OR c.name ILIKE %:keyword% " +
            "OR CONCAT(c.dept, c.num) ILIKE %:keyword%")
    List<Course> search(@Param("keyword") String keyword);
}
