--PAYMENT METHOD :
INSERT INTO admin (username, password, email, first_name, last_name, phone_number, address, ssn, dob) VALUES ('johndoe', 'password');

INSERT INTO students (id,username, password, email, first_name, last_name, phone_number, address, ssn, dob, gender,  guardian_name, guardian_number) VALUES (1,'johndoe', 'password', 'johndoe@example.com', 'John', 'Doe', 1234567890, '123 Main St', 123456789, '1990-01-01','M','DS',2323);
--INSERT INTO STUDENTS (id,PASSWORD,USERNAME,ADDRESS,DOB,EMAIL,FIRST_NAME,LAST_NAME,PHONE_NUMBER,SSN)
--VALUES(123,'123','123',12,'1999-05-15','Ds','ds','ds',123,123);
--insert into student(id) values (12);
--INSERT INTO payment_method (id,stu_id, payment_date, amount, method, trans_id, remarks)
--VALUES (12,123456, '2023-04-19', 100.0, 'credit_card', '1234567890', 'Payment for tuition fees1');

INSERT INTO payment_method (id,stu_id, payment_date, amount, method, trans_id, remarks)
VALUES (23,1, '2022-05-19', 1244.0, 'credit_card', '123455454567890', 'Payment for tuition fees2');

--INSERT INTO payment_method (id,stu_id, payment_date, amount, method, trans_id, remarks)
--VALUES (44,1236, '2022-04-19', 1244.0, 'credit_card', '123455454567890', 'Payment for tuition fees2');

INSERT INTO courses (dept, num, name) VALUES ('MATH', '101', 'Introduction to Calculus');

INSERT INTO card_details (stu_id, exp_month, exp_year, type, number)
VALUES (1, '12', '2027', 'rupay', '12323232323');

--INSERT INTO card_details (dept, num, name) VALUES ('MATH', '101', 'Introduction to Calculus');

--
--INSERT INTO courses (dept,num,name) VALUES("CSE","101","SE");
--INSERT INTO courses (dept,num,name) VALUES("CSE",102,"ST");
--INSERT INTO courses (id,dept,num,name)  VALUES(153,565,"2","software testing");

--PAYMENT RECORDS :
INSERT INTO payment_record (term,course_fee) VALUES(2020,3500);
--INSERT INTO payment_record (num,course_fee) VALUES(102,4567);
--INSERT INTO payment_record (course_id,course_fee) VALUES(153,3760);