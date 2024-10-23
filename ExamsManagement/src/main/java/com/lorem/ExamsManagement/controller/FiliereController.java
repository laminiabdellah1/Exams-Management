package com.lorem.ExamsManagement.controller;


import com.lorem.ExamsManagement.model.Department;
import com.lorem.ExamsManagement.model.Filiere;
import com.lorem.ExamsManagement.model.FilliereType;
import com.lorem.ExamsManagement.model.Module;
import com.lorem.ExamsManagement.service.DepartmentService;
import com.lorem.ExamsManagement.service.FiliereService;
import com.lorem.ExamsManagement.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filiere")
@CrossOrigin(origins = "http://localhost:5173")
public class FiliereController {

    @Autowired
    FiliereService filiereService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ModuleService moduleService;

    @PostMapping("/add")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere){
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFiliere(@RequestParam Long id){
        filiereService.deleteFiliere(filiereService.findFiliereById(id).get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Filiere>> getAllFilieres(){
        return ResponseEntity.ok(filiereService.retrieveAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.findFiliereById(id).get());
    }

    @GetMapping("/byType")
    public ResponseEntity<Iterable<Filiere>> getFiliereByType(@RequestParam String type){
        return ResponseEntity.ok(filiereService.findFilliereByType(FilliereType.valueOf(type)));
    }


    @PostMapping("/addDepartment")
    public ResponseEntity<Filiere> addDepartment(@RequestParam Long idFiliere, @RequestParam Long idDepartment){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        Department department = departmentService.findDepartmentById(idDepartment).get();
        filiere.setDepartment(department);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @PostMapping("/removeDepartment")
    public ResponseEntity<Filiere> removeDepartment(@RequestParam Long idFiliere){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        filiere.setDepartment(null);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }

    @PostMapping("/addModule")
    public ResponseEntity<Filiere> addModule(@RequestParam Long idFiliere, @RequestParam Long idModule){
        Filiere filiere = filiereService.findFiliereById(idFiliere).get();
        Module module = moduleService.findModuleById(idModule).get();
        filiere.getModules().add(module);
        return ResponseEntity.ok(filiereService.saveFiliere(filiere));
    }




    /***
     *     @GetMapping("/valid-levels/{type}")
     *     public ResponseEntity<List<Niveau>> getValidLevels(@PathVariable String type) {
     *         FilliereType typeFiliere;
     *         try {
     *             typeFiliere = FilliereType.valueOf(type.toUpperCase());
     *         } catch (IllegalArgumentException e) {
     *             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     *         }
     *         Filiere filiere = new Filiere();
     *         filiere.setFilliereType(typeFiliere);
     *         return ResponseEntity.ok(filiere.getValidLevels());
     *     }
     */
}
