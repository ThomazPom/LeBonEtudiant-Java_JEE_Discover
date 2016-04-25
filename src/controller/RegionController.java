/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
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
 public List<Region> getRegionsById(List<Long> idRegion) {
        System.out.println("-->>getRegionsById()");
        Query q = em.createQuery("SELECT r from Region r WHERE r.id IN :idRegion");
        q.setParameter("idRegion", idRegion);
        return q.getResultList();
    }

    public  List<Region> getRegions(){
        return em.createQuery("select r from Region r").getResultList();
    }
    public List<Region> getRegionsById(String[] idRegion) {
        List<Long> arIdsList = new ArrayList<Long>() ;
        for(String s : idRegion)
        {
            try {
                arIdsList.add(Long.parseLong(s));
            } catch (Exception e) {
                System.err.println(s + " is not a valid id ");
            }
        }
        return getRegionsById(arIdsList);
    }
    public Region createRegion(String libelle) {
        System.out.println("----->public Region createRegion(String libelle)");
        Region r = new Region(libelle);
       em.persist(r);
       return r;
    }

}
