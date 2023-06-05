package com.group10.se452_g10.enrollment;

import java.util.List;
import java.util.Optional;

import com.group10.se452_g10.account.Teacher;
import com.group10.se452_g10.course.Course;
import com.group10.se452_g10.course.CourseRepository;
import com.group10.se452_g10.course.GPA;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/teacher-enrollment")
@Tag(name = "TeacherEnrollment", description = "Courses being taught by the teachers")
@Log4j2
public class TeacherEnrollmentService {

    @Autowired
    private TeacherEnrollmentRepository repo;
    private CourseRepository courseRepository;
    private TeacherEnrollmentRepository teacherEnrollmentRepository;

    @GetMapping
    public List<TeacherEnrollment> list() {
        log.traceEntry("Enter list");
        var retval= repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

   @PostMapping
    public TeacherEnrollment save(@RequestBody TeacherEnrollment teacher) {
        log.traceEntry("enter save", teacher);
        repo.save(teacher);
        log.traceExit("exit save", teacher);
        return teacher;
    }

    @PostMapping("/valid")
    public ResponseEntity<TeacherEnrollment> saveValidated(@Valid @RequestBody TeacherEnrollment teacher) {
        log.traceEntry("enter save", teacher);
        repo.save(teacher);
        log.traceExit("exit save", teacher);
        return ResponseEntity.ok(teacher);
    }

    //public void enrollTeacherInCourse(Long TeacherId, Long id){
      //  TeacherEnrollment teacher = repo.findById(TeacherId).orElseThrow(()->new IllegalArgumentException("Teacher not found with id:"+TeacherId));
        //Course course = courseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Course not found with id:"+id));

  @PostMapping("/TeacherEnrollment/{TeacherId}/Course/{id}")
  public ResponseEntity<String> enrollTeacherInCourse(@RequestBody TeacherEnrollment enrollment){
      String teacherId = enrollment.getTeacherId();
      String courseId = enrollment.getCourseId();

     // Check if the teacher exists
      Optional<TeacherEnrollment> enrolledTeacher = repo.findById(Long.valueOf(teacherId));
      if (enrolledTeacher.isEmpty()){
          return ResponseEntity.badRequest().body("Teacher not found with id: " + teacherId);
      }
      TeacherEnrollment teacher  = enrolledTeacher.get();

      Optional<Course> listedCourses = courseRepository.findById(Long.valueOf(courseId));
      if(listedCourses.isEmpty()){
          return ResponseEntity.badRequest().body("Course not found with id:"+ courseId);
      }
      Course course = listedCourses.get();
      teacher.getCourse().add(course);
      repo.save(teacher);


      return ResponseEntity.ok("Enrollment successful");
  }

   @PostMapping("/queryByTeacherId")
    @Operation(summary = "Query and list the courses taught by a teacher.")
    public List<Course> getAllCoursesByTeacherId(String id) {
        log.traceEntry("Enter list");
        var retval = repo.getAllCoursesByTeacherId(id);
        log.traceExit("Exit list", retval);
        return retval;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id ) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }
}

