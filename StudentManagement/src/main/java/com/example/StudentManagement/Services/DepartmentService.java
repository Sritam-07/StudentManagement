package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    Department createDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    boolean deleteDepartment(Long id);
    List<Department> searchDepartmentsByName(String name);
}