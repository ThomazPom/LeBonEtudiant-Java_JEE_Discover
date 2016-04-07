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
import model.Ville;

/**
 *
 * @author Thomas
 */

@Stateless
public class VilleController {
@PersistenceContext
EntityManager em;
    public Ville getVilleByName(String libelle,boolean createIfNotExist,Departement departement) {
        System.out.println("----->public Ville getVilleByName(String libelle,boolean createIfNotExist)");
        Query q = em.createQuery("SELECT r from Ville r where r.libelle =:libelle and r.departement.id=:deptid");
        
        q.setParameter("libelle", libelle);
        q.setParameter("deptid", departement.getId());
        List<Ville> ql = q.getResultList();
        
        if (ql.iterator().hasNext()) {
            return ql.iterator().next();
        }
        else if(createIfNotExist)
        {
            return createVille(libelle,departement);
        }
        return  null;
    }

    public Ville createVille(String libelle, Departement departement) {
        System.out.println("----->public Ville createVille(String libelle)");
      Ville v = new Ville(libelle, departement);
      em.persist(v);
    return v;

    }
}
