/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Categorie;
import model.Region;

/**
 *
 * @author Thomas
 */
@Stateless
public class CategorieController {

    @PersistenceContext
    EntityManager em;

    public Categorie getCategorieByName(String libelle, boolean createIfNotExist) {
        System.out.println("----->public Categorie getCategorieByName(String libelle,boolean createIfNotExist)");
        Query q = em.createQuery("SELECT r from Categorie r where r.libelle =:libelle");

        q.setParameter("libelle", libelle);
        List<Categorie> ql = q.getResultList();

        if (ql.iterator().hasNext()) {
            return ql.iterator().next();
        } else if (createIfNotExist) {
            return createCategorie(libelle);
        }
        return null;
    }

    public List<Categorie> getCategoriesById(List<Long> idCateg) {
        System.out.println("-->>getCategoriesById()");
        Query q = em.createQuery("SELECT r from Categorie r WHERE r.id IN :idCateg");
        q.setParameter("idCateg", idCateg);
        return q.getResultList();
    }

    public List<Categorie> getCategoriesById(String[] idCateg) {
        List<Long> arIdsList = new ArrayList<Long>();
        for (String s : idCateg) {
            try {
                arIdsList.add(Long.parseLong(s));
            } catch (Exception e) {
                System.err.println(s + " is not a valid id ");
            }
        }
        return getCategoriesById(arIdsList);
    }

    public Categorie createCategorie(String libelle) {
        System.out.println("----->public Categorie createCategorie(String libelle)");
        Categorie r = new Categorie(libelle);
        em.persist(r);
        return r;
    }

    public List<Categorie> getCategories() {
        System.out.println("-->>getCategories()");
        return em.createQuery("SELECT c from Categorie c").getResultList();
    }

    public Categorie getCategoriesById(int id) {
        System.out.println("-->>getCategoriesById()");
        Query q = em.createQuery("SELECT c from Categorie c where c.id=:id");
        q.setParameter("id", id);
        List<Categorie> listCateg = q.getResultList();

        Iterator<Categorie> i = listCateg.iterator();
        if (i.hasNext()) {
            return i.next();
        }
        return null;
    }
}
