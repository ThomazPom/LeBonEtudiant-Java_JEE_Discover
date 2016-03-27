/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Thomas
 */

@Entity
public class Etablissement {

    public Etablissement() {
    }

    public Etablissement(String nom,String sigle, String adresse,String codepostal,   Ville ville, double LongitudeX, double LatitudeY) {
        this.annonces = new ArrayList<Annonce>() ;
        this.id = id;
        this.codepostal = codepostal;
        this.nom = nom;
        this.sigle = sigle;
        this.adresse = adresse;
        this.ville = ville;
        this.LongitudeX = Math.round(LongitudeX*100000)/100000.00;
        this.LatitudeY = Math.round(LatitudeY*100000)/100000.00;
      
    }

    @ManyToMany(mappedBy = "etablissements")
    private List<Annonce> annonces;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codepostal;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String nom;
    private String sigle;
    private String adresse;
    @ManyToOne
    private Ville ville;
    private double LongitudeX;
    private double LatitudeY;

    /**
     * @return the annonces
     */
    public List<Annonce> getAnnonces() {
        return annonces;
    }

    /**
     * @param annonces the annonces to set
     */
    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * @param sigle the sigle to set
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the ville
     */
    public Ville getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    /**
     * @return the LongitudeX
     */
    public double getLongitudeX() {
        return LongitudeX;
    }

    /**
     * @param LongitudeX the LongitudeX to set
     */
    public void setLongitudeX(double LongitudeX) {
        this.LongitudeX = LongitudeX;
    }

    /**
     * @return the LatitudeY
     */
    public double getLatitudeY() {
        return LatitudeY;
    }

    /**
     * @param LatitudeY the LatitudeY to set
     */
    public void setLatitudeY(double LatitudeY) {
        this.LatitudeY = LatitudeY;
    }
    
}
