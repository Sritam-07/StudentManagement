package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Course;
import com.example.StudentManagement.Services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating course: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        if (updatedCourse != null) {
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Course not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Course not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<Course>> getCoursesBySemester(@PathVariable String semester) {
        List<Course> courses = courseService.getCoursesBySemester(semester);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}