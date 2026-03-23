package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    boolean deleteStudent(Long id);
    List<Student> getStudentsByDepartment(Long departmentId);
    List<Student> searchStudentsByName(String name);
    List<Student> getStudentsByClass(String className);
}