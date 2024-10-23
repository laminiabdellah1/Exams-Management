package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.*;
import com.lorem.ExamsManagement.model.*;
import com.lorem.ExamsManagement.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private  final UserDAO userRepository;
    private  final EnseignantDAO enseignantRepository;
    private  final ExamDAO examenRepository;
    private  final ModuleDAO moduleRepository;
    private  final ElementDAO elementRepository;
    private  final FilliereDAO filiereRepository;
    private  final DepartementDAO departementRepository;
    private  final EnseignantGroupDAO grpEnseignantRepository;


    @Autowired
    public AdminService(UserDAO userRepository, EnseignantDAO enseignantRepository, ExamDAO examenRepository, ModuleDAO moduleRepository, ElementDAO elementRepository, FilliereDAO filiereRepository, DepartementDAO departementRepository, EnseignantGroupDAO grpEnseignantRepository) {
        this.userRepository = userRepository;
        this.enseignantRepository = enseignantRepository;
        this.examenRepository = examenRepository;
        this.moduleRepository = moduleRepository;
        this.elementRepository = elementRepository;
        this.filiereRepository = filiereRepository;
        this.departementRepository = departementRepository;
        this.grpEnseignantRepository = grpEnseignantRepository;
    }


    public ResponseEntity<?> createGrp(List<Integer> userIds, String name) {
        try{
            List<Enseignant> enseignants = new ArrayList<>();
            for (Integer userId : userIds) {
                Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(userId);
                enseignantOptional.ifPresent(enseignants::add);
            }
            EnseignantGroup grp=new EnseignantGroup(name, enseignants);
            grpEnseignantRepository.save(grp);

            return ResponseEntity.ok(grp);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }

    }

    public ResponseEntity<?> createDepatement(String nom, String description, int enseignantId) {
        try {
            Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(enseignantId);
            if (enseignantOptional.isPresent()) {
                Department dp = new Department(nom, description, enseignantOptional.get());
                departementRepository.save(dp);
                return ResponseEntity.ok(dp);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Enseignant not found with ID: " + enseignantId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<?> createFiliere(String nom, int coordonnateurId, int departementId) {
        try {
            Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(coordonnateurId);
            Optional<Department> departementOptional = departementRepository.findDepartementById(departementId);

            if (enseignantOptional.isPresent() && departementOptional.isPresent()) {
                Filiere filiere = new Filiere(nom, enseignantOptional.get(), departementOptional.get());
                filiereRepository.save(filiere);
                return ResponseEntity.ok(filiere);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Enseignant or departement not found with ID: " + coordonnateurId +"   "+departementId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    public  ResponseEntity<?> createExamen(String nom, Semestre semestre, Session sesion, ExamType type, LocalDate date, double heur, Duration durePrevu, Duration dureReel, String anneUniversitaire, int coordonnateurId) {
        try {
            Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(coordonnateurId);

            if (enseignantOptional.isPresent()) {
                Exam examen=new Exam(nom, semestre, sesion,type,date,heur,durePrevu,dureReel,anneUniversitaire,enseignantOptional.get());
                examenRepository.save(examen);
                return ResponseEntity.ok(examen);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Enseignant not found with ID: " + coordonnateurId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    //create module
    public ResponseEntity<?> createModule(String nom, String description, List<Long> filiereIds, int coordonnateurId) {
        try {
            Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(coordonnateurId);
            if (enseignantOptional.isPresent()) {
                List<Filiere> filieres = new ArrayList<>();
                for (Long filiereId : filiereIds) {
                    Optional<Filiere> filiereOptional = filiereRepository.findFiliereById(filiereId);
                    filiereOptional.ifPresent(filieres::add);
                }
                Module module = new Module(nom, description, filieres, enseignantOptional.get());
                moduleRepository.save(module);
                return ResponseEntity.ok(module);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Enseignant not found with ID: " + coordonnateurId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    public ResponseEntity<?> createGrpByDepartement(List<Integer> ids, String nom, int departementId) {
        try {
            Optional<Department> departementOptional = departementRepository.findDepartementById(departementId);
            if (departementOptional.isPresent()) {
                List<Enseignant> enseignants = new ArrayList<>();
                for (Integer userId : ids) {
                    Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(userId);
                    enseignantOptional.ifPresent(enseignants::add);
                }
                EnseignantGroup grp=new EnseignantGroup(nom, enseignants,departementOptional.get());
                grpEnseignantRepository.save(grp);

                return ResponseEntity.ok(grp);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Departement not found with ID: " + departementId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<?> createGrpByFiliere(List<Integer> ids, String nom, Long filiereId) {
        try {
            Optional<Filiere> filiereOptional = filiereRepository.findFiliereById(filiereId);
            if (filiereOptional.isPresent()) {
                List<Enseignant> enseignants = new ArrayList<>();
                for (Integer userId : ids) {
                    Optional<Enseignant> enseignantOptional = enseignantRepository.findEnseignantById(userId);
                    enseignantOptional.ifPresent(enseignants::add);
                }
                EnseignantGroup grp=new EnseignantGroup(nom, enseignants, filiereOptional.get().getDepartement());
                grpEnseignantRepository.save(grp);

                return ResponseEntity.ok(grp);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Filiere not found with ID: " + filiereId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}
