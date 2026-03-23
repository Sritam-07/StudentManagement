package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Department addDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    Boolean deleteDepartment(Long id);
    Department findDepartmentById(Long id);
}