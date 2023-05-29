package com.group10.se452_g10.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

//


//    @Query("SELECT c FROM students c WHERE c.lastName ILIKE %:keyword%" )
//    public List<Student> findByLastName(@Param("name")String name);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("select firstName , lastName , age from teachers where age<= :age")
    public List<Student> findByAgeLessThanEqual(@Param("age")long age);

    //@Query("SELECT * from students where firstName ILIKE %:name%")
    public List<Student> findByFirstName(@Param("name") String name);


}
