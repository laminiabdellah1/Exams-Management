package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findDepartmentById(Long id);


    List<Department> findAll();
}
