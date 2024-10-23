package com.lorem.ExamsManagement.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enseignant_group")
public class EnseignantGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nom;

    @OneToMany(mappedBy = "enseignantGroup")
    private List<Enseignant> enseignantList;

    @ManyToOne
    @JoinColumn(name="filiere_id")
    private Filiere filiere;





    public EnseignantGroup(){
        this.enseignantList = new ArrayList<>();
    }

    public EnseignantGroup(String name, List<Enseignant> enseignants) {
        this.nom = name;
        this.enseignantList = enseignants;

    }

    public EnseignantGroup(String nom, List<Enseignant> enseignants, Department department) {
        this.nom = nom;
        this.enseignantList = enseignants;
        this.filiere = department.getFiliere();


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Enseignant> getEnseignantList() {
        return enseignantList;
    }

    public void setEnseignantList(List<Enseignant> enseignantList) {
        this.enseignantList = enseignantList;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
}
