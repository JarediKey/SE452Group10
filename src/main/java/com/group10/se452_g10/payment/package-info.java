/**
 *   --LESSONS LEARNED--
 *
 * 1)Created a spring boot project along with the dependencies like h2 database, log4j, etc.,
 * 2)Learned to handle version control system and done few sessions on code merging, rebase, push , fetch etc.,
 *   on both individual branch and main branch.,
 * 3)Learned about build.gradle, dependencies, modules inside it.
 * 4)Learned about logging system and it’s features.
 * 5)Created database tables with help of java files and setted up primary key, foreign key few other constraints with help of other annotations.
 * 6)Created repositories which extends JPA Repository to access inbuilt methods of database to play around it.
 * 7)Created Rest Controller for API Calls with help of Getmapping, PostMapping etc.,
 * 8)Created unit tests for tables created.
 *
 *    --Data Entity: payment_method, payment_record
 *
 * 1)Created tables payment_method and payment_record.
 * 2)payment_method table will maintain the records of all the fee payments along with student_id
 *   and the amount paid and so on. Calculate the fee for students based on the enrollment of courses and more.
 * 3)payment_record table will maintain the records of all the course_id and fees for that particular course.
 *
 *
 *    --UNIT TESTS:
 *
 * Covered below operations for both the tables which means main functionality is working fine.
 * 1) Records save
 * 2) Records delete
 * 3) Records update.
 * 4) Null value verification
 * 5) unique value verification
 * 6) Records view
 *
 *  --Documentation on features that were not covered in class(persistence and non-persistence)
 *
 *  1)Created attributes with different annotations for different constraints such as non-null, unique, column, temporal etc.,
 *  2)Implemented relation between two different tables using foreign key concept (@JoinColumn, @ManyToOne annotation).
 *      21)student_id in payment_method is foreign key which refers from student(student_id)
 *      22)course_id in payment_record also a foreign key which refers from course(course_id)(Yet to implement)
 *  3)Every test case will take care about the dependency record in other tables.
 *  3)Created  a different enum class 'PaymentMethodRequest' for handling the method type of payments
 *    like credit card, debit card, etc.,
 *  4)Updated logging features in application-dev.yml for pattern of lines in both console and log file.
 *  5)logs will be rolled on daily basis.
 *  6)Implemented '@TestMethodOrder' annotation to run tests in specific order.
 *  7)Enabled caching for entire application using '@EnableCaching' annotation.
 *
 *
 *  Note: Above information will be updated, as the project moves on.
 *
 * @author Srinivas Dasari
 *
 */

package com.group10.se452_g10.payment;