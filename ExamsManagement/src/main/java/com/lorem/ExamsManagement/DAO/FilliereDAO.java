package com.lorem.ExamsManagement.DAO;

import com.lorem.ExamsManagement.model.Filiere;
import com.lorem.ExamsManagement.model.FilliereType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilliereDAO extends JpaRepository<Filiere , Long> {

    Iterable<Filiere> findFiliereByFilliereType(FilliereType type);

    Optional<Filiere> findFiliereById(Long filiereId);

    List<Filiere> findAllById(Iterable<Long> ids);
}
