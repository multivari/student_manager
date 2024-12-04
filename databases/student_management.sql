create database student_management;
use student_management;
CREATE TABLE student (
                         student_id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100),
                         email VARCHAR(100),
                         phone VARCHAR(20),
                         dob DATE,
                         address TEXT,
                         enrollment_date DATE
);

CREATE TABLE course (
                        course_id INT PRIMARY KEY AUTO_INCREMENT,
                        course_name VARCHAR(100),
                        credits INT,
                        instructor_id INT
);

CREATE TABLE instructor (
                            instructor_id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(100),
                            email VARCHAR(100),
                            phone VARCHAR(20)
);

CREATE TABLE enrollment (
                            enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id INT,
                            course_id INT,
                            grade DECIMAL(5,2)
);

CREATE TABLE attendance (
                            attendance_id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id INT,
                            course_id INT,
                            date DATE,
                            status ENUM('Present', 'Absent')
);
