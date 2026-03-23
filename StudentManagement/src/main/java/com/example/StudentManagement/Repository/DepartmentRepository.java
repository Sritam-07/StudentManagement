package com.example.StudentManagement.Repository;

import com.example.StudentManagement.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);

    @Query("SELECT d FROM Department d WHERE d.hod = :hod")
    Department findByHod(@Param("hod") String hod);
}