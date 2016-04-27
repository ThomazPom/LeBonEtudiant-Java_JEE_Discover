/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Etablissement;
import model.Ville;

/**
 *
 * @author Thomas
 */
@Stateless
public class EtablissementController {

    @PersistenceContext
    private EntityManager em;

    public Etablissement getEtablissementByID(int id) {
        System.out.println("----->public Etablissement getEtablissementByName(String libelle,boolean createIfNotExist)");
        Query q = em.createQuery("SELECT r from Etablissement r where r.id =:id");

        q.setParameter("id", id);
        List<Etablissement> ql = q.getResultList();

        if (ql.iterator().hasNext()) {
            return ql.iterator().next();
        }
        return null;
    }

    public List<Etablissement> getEtablissementsById(List<Long> idEtabs) {
        if (idEtabs.size()==0) {
            return new ArrayList<>();
        }
        System.out.println("-->>getEtablissementsById()");
        Query q = em.createQuery("SELECT r from Etablissement r WHERE r.id IN :idEtabs");
        q.setParameter("idEtabs", idEtabs);
        return q.getResultList();
    }

    public List<Etablissement> getEtablissementsById(String[] idEtabs) {
        List<Long> arIdsList = new ArrayList<Long>() ;
        for(String s : idEtabs)
        {
            try {
                arIdsList.add(Long.parseLong(s));
            } catch (Exception e) {
                System.err.println(s + " is not a valid id ");
            }
        }
        return getEtablissementsById(arIdsList);
    }
    public List<Etablissement> getEtablissements() {
        System.out.println("-->>getEtablissements()");
        return em.createQuery("SELECT r from Etablissement r").getResultList();

    }

    public Etablissement createEtablissement(String nom, String sigle, String adresse, String codepostal, Ville ville, double LongitudeX, double LatitudeY) {
        System.out.println("-----> createEtablissement(nom, sigle, adresse, codepostal, ville, LongitudeX, LatitudeY");
        Etablissement r = new Etablissement(nom, sigle, adresse, codepostal, ville, LongitudeX, LatitudeY);
        em.persist(r);
        return r;
    }

}
