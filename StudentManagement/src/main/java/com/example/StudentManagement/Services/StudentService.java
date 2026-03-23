package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentDetails();

    void addStudent(Student student);

    Boolean deleteStudentById(Long id);

    Boolean updateStudentDetailsById(Student student,Long id);
}
