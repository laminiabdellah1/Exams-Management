package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EnseignantDAO extends JpaRepository<Enseignant, Long> {


    Optional<Enseignant> findEnseignantById(Integer userId);
}
