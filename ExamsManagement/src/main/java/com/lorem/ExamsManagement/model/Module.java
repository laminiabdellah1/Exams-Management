package com.lorem.ExamsManagement.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long id;

    private String title;


    String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "modules")
    private List<Filiere> filieres;



    @JsonManagedReference(value="module-elements")
    @OneToMany(mappedBy = "module")
    private List<ElementPedagogique> elementsPedagogiques;


    @JsonBackReference(value="module-coordinator")
    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Enseignant coordinator;




    public Module(){
        this.elementsPedagogiques = new ArrayList<>();
        this.filieres = new ArrayList<>();
    }

    public Module(Long id, List<Filiere> filieres, List<ElementPedagogique> elementsPedagogiques, Enseignant coordinator, String title) {
        this.id = id;
        this.filieres = filieres;
        this.elementsPedagogiques = elementsPedagogiques;
        this.coordinator = coordinator;
        this.title = title;
    }

    public Module(String nom, String description, List<Filiere> filiere) {
        this.title = nom;
        this.description = description;
        this.filieres = filiere;

    }

    public Module(String title, String description, Filiere filiere){
        this.title = title;
        this.description = description;
        this.filieres = new ArrayList<>();
        this.filieres.add(filiere);
    }

    public Module(String title, String description, Enseignant enseignant){
        this.title = title;
        this.description = description;
        this.coordinator = enseignant;
    }


    public Module(String title, Enseignant enseignant){
        this.title = title;
        this.coordinator = enseignant;
    }

    public Module(String nom, String description, List<Filiere> filieres, Enseignant enseignant) {
        this.title = nom;
        this.description = description;
        this.filieres = filieres;
        this.coordinator = enseignant;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filieres=" + filieres +
                ", elementsPedagogiques=" + elementsPedagogiques +
                ", coordinator=" + coordinator +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enseignant getCoordinator() {
        return coordinator;
    }


    public void setCoordinator(Enseignant coordinator) {
        this.coordinator = coordinator;
    }

    public List<Filiere> getFilieres() {
        return filieres;
    }

    public void setFilieres(List<Filiere> filieres) {
        this.filieres = filieres;
    }

    public List<ElementPedagogique> getElementsPedagogiques() {
        return elementsPedagogiques;
    }

    public void setElementsPedagogiques(List<ElementPedagogique> elementsPedagogiques) {
        this.elementsPedagogiques = elementsPedagogiques;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void removeCoordinator() {
        // Hypothetical cleanup operations
        //this.coordinator.notifyRemovalFromModule(this);
        this.coordinator = null;
       //updateCoordinatorCaches();
        //triggerCoordinatorRemovalBusinessLogic();
    }


    public void addElement(ElementPedagogique elementPedagogique) {
        this.elementsPedagogiques.add(elementPedagogique);
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getFiliereIds() {
        List<Long> filiereIds = new ArrayList<>();
        for(Filiere filiere : this.filieres){
            filiereIds.add(filiere.getId());
        }
        return filiereIds;
    }

    public int getCoordonnateurId() {
        return Math.toIntExact(this.coordinator.getId());
    }
}
