package com.lorem.ExamsManagement.service;

import com.lorem.ExamsManagement.DAO.EnseignantGroupDAO;
import com.lorem.ExamsManagement.model.EnseignantGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrpEnseignantServiceImpl implements GrpEnseignantService {

    private final EnseignantGroupDAO enseignantGroupDAO;

    public GrpEnseignantServiceImpl(EnseignantGroupDAO enseignantGroupDAO) {
        this.enseignantGroupDAO = enseignantGroupDAO;
    }

    @Override
    public EnseignantGroup saveEnseignantGroup(EnseignantGroup enseignantGroup) {
        return enseignantGroupDAO.save(enseignantGroup);
    }

    @Override
    public Optional<EnseignantGroup> findEnseignantGroupById(Long id) {
        return enseignantGroupDAO.findById(id);
    }

    @Override
    public List<EnseignantGroup> findAll() {
        return enseignantGroupDAO.findAll();
    }

    @Override
    public void deleteEnseignantGroup(Long id) {
        enseignantGroupDAO.deleteById(id);
    }
}