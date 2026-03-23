package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findCourseDetails();

    void addCourse(Course course);

    Boolean deleteCourseById(Long id);

    Boolean updateCourseDetailsById(Course course, Long id);
}