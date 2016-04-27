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
        if (!ql.isEmpty()) {
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
    
    public  List<Departement> getDepartements(){
        return em.createQuery("select d from Departement d").getResultList();
    }
    public List<Departement> getDepartementById(List<Long> idDept) {
        System.out.println("-->>getDepartementById()");
        if (idDept.size()==0) {
            return new ArrayList<>();
        }
        Query q = em.createQuery("SELECT r from Departement r WHERE r.id IN :idDept");
        q.setParameter("idDept", idDept);
        return q.getResultList();
    }

    public List<Departement> getDepartementById(String[] idDept) {
        List<Long> arIdsList = new ArrayList<Long>();
        for (String s : idDept) {
            try {
                arIdsList.add(Long.parseLong(s));
            } catch (Exception e) {
                System.err.println(s + " is not a valid id ");
            }
        }
        return getDepartementById(arIdsList);
    }
}
