package com.example.StudentManagement.Repository;

import com.example.StudentManagement.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
    List<Course> findBySemester(String semester);

    @Query("SELECT c FROM Course c WHERE c.courseCode = :courseCode")
    Course findByCourseCode(@Param("courseCode") String courseCode);

    @Query("SELECT c FROM Course c WHERE c.instructor LIKE %:instructor%")
    List<Course> findByInstructorContaining(@Param("instructor") String instructor);
}