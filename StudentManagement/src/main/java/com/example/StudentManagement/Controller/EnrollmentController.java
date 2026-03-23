package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Enrollment;
import com.example.StudentManagement.Services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@RequestParam Long studentId,
                                           @RequestParam Long courseId) {
        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{enrollmentId}/grade")
    public ResponseEntity<?> updateGrade(@PathVariable Long enrollmentId,
                                         @RequestParam String grade,
                                         @RequestParam Double marks) {
        try {
            Enrollment enrollment = enrollmentService.updateGrade(enrollmentId, grade, marks);
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{enrollmentId}/drop")
    public ResponseEntity<?> dropCourse(@PathVariable Long enrollmentId) {
        try {
            Enrollment enrollment = enrollmentService.dropCourse(enrollmentId);
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{enrollmentId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long enrollmentId,
                                          @RequestParam String status) {
        try {
            Enrollment enrollment = enrollmentService.updateStatus(enrollmentId, status);
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getCourseEnrollments(courseId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/active")
    public ResponseEntity<List<Enrollment>> getActiveEnrollments(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getActiveEnrollmentsByStudent(studentId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkEnrollment(@RequestParam Long studentId,
                                                   @RequestParam Long courseId) {
        boolean isEnrolled = enrollmentService.isStudentEnrolled(studentId, courseId);
        return new ResponseEntity<>(isEnrolled, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return new ResponseEntity<>("Enrollment deleted successfully", HttpStatus.OK);
    }
}