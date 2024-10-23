package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartementDAO extends JpaRepository<Department , Long> {
    Optional<Department> findDepartementById(int departementId);
}
