/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
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
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne
    private Utilisateur Proprietaire;
    private String Titre;
    private String numeroOverride;
    private String emailOverride;
    private String Description;
    private boolean active;
    public Annonce() {
    }

    
    public Annonce(Utilisateur Proprietaire, String Titre, String numeroOverride, String emailOverride, String Description, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        
        this.Proprietaire = Proprietaire;
        this.Titre = Titre;
        this.numeroOverride = numeroOverride;
        this.emailOverride = emailOverride;
        this.Description = Description;
        this.active = active;
        this.categories = categories;
        this.etablissements = etablissements;
        if(numeroOverride.isEmpty()){
            numeroOverride=Proprietaire.getNumtel();
        }
        if(emailOverride.isEmpty())
        {
            
        }
    }
    
    
    @ManyToMany
    private List<Categorie> categories;
    
    @ManyToMany
    private List<Etablissement> etablissements;

    /**
     * @return the Proprietaire
     */
    public Utilisateur getProprietaire() {
        return Proprietaire;
    }

    /**
     * @return the Titre
     */
    public String getTitre() {
        return Titre;
    }

    /**
     * @param Titre the Titre to set
     */
    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    /**
     * @return the numeroOverride
     */
    public String getNumeroOverride() {
        return numeroOverride;
    }

    /**
     * @param numeroOverride the numeroOverride to set
     */
    public void setNumeroOverride(String numeroOverride) {
        this.numeroOverride = numeroOverride;
    }

    /**
     * @return the emailOverride
     */
    public String getEmailOverride() {
        return emailOverride;
    }

    /**
     * @param emailOverride the emailOverride to set
     */
    public void setEmailOverride(String emailOverride) {
        this.emailOverride = emailOverride;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the categories
     */
    public List<Categorie> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

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
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
