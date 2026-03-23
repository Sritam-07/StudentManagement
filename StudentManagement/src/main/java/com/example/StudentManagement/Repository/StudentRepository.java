package com.example.StudentManagement.Repository;

import com.example.StudentManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByDepartmentId(Long departmentId);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Student findByEmail(@Param("email") String email);

    @Query("SELECT s FROM Student s WHERE s.studentClass = :className")
    List<Student> findByStudentClass(@Param("className") String className);
}