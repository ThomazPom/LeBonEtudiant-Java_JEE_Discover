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
import model.Region;

/**
 *
 * @author Thomas
 */

@Stateless
public class RegionController {
    @PersistenceContext
    private EntityManager em;
    

    public Region getRegionByName(String libelle,boolean createIfNotExist) {
        System.out.println("----->public Region getRegionByName(String libelle,boolean createIfNotExist)");
        Query q = em.createQuery("SELECT r from Region r where r.libelle =:libelle");
        
        q.setParameter("libelle", libelle);
        List<Region> ql = q.getResultList();
        
        if (ql.iterator().hasNext()) {
            return ql.iterator().next();
        }
        else if(createIfNotExist)
        {
            return createRegion(libelle);
        }
        return  null;
    }

    public Region createRegion(String libelle) {
        System.out.println("----->public Region createRegion(String libelle)");
        Region r = new Region(libelle);
       em.persist(r);
       return r;
    }

}
