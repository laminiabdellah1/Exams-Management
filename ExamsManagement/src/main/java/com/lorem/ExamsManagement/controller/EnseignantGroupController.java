package com.lorem.ExamsManagement.controller;

import com.lorem.ExamsManagement.model.EnseignantGroup;
import com.lorem.ExamsManagement.service.AdminService;
import com.lorem.ExamsManagement.service.GrpEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enseignantGroup")
public class EnseignantGroupController {

    @Autowired
    private AdminService adminService;

    private final GrpEnseignantService enseignantGroupService;


    @Autowired
    public EnseignantGroupController(GrpEnseignantService enseignantGroupService) {
        this.enseignantGroupService = enseignantGroupService;
    }

    @GetMapping("/getAllGroupeEnseignants")
    public ResponseEntity<List<EnseignantGroup>> getAllEnseignantGroups() {
        return ResponseEntity.ok(enseignantGroupService.findAll());
    }
    @PostMapping("/createGrpEnseignant")
    public ResponseEntity<ResponseEntity<?>> createGrp(@RequestBody Map<String, Object> requestBody) {
        List<Integer> ids = (List<Integer>) requestBody.get("ids");
        String nom = (String) requestBody.get("nom");
        return ResponseEntity.ok(adminService.createGrp(ids,nom));
    }

    //create groupe par departement
    @PostMapping("/createGrpEnseignantByDepartement")
    public ResponseEntity<ResponseEntity<?>> createGrpByDepartement(@RequestBody Map<String, Object> requestBody) {
        List<Integer> ids = (List<Integer>) requestBody.get("ids");
        String nom = (String) requestBody.get("nom");
        int departement_id = (int) requestBody.get("departement_id");
        return ResponseEntity.ok(adminService.createGrpByDepartement(ids,nom,departement_id));
    }

    //create groupe par filiere
    @PostMapping("/crea teGrpEnseignantByFiliere")
    public ResponseEntity<ResponseEntity<?>> createGrpByFiliere(@RequestBody Map<String, Object> requestBody) {
        List<Integer> ids = (List<Integer>) requestBody.get("ids");
        String nom = (String) requestBody.get("nom");
        Long filiere_id = (Long) requestBody.get("filiere_id");
        return ResponseEntity.ok(adminService.createGrpByFiliere(ids,nom,filiere_id));
    }

}