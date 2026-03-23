package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Course;
import com.example.StudentManagement.Entity.Enrollment;
import com.example.StudentManagement.Entity.Student;
import com.example.StudentManagement.Repository.CourseRepository;
import com.example.StudentManagement.Repository.EnrollmentRepository;
import com.example.StudentManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // Check if already enrolled
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment updateGrade(Long enrollmentId, String grade, Double marks) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + enrollmentId));

        enrollment.setGrade(grade);
        enrollment.setMarks(marks);

        if (grade != null && !grade.isEmpty()) {
            enrollment.setStatus("COMPLETED");
        }

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment dropCourse(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + enrollmentId));

        enrollment.setStatus("DROPPED");
        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment updateStatus(Long enrollmentId, String status) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + enrollmentId));

        enrollment.setStatus(status);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getCourseEnrollments(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public List<Enrollment> getActiveEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentIdAndStatus(studentId, "ENROLLED");
    }

    @Override
    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    @Transactional
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}