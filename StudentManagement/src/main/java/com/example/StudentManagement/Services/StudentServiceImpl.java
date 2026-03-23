package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Department;
import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Repository.DepartmentRepository;
import com.example.StudentManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Student> findStudentDetails() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {

        if (student.getDepartment() != null && student.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(student.getDepartment().getId())
                    .orElse(null);
            student.setDepartment(department);
        }
        studentRepository.save(student);
    }

    @Override
    public Boolean deleteStudentById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateStudentDetailsById(Student updatedStudent, Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setAge(updatedStudent.getAge());
            student.setName(updatedStudent.getName());
            student.setStudentClass(updatedStudent.getStudentClass());

            if (updatedStudent.getDepartment() != null && updatedStudent.getDepartment().getId() != null) {
                Department department = departmentRepository.findById(updatedStudent.getDepartment().getId())
                        .orElse(null);
                student.setDepartment(department);
            }

            studentRepository.save(student);
            return true;
        } else {
            return false;
        }
    }
}