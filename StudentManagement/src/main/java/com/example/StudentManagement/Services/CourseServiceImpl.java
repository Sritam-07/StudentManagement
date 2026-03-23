package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Course;
import com.example.StudentManagement.Repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Long id, Course courseDetails) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setCourseName(courseDetails.getCourseName());
            existingCourse.setCourseCode(courseDetails.getCourseCode());
            existingCourse.setCredits(courseDetails.getCredits());
            existingCourse.setSemester(courseDetails.getSemester());
            existingCourse.setInstructor(courseDetails.getInstructor());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getCoursesBySemester(String semester) {
        return courseRepository.findBySemester(semester);
    }

    @Override
    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByCourseNameContainingIgnoreCase(name);
    }
}