package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating student: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Student not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Student>> getStudentsByDepartment(@PathVariable Long departmentId) {
        List<Student> students = studentService.getStudentsByDepartment(departmentId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/class/{className}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable String className) {
        List<Student> students = studentService.getStudentsByClass(className);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}