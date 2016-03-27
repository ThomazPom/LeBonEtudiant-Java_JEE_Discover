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
import javax.persistence.OneToMany;

/**
 *
 * @author Thomas
 */
@Entity
public class Region {
    @OneToMany(mappedBy = "region")
    private List<Departement> departements;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Region() {
    }

    public Region(String libelle) {
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String libelle;

    /**
     * @return the departements
     */
    public List<Departement> getDepartements() {
        return departements;
    }

    /**
     * @param departements the departements to set
     */
    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
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
}
