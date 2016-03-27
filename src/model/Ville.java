/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Thomas
 */
@Entity
public class Ville {
    @OneToMany(mappedBy = "ville")
    private List<Etablissement> etablissements;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Ville() {
    }

    public Ville(String libelle, Departement departement) {
        this.libelle = libelle;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String libelle;
    @ManyToOne
    private Departement departement;

    /**
     * @return the etablissements
     */
    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    /**
     * @param etablissements the etablissements to set
     */
    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * @param departement the departement to set
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
