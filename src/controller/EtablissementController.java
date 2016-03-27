/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    
    public Etablissement createEtablissement(String nom, String sigle, String adresse, String codepostal, Ville ville, double LongitudeX, double LatitudeY) {
        System.out.println("-----> createEtablissement(nom, sigle, adresse, codepostal, ville, LongitudeX, LatitudeY");
        Etablissement r = new Etablissement(nom, sigle, adresse, codepostal, ville, LongitudeX, LatitudeY);
        em.persist(r);
        return r;
    }
    
}
