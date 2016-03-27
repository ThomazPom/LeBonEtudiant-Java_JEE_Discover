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
public class Departement {
    @OneToMany(mappedBy = "departement")
    private List<Ville> villes;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Departement() {
    }

    public Departement(String libelle, Region region) {
        this.libelle = libelle;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   private String libelle;
    @ManyToOne
   private Region region;

    /**
     * @return the villes
     */
    public List<Ville> getVilles() {
        return villes;
    }

    /**
     * @param villes the villes to set
     */
    public void setVilles(List<Ville> villes) {
        this.villes = villes;
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
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(Region region) {
        this.region = region;
    }
    
}
