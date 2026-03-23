package com.example.StudentManagement.Repository;

import com.example.StudentManagement.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
