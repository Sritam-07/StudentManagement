package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course createCourse(Course course);
    Course updateCourse(Long id, Course course);
    boolean deleteCourse(Long id);
    List<Course> getCoursesBySemester(String semester);
    List<Course> searchCoursesByName(String name);
}