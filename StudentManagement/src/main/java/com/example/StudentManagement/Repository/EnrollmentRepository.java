package com.example.StudentManagement.Repository;

import com.example.StudentManagement.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findByStudentIdAndStatus(Long studentId, String status);
    List<Enrollment> findByCourseIdAndStatus(Long courseId, String status);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.course.id = :courseId")
    Enrollment findByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId AND e.grade IS NULL")
    List<Enrollment> findPendingGradesByStudentId(@Param("studentId") Long studentId);
}