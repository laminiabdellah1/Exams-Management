package com.lorem.ExamsManagement.controller;

import com.lorem.ExamsManagement.Exeption.CustomException;
import com.lorem.ExamsManagement.model.Department;
import com.lorem.ExamsManagement.service.AdminService;
import com.lorem.ExamsManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departement")
public class DepartmentController {
      @Autowired
      private  final AdminService adminService;

      private final DepartmentService departmentService;

      public DepartmentController(AdminService adminService, DepartmentService departmentService) {
        this.adminService = adminService;
        this.departmentService = departmentService;
      }

      @GetMapping("/getAllDepartements")
      public ResponseEntity<?> getAllDepartements() {
          try {
              List<Department> departementsList = departmentService.findAll();
              if (!departementsList.isEmpty()) {

                  return ResponseEntity.ok(departementsList);
              } else {
                  throw new CustomException("list est vide il nya aucun user");
              }
          } catch (UsernameNotFoundException e) {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
          } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body("An error occurred: " + e.getMessage());
          }
      }

    @PostMapping("/createDepartement")
    public ResponseEntity<ResponseEntity<?>> createDepatement(@RequestBody Map<String, Object> requestBody) {
        String nom = (String) requestBody.get("nom");
        String description = (String) requestBody.get("description");
        int enseignant_id = (int) requestBody.get("enseignant_id");


        return ResponseEntity.ok(adminService.createDepatement(nom,description,enseignant_id));
    }


}
