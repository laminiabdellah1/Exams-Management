package com.lorem.ExamsManagement.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Filiere> filiereList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "enseignant_id")
    Enseignant chefDepartement;
    private  String description;

    public Department(){

    }

    public Department(Long id, String name, List<Filiere> filiereList) {
        this.id = id;
        this.name = name;
        this.filiereList = filiereList;
    }

    //Department(String nom,String description, enseignantOptional.get())
    public Department(String nom,String description, Enseignant enseignant){
        this.name = nom;
        this.description = description;
        this.chefDepartement = enseignant;
    }

    public List<Filiere> getFiliereList() {
        return filiereList;
    }

    public void setFiliereList(List<Filiere> filiereList) {
        this.filiereList = filiereList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filiere getFiliere() {
       return filiereList.get(0);
    }
}
