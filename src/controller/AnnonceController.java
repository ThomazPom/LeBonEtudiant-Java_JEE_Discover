/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

    public Annonce creerAnnonce(Utilisateur Proprietaire, String titre, int prix, String numeroOverride, String emailOverride, String Description, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce a = new Annonce(Proprietaire, titre, prix, numeroOverride, emailOverride, Description, active, categories, etablissements);
        em.persist(a);
        return a;
    }

    public Annonce majAnnonce(int id,String titre, int prix, String numeroOverride, String emailOverride, String Description, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce annonce =  getAnnonceById(id);
        if(annonce!=null)
        {
            if(prix!=-1) annonce.setPrix(prix);
            if(numeroOverride!=null) annonce.setNumeroOverride(numeroOverride);
            if(emailOverride!=null) annonce.setEmailOverride(emailOverride);
            if(Description!=null) annonce.setPrix(prix);
            em.persist(annonce);
            return annonce;
        }
        return null;
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

    public Annonce creerAnnonce(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, boolean active, String[] categories, String[] etablissements) {
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

        return creerAnnonce(Proprietaire, Titre, prixCreate, numeroOverride, emailOverride, Description, active, arcateg, aretab);
    }
}
