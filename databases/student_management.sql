-- 创建数据库
CREATE DATABASE student_management;

-- 选择数据库
USE student_management;

-- 创建学生表
CREATE TABLE student (
                         student_id INT PRIMARY KEY AUTO_INCREMENT,  -- 学生ID，主键，自增
                         name VARCHAR(100),  -- 学生姓名
                         email VARCHAR(100),  -- 学生邮箱
                         phone VARCHAR(20),  -- 学生电话
                         dob DATE,  -- 出生日期
                         address TEXT,  -- 地址
                         enrollment_date DATE  -- 入学日期
);

-- 提交学生表创建
COMMIT;

-- 创建课程表
CREATE TABLE course (
                        course_id INT PRIMARY KEY AUTO_INCREMENT,  -- 课程ID，主键，自增
                        course_name VARCHAR(100),  -- 课程名称
                        credits INT,  -- 学分
                        instructor_id INT  -- 教师ID，外键
);

-- 提交课程表创建
COMMIT;

-- 创建教师表
CREATE TABLE instructor (
                            instructor_id INT PRIMARY KEY AUTO_INCREMENT,  -- 教师ID，主键，自增
                            name VARCHAR(100),  -- 教师姓名
                            email VARCHAR(100),  -- 教师邮箱
                            phone VARCHAR(20)  -- 教师电话
);

-- 提交教师表创建
COMMIT;

-- 创建选课表
CREATE TABLE enrollment (
                            enrollment_id INT PRIMARY KEY AUTO_INCREMENT,  -- 选课ID，主键，自增
                            student_id INT,  -- 学生ID，外键
                            course_id INT,  -- 课程ID，外键
                            grade DECIMAL(5,2)  -- 成绩，保留两位小数
);

-- 提交选课表创建
COMMIT;

-- 创建考勤表
CREATE TABLE attendance (
                            attendance_id INT PRIMARY KEY AUTO_INCREMENT,  -- 考勤ID，主键，自增
                            student_id INT,  -- 学生ID，外键
                            course_id INT,  -- 课程ID，外键
                            date DATE,  -- 考勤日期
                            status ENUM('Present', 'Absent')  -- 考勤状态（出勤/缺席）
);

-- 提交考勤表创建
COMMIT;
