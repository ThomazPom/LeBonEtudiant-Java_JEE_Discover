/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

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
    private String Titre="";
    private int prix=0;
    private String numeroOverride ="";
    private String emailOverride ="";
    @Lob
    private String Description="";
    @Temporal(DATE)
    private Date datePublication;
    @Temporal(DATE)
    private Date dateFin;
    private boolean active;
    private boolean typeVente;
    

    public Annonce() {
    }

    public Annonce(Utilisateur Proprietaire, String Titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements, boolean type) {

        this.Proprietaire = Proprietaire;
        
        this.Titre = Titre;
        if (!numeroOverride.isEmpty()) {
            if (!numeroOverride.equals(Proprietaire.getNumtel())) {
                this.numeroOverride = numeroOverride;
            }
        } else this.numeroOverride = Proprietaire.getNumtel();
        
        if (!emailOverride.isEmpty()) {
            if (!emailOverride.equals(Proprietaire.getLogin())) {
                setEmailOverride(emailOverride);
            }
        } else this.emailOverride = Proprietaire.getLogin();
        
        this.prix=prix;
        this.Description = Description;
        this.active = active;
        this.datePublication = new Date();
        this.dateFin = dateFin;
        this.categories = categories;
        this.etablissements = etablissements;
        this.typeVente = type;
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
        if (numeroOverride.isEmpty()) {
            return Proprietaire.getNumtel();
        }
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
        if (emailOverride.isEmpty()) {
            return Proprietaire.getLogin();
        }
        return emailOverride;
    }

    /**
     * @param emailOverride the emailOverride to set
     */
    public void setEmailOverride(String emailOverride) {
        if (!util.validator.validateEmail(emailOverride)) {
            this.emailOverride="";
        } else {
            this.emailOverride = emailOverride;
        }
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

    public String getDatePublication() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(datePublication);
    }

    public String getDateFin() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dateFin);
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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

    /**
     * @return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * @return the typeVente
     */
    public boolean isTypeVente() {
        return typeVente;
    }
    
    /**
     * @return type
     */
    public String getType() {
        if (isTypeVente()) {
            return "Vente";
        } else return "Demande";            
    }
    

}
