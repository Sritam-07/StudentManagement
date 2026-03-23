package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Entity.Department;
import com.example.StudentManagement.Services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            Department createdDepartment = departmentService.createDepartment(department);
            return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating department: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        if (updatedDepartment != null) {
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        }
        return new ResponseEntity<>("Department not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted) {
            return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Department not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Department>> searchDepartments(@RequestParam String name) {
        List<Department> departments = departmentService.searchDepartmentsByName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}