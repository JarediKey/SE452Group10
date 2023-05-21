-- Insert courses --
INSERT INTO courses (dept, num, name, academic_year, quarter) VALUES ('MATH', '101', 'Introduction to Calculus', 2023, 'FALL');
INSERT INTO courses (dept, num, name, academic_year, quarter) VALUES ('MATH', '201', 'Multivariable Calculus', 2023, 'FALL');
INSERT INTO courses (dept, num, name, academic_year, quarter) VALUES ('CS', '101', 'Introduction to Computer Science', 2023, 'FALL');
INSERT INTO courses (dept, num, name, academic_year, quarter) VALUES ('CS', '201', 'Data Structures and Algorithms', 2023, 'FALL');
INSERT INTO courses (dept, num, name, academic_year, quarter) VALUES ('ENG', '101', 'Introduction to Literature', 2023, 'FALL');

-- Insert students --
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('johndoe', 'password', 'johndoe@example.com', 'John', 'Doe', 1234567890, '123 Main St', 123456789, '1990-01-01', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('janedoe', 'password', 'janedoe@example.com', 'Jane', 'Doe', 1234567891, '124 Main St', 123456788, '1991-02-02', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('bobsmith', 'password', 'bobsmith@example.com', 'Bob', 'Smith', 1234567892, '125 Main St', 123456787, '1992-03-03', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('jennysmith', 'password', 'jennysmith@example.com', 'Jenny', 'Smith', 1234567893, '126 Main St', 123456786, '1993-04-04', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('tomhanks', 'password', 'tomhanks@example.com', 'Tom', 'Hanks', 1234567894, '127 Main St', 123456785, '1994-05-05', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('angelinajolie', 'password', 'angelinajolie@example.com', 'Angelina', 'Jolie', 1234567895, '128 Main St', 123456784, '1995-06-06', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('bradpitt', 'password', 'bradpitt@example.com', 'Brad', 'Pitt', 1234567896, '129 Main St', 123456783, '1996-07-07', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('emmaroberts', 'password', 'emmaroberts@example.com', 'Emma', 'Roberts', 1234567897, '130 Main St', 123456782, '1997-08-08', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('leonardodicaprio', 'password', 'leonardodicaprio@example.com', 'Leonardo', 'DiCaprio', 1234567898, '131 Main St', 123456781, '1998-09-09', 'M', '', 0);
INSERT INTO students (username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender, guardian_name, guardian_number) VALUES ('sandrabullock', 'password', 'sandrabullock@example.com', 'Sandra', 'Bullock', 1234567899, '132 Main St', 123456780, '1999-10-10', 'M', '', 0);

-- Insert GPA --
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 85, 3.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 92, 3.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 78, 2.5);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 88, 3.3);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 95, 4.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 89, 3.3);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 91, 3.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 82, 2.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 95, 4.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 75, 2.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 85, 3.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 90, 3.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 80, 2.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 85, 3.0);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 92, 3.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 90, 3.7);
INSERT INTO gpa (course_id, student_id, grade, gpa) VALUES ((SELECT id FROM courses ORDER BY RAND() LIMIT 1), (SELECT id FROM students ORDER BY RAND() LIMIT 1), 88, 3.3);