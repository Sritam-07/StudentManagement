package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enrollStudent(Long studentId, Long courseId);
    Enrollment updateGrade(Long enrollmentId, String grade, Double marks);
    Enrollment dropCourse(Long enrollmentId);
    Enrollment updateStatus(Long enrollmentId, String status);
    List<Enrollment> getStudentEnrollments(Long studentId);
    List<Enrollment> getCourseEnrollments(Long courseId);
    List<Enrollment> getActiveEnrollmentsByStudent(Long studentId);
    boolean isStudentEnrolled(Long studentId, Long courseId);
    void deleteEnrollment(Long id);
}