package com.group10.se452_g10.account;

import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.Quarter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {



    @Query("select firstName , lastName , age from teachers where age<= :age")
    public List<Teacher> findByAgeLessThanEqual(@Param("age") long age);

    @Query("SELECT c FROM teachers c WHERE c.firstName ILIKE %:keyword%" )
    public List<Teacher> findByFirstName(@Param("name")String name);


    @Query("SELECT c FROM teachers c WHERE c.lastName ILIKE %:keyword%" )
    public List<Teacher> findByLastName(@Param("name")String name);



}
