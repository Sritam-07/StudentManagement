package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Course;
import com.example.StudentManagement.Services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.findCourseDetails();
        return new ResponseEntity<>(courses, HttpStatus.FOUND);
    }

    @PostMapping("/admin/setDetails")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return new ResponseEntity<>("Course added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long id){
        boolean deleted = courseService.deleteCourseById(id);
        if(deleted){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<String> updateCourseDetailsById(@PathVariable Long id, @RequestBody Course course){
        boolean updated = courseService.updateCourseDetailsById(course, id);
        if (updated){
            return new ResponseEntity<>("The course Details Updated", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The course with the id not found", HttpStatus.NOT_FOUND);
        }
    }
}