/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.UserController;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Thomas
 */
@Entity
public class Utilisateur {

    private static SecureRandom random = new SecureRandom();
    @OneToMany(mappedBy = "Proprietaire")
    private List<Annonce> annonces;

    public Utilisateur(){}
    public Utilisateur(String login, String nom, String prenom,String numtel, String role,List<Etablissement> etabsUser) {
        this.userSalt = new BigInteger(130, random).toString(32);
        this.login = login.toLowerCase();
        this.nom = nom;
        this.prenom=prenom;
        this.role = role;
        this.numtel = numtel;
        this.etabsUser = etabsUser;
        
    }
    @ManyToMany
    private List<Etablissement> etabsUser;

    private String userSalt;

    private String role;
    private String login;
    private String nom;
    private String prenom;
    private String numtel;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String saltedPass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the saltedPass
     */
    public String getSaltedPass() {
        return saltedPass;
    }

    public void setPass(String newPass) {
        saltedPass = util.init.saltPassWord(this, newPass);
    }

    /**
     * @return the userSalt
     */
    public String getUserSalt() {
        return userSalt;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
@Override
public String toString()
{
    
        return "utilsiateurs.modeles.Utilisateur[ id=" + id + " ]\n[ nom=" + nom + " ]\n[ prenom=" + prenom + " ]\n[ login=" + login + " ]\n[ salt=" +saltedPass  + " ]";
}

    /**
     * @return the etabsUser
     */
    public List<Etablissement> getEtabsUser() {
        return etabsUser;
    }

    /**
     * @param etabsUser the etabsUser to set
     */
    public void setEtabsUser(List<Etablissement> etabsUser) {
        this.etabsUser = etabsUser;
    }
    public void addEtabsUser(Etablissement etabUser) {
        this.etabsUser.add(etabUser);
    }
    public void removeEtabUser(Etablissement etabUser) {
        this.etabsUser.remove(etabUser);
    }

    /**
     * @return the numtel
     */
    public String getNumtel() {
        return numtel;
    }

    /**
     * @param numtel the numtel to set
     */
    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
    
    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

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
     * @param annonce to add annonce in list annonce users
     */
    public void addAnnonce(Annonce a) {
        annonces.add(a);
    }
}
