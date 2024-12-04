package com.student.manager.mapper;


import com.student.manager.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface StudentMapper {

    // 根据ID查询学生
    Student selectStudentById(Long studentId);

    // 查询所有学生
    List<Student> selectAllStudents();

    // 根据条件查询学生
    List<Student> selectStudentsByCondition(@Param("name") String name,
                                             @Param("email") String email,
                                             @Param("phone") String phone,
                                             @Param("dob") String dob,
                                             @Param("address") String address);

    // 插入学生
    void insertStudent(Student student);

    // 更新学生
    void updateStudent(Student student);

    // 删除学生
    void deleteStudent(Long studentId);
}
