package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Course;
import com.example.StudentManagement.Repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findCourseDetails() {
        return courseRepository.findAll();
    }

    @Override
    public void addCourse(Course course) {
        course.setCourseName(course.getCourseName());
        course.setSem(course.getSem());
        course.setStudent(course.getStudent());
        courseRepository.save(course);
    }

    @Override
    public Boolean deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateCourseDetailsById(Course updatedCourse, Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setCourseName(updatedCourse.getCourseName());
            course.setSem(updatedCourse.getSem());
            course.setStudent(updatedCourse.getStudent());
            courseRepository.save(course);
            return true;
        } else {
            return false;
        }
    }
}