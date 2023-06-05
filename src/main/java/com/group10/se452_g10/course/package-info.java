/**
 * The classes in course package is all about course and GPA operations.
 * @author Jared Wang
 *
 * =====Implementation=====
 * 1. Defined an entity class named Course to represent course information. The class is annotated with @Entity
 *  to indicate it as an entity and establishes relationships with other entity classes (such as the association with GPA).
 * 2. Defined an entity class named GPA to represent GPA records for students in courses. The class is annotated
 *  with @Entity to indicate it as an entity and establishes relationships with other entity classes (such as Course and Student).
 * 3. Created a repository interface named CourseRepository that extends JpaRepository to define database operations
 *  for the Course entity, such as querying, saving, and deleting.
 * 4. Created a repository interface named GpaRepository that extends JpaRepository to define database operations
 *  for the GPA entity, such as querying, saving, and deleting.
 * 5. Implemented multiple HTTP endpoint methods in the CourseService class, such as list(), save(), search(),
 *  and delete(), to handle course-related operations.
 * 6. Implemented multiple HTTP endpoint methods in the GpaService class, such as list(), queryByStudentId(),
 *  queryByCourseId(), save(), and delete(), to handle GPA-related operations.
 * 7. In the CourseServiceTest test class, wrote unit tests for each endpoint method, simulating HTTP requests
 *  and verifying response results to test and validate the functionality.
 * 8. In the GpaServiceTest test class, wrote unit tests for each endpoint method, simulating HTTP requests and
 *  verifying response results to test and validate the functionality.
 * 9. Utilized various annotations and configurations, such as @AutoConfigureMockMvc to configure the MockMvc object,
 *   @Transactional for transaction management, @Sql to execute SQL scripts for data initialization, @WithMockUser to
 *  simulate user authentication, @ActiveProfiles to specify the active profile, and @TestInstance to configure the test class's instance lifecycle.
 *
 * =====Lessons Learned=====
 * 1. Entity classes and relationships:
 *      Use the @Entity annotation to define entity classes and use annotations (such as @ManyToOne, @JoinColumn)
 *      to establish relationships between entity classes, such as the relationship between Course and GPA.
 * 2. JPA Repository:
 *      Use the JpaRepository interface to define methods for database operations.
 *      By extending this interface and passing the entity class and primary key type,
 *      database operations like CRUD can be easily performed.
 * 3. Custom queries:
 *      Define custom queries using the @Query annotation. Use named parameters (such as @Param)
 *      to pass parameter values and invoke these custom query methods within the repository.
 * 4. Spring MVC controllers:
 *      Annotate the controller class with @RestController and use the @RequestMapping annotation
 *      to define URL mappings for handling HTTP requests and responses.
 * 5. MockMvc testing:
 *      Configure MockMvc for controller layer unit testing using the @AutoConfigureMockMvc annotation.
 *      Simulate HTTP requests and verify the response results.
 * 6. Security authentication:
 *      Configure simulated users with specific authorities using the @WithMockUser annotation for testing interface security.
 * 7. Data initialization:
 *      Use the @Sql annotation to execute SQL scripts before testing to initialize test data and ensure the test environment is prepared.
 * 8. Configuration files:
 *      Specify the active profile using the @ActiveProfiles annotation. For example,
 *      use the "test" configuration file for testing purposes to separate configurations for different environments.
 * 9. Unit test lifecycle:
 *      Configure the test class's instance lifecycle with the @TestInstance(TestInstance.Lifecycle.PER_CLASS)
 *      annotation to ensure state isolation between test methods.
 * 10.Object mapping:
 *      Use the ObjectMapper for object serialization and deserialization, converting objects to JSON format or vice versa.
 */
package com.group10.se452_g10.course;