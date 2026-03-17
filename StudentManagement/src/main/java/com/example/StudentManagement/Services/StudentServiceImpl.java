package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findStudentDetails(){
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        student.setName(student.getName());
        student.setStudentClass(student.getStudentClass());
        student.setAge(student.getAge());
        student.setCourse(student.getCourse());
        studentRepository.save(student);
    }

    @Override
    public Boolean deleteStudentById(Long id) {
       if(studentRepository.existsById(id)){
           return true;
       }else{
           return false;
       }
    }
}
