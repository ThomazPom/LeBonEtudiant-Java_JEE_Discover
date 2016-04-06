/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Annonce;
import model.Categorie;
import model.Etablissement;
import model.Utilisateur;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Thomas
 */
@Stateless
public class AnnonceController {

    @PersistenceContext
    EntityManager em;

    @EJB
    CategorieController cc;

    @EJB
    EtablissementController ec;

    public Annonce creerAnnonce(Utilisateur Proprietaire, String titre, int prix, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce a = new Annonce(Proprietaire, titre, prix, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements, "vente");
        em.persist(a);
        return a;
    }
    
    public Annonce creerAnnonce(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, String[] categories, String[] etablissements) {
        ArrayList<Categorie> arcateg = new ArrayList<>();
        ArrayList<Etablissement> aretab = new ArrayList<>();

        for (String id : categories) {
            int idCateg = -1;
            try {
                idCateg = Integer.parseInt(id);
            } catch (Exception e) {
                System.err.println(id + " is not an id");
                return null;
            }
            Categorie et = cc.getCategoriesById(idCateg);
            if (et != null) {
                arcateg.add(et);
            }
        }
        for (String id : etablissements) {
            int idEtab = -1;
            try {
                idEtab = Integer.parseInt(id);
            } catch (Exception e) {
                System.err.println(id + " is not an id");
                return null;
            }
            Etablissement et = ec.getEtablissementByID(idEtab);
            if (et != null) {
                aretab.add(et);
            }
        }

        int prixCreate = 999999;
        try {
            prixCreate = Integer.parseInt(prix);
        } catch (Exception e) {
            System.err.println(prix + " is not a number");
            return null;
        }
        
        if(numeroOverride==null) numeroOverride = Proprietaire.getNumtel();
        if(emailOverride==null) emailOverride = Proprietaire.getLogin();

        return creerAnnonce(Proprietaire, Titre, prixCreate, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
    }
    
    public Annonce creerDemande(Utilisateur Proprietaire, String titre, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce a = new Annonce(Proprietaire, titre, 0, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements, "demande");
        em.persist(a);
        return a;
    }
    
    public Annonce creerDemande(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, String[] categories, String[] etablissements) {
        ArrayList<Categorie> arcateg = new ArrayList<>();
        ArrayList<Etablissement> aretab = new ArrayList<>();

        for (String id : categories) {
            int idCateg = -1;
            try {
                idCateg = Integer.parseInt(id);
            } catch (Exception e) {
                System.err.println(id + " is not an id");
                return null;
            }
            Categorie et = cc.getCategoriesById(idCateg);
            if (et != null) {
                arcateg.add(et);
            }
        }
        for (String id : etablissements) {
            int idEtab = -1;
            try {
                idEtab = Integer.parseInt(id);
            } catch (Exception e) {
                System.err.println(id + " is not an id");
                return null;
            }
            Etablissement et = ec.getEtablissementByID(idEtab);
            if (et != null) {
                aretab.add(et);
            }
        }
        
        if(numeroOverride==null) numeroOverride = Proprietaire.getNumtel();
        if(emailOverride==null) emailOverride = Proprietaire.getLogin();

        return creerAnnonce(Proprietaire, Titre, 0, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
    }
    
    public void creerAnnonces() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        Query q = em.createQuery("SELECT a from Utilisateur a");
//        List<Utilisateur> listUsers = q.getResultList();
//        for (Utilisateur user : listUsers) {
//            int randomEtab = (int)(Math.random() * (ec.getEtablissements().size()));
//            this.creerAnnonce(user, "", randomEtab, "", "", "", true, cc.getCategories(),ec.getEtablissements());
//        }
    }
    
    public Annonce majAnnonce(int id, String titre, int prix, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) throws ParseException {
        Annonce annonce =  getAnnonceById(id);
        if(annonce!=null)
        {
            if(prix!=-1) annonce.setPrix(prix);                                 
            if(numeroOverride!=null) annonce.setNumeroOverride(numeroOverride);
            if(emailOverride!=null) annonce.setEmailOverride(emailOverride);
            if(Description!=null) annonce.setDescription(Description);
            if(dateFin!=null) annonce.setDateFin(dateFin);
            if(!active) annonce.setActive(active);                              //si l'annonce est desactivee on l'active.
            if(!categories.isEmpty()) annonce.setCategories(categories);
            if(!etablissements.isEmpty()) annonce.setEtablissements(etablissements);
            
            em.persist(annonce);
            return annonce;
        }
        return null;
    }
    
    public Annonce majAnnonce(String id, String titre, String prix, String numeroOverride, String emailOverride, String Description, String dateFin, boolean active, String[] categories, String[] etablissements) throws ParseException{
        ArrayList<Categorie> arcateg = new ArrayList<>();
        ArrayList<Etablissement> aretab = new ArrayList<>();
        
        int idAnnonce = -1;
        try {
            idAnnonce = Integer.parseInt(id);
        } catch (Exception e) {
            System.err.println(id + "is not an id (majAnnonce)");
            return null;
        }
        for (String idA : categories) {
            int idCateg = -1;
            try {
                idCateg = Integer.parseInt(idA);
            } catch (Exception e) {
                System.err.println(idA + " is not an id (majAnnonce)");
                return null;
            }
            Categorie et = cc.getCategoriesById(idCateg);
            if (et != null) {
                arcateg.add(et);
            }
        }
        for (String idA : etablissements) {
            int idEtab = -1;
            try {
                idEtab = Integer.parseInt(idA);
            } catch (Exception e) {
                System.err.println(idA + " is not an id (majAnnonce)");
                return null;
            }
            Etablissement et = ec.getEtablissementByID(idEtab);
            if (et != null) {
                aretab.add(et);
            }
        }
        
        int prixAnnonce = 999999;
        try {
            prixAnnonce = Integer.parseInt(prix);
        } catch (Exception e) {
            System.err.println(prix + "is not a number (majAnnonce)");
            return null;
        }
        return majAnnonce(idAnnonce, titre, prixAnnonce, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
    }

    public Annonce getAnnonceById(int id) {
        System.out.println("-->>getAnnonceById()");
        Query q = em.createQuery("SELECT a from Annonce c where a.id=:id");
        q.setParameter("id", id);
        List<Annonce> listAnnonce = q.getResultList();

        Iterator<Annonce> i = listAnnonce.iterator();
        if (i.hasNext()) {
            return i.next();
        }
        System.out.println("Annonce : " + id +" is not an ID");
        return null;
    }
    
    public List<Annonce> getAnnonces() {
        System.out.println("-->>getAnnonces()");
        return em.createQuery("SELECT a from Annonce a").getResultList();
    }
}
