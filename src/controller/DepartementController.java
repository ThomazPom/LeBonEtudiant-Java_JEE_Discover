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
import model.Departement;
import model.Region;

/**
 *
 * @author Thomas
 */

@Stateless
public class DepartementController {
@PersistenceContext
EntityManager em;
    public Departement getDepartementByName(String libelle,boolean createIfNotExist,Region region) {
        System.out.println("----->public Departement getDepartementByName(String libelle,boolean createIfNotExist)");
        Query q = em.createQuery("SELECT r from Departement r where r.libelle =:libelle");
        
        q.setParameter("libelle", libelle);
        List<Departement> ql = q.getResultList();
        
        if (ql.iterator().hasNext()) {
            return ql.iterator().next();
        }
        else if(createIfNotExist)
        {
            return createDepartement(libelle,region);
        }
        return  null;
    }

    public Departement createDepartement(String libelle, Region region) {
        System.out.println("----->public Departement createDepartement(String libelle)");
        Departement r = new Departement(libelle,region);
       em.persist(r);
       return r;
    }
}
