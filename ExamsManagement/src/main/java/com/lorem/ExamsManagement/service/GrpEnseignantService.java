package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.model.EnseignantGroup;

import java.util.List;
import java.util.Optional;

public interface GrpEnseignantService {
    EnseignantGroup saveEnseignantGroup(EnseignantGroup enseignantGroup);
    Optional<EnseignantGroup> findEnseignantGroupById(Long id);
    List<EnseignantGroup> findAll();
    void deleteEnseignantGroup(Long id);


}