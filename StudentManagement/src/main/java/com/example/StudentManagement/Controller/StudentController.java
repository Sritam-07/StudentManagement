package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student>student=studentService.findStudentDetails();
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }
    @PostMapping("/admin/setDetails")
    public ResponseEntity<String> addStudents(@RequestBody Student student){
        studentService.addStudent(student);
        return new ResponseEntity<>("Student added Successfully ",HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id){
            boolean deleted = studentService.deleteStudentById(id);
            if(deleted){
                return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Id not found",HttpStatus.NOT_FOUND);
            }
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<String> updateStudentDetailsById(@PathVariable Long id ,@RequestBody Student student){
        boolean updated = studentService.updateStudentDetailsById(student,id);
        if (updated){
            return new ResponseEntity<>("The student Details Updated",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The student with the id not found",HttpStatus.NOT_FOUND);
        }
    }
}
