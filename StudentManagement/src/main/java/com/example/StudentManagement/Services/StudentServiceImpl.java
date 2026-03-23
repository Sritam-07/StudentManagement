package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Department;
import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Repository.DepartmentRepository;
import com.example.StudentManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        if (student.getDepartment() != null && student.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(student.getDepartment().getId())
                    .orElse(null);
            student.setDepartment(department);
        }
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(studentDetails.getName());
            existingStudent.setAge(studentDetails.getAge());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setPhoneNumber(studentDetails.getPhoneNumber());
            existingStudent.setStudentClass(studentDetails.getStudentClass());

            if (studentDetails.getDepartment() != null && studentDetails.getDepartment().getId() != null) {
                Department department = departmentRepository.findById(studentDetails.getDepartment().getId())
                        .orElse(null);
                existingStudent.setDepartment(department);
            }

            return studentRepository.save(existingStudent);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Student> getStudentsByDepartment(Long departmentId) {
        return studentRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> getStudentsByClass(String className) {
        return studentRepository.findByStudentClass(className);
    }
}