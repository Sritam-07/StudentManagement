package com.example.StudentManagement.Services;

import com.example.StudentManagement.Entity.Department;
import com.example.StudentManagement.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);
        if (existingDepartment != null) {
            existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
            existingDepartment.setLocation(departmentDetails.getLocation());
            existingDepartment.setHod(departmentDetails.getHod());
            existingDepartment.setContactNumber(departmentDetails.getContactNumber());
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByDepartmentNameContainingIgnoreCase(name);
    }
}