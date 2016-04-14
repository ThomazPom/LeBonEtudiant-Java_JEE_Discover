/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    
    //Classe anonyme pour la pagination des annonces
    public static class AnnoncePage{
        private int nbPages;
        private int nbresSultPage;
        private int pageCourante;
        private Collection<Annonce> resultList;

        public AnnoncePage( int nbresSultPage, int pageCourante) {
            this.nbresSultPage = nbresSultPage;
            this.pageCourante = pageCourante;
        }
        public int getNbPages() {
            return nbPages;
        }

        public void setNbPages(int nbPages) {
            this.nbPages = nbPages;
        }
        
        public int getNbresSultPage() {
            return nbresSultPage;
        }
        public int getPageCourante() {
            return pageCourante;
        }
        public Collection<Annonce> getResultList() {
            return resultList;
        }
        public void setResultList(Collection<Annonce> resultList) {
            this.resultList = resultList;
        } 
    }
    
    public int countAllAds(){
        Query query = em.createQuery("select COUNT(a) from Annonce a");
       return Integer.parseInt(query.getSingleResult().toString());
    }
    
    public void getOnePageAds(AnnoncePage annoncePage)
    {
        Query query = em.createQuery("select a from Annonce a");
        query.setFirstResult(annoncePage.getPageCourante() * annoncePage.getNbresSultPage());
        query.setMaxResults(annoncePage.getNbresSultPage());
        
        int nbAds = countAllAds();                 //on recupere le nombre d'utlisateurs
        int NbPages = (int) Math.ceil(nbAds / annoncePage.getNbresSultPage());
        
        annoncePage.setNbPages(NbPages);
        annoncePage.setResultList(query.getResultList());
    }
    

    public Annonce creerAnnonce(Utilisateur Proprietaire, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce a = new Annonce(Proprietaire, titre, prix, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements, true);
        em.persist(a);
        return a;
    }

    public Annonce creerAnnonce(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, String strDateFin, boolean active, String[] categories, String[] etablissements) {
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFin = Calendar.getInstance().getTime();
        try {
            dateFin = df.parse(strDateFin);
        } catch (ParseException ex) {
            System.out.println(strDateFin + " is not a valid date string");
        }

        if (numeroOverride == null) {
            numeroOverride = Proprietaire.getNumtel();
        }
        if (emailOverride == null) {
            emailOverride = Proprietaire.getLogin();
        }

        return AnnonceController.this.creerAnnonce(Proprietaire, Titre, prixCreate, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
    }

    public Annonce creerDemande(Utilisateur Proprietaire, String titre, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        Annonce a = new Annonce(Proprietaire, titre, 0, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements, false);
        em.persist(a);
        return a;
    }

    public Annonce creerDemande(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, String strDateFin, boolean active, String[] categories, String[] etablissements) {
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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFin = Calendar.getInstance().getTime();
        try {
            dateFin = df.parse(strDateFin);
        } catch (ParseException ex) {
            System.out.println(strDateFin + " is not a valid date string");
        }

        if (numeroOverride == null) {
            numeroOverride = Proprietaire.getNumtel();
        }
        if (emailOverride == null) {
            emailOverride = Proprietaire.getLogin();
        }

        return creerDemande(Proprietaire, Titre, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
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

    public Annonce majAnnonce(Annonce annonce, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {

        if (annonce != null) {
            
            if (prix != -1) {
                annonce.setPrix(prix);
            }
            if (numeroOverride != null) {
                annonce.setNumeroOverride(numeroOverride);
            }
            
            if (titre != null) {
                annonce.setTitre(titre);
            }
            if (emailOverride != null) {
                annonce.setEmailOverride(emailOverride);
            }
            if (Description != null) {
                annonce.setDescription(Description);
            }
            if (dateFin != null) {
                annonce.setDateFin(dateFin);
            }
            if (!active) {
                annonce.setActive(active);                              //si l'annonce est desactivee on l'active.
            }
            if (!categories.isEmpty()) {
                annonce.setCategories(categories);
            }
            if (!etablissements.isEmpty()) {
                annonce.setEtablissements(etablissements);
            }

            em.merge(annonce);
            return annonce;
        }
        return null;
    }

    public Annonce majAnnonce(int id, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        return majAnnonce(getAnnonceById(id), titre, prix, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements);
    }

    public Annonce majAnnonce(Annonce annonce, String titre, String prix, String numeroOverride, String emailOverride, String Description, String strDateFin, boolean active, String[] categories, String[] etablissements) {
        {

            ArrayList<Categorie> arcateg = new ArrayList<>();
            ArrayList<Etablissement> aretab = new ArrayList<>();

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
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dateFin = Calendar.getInstance().getTime();
            try {
                dateFin = df.parse(strDateFin);
            } catch (ParseException ex) {
                System.out.println(strDateFin + " is not a valid date string");
            }
            int prixAnnonce = 999999;
            try {
                prixAnnonce = Integer.parseInt(prix);
            } catch (Exception e) {
                System.err.println(prix + "is not a number (majAnnonce)");
                return null;
            }
            return majAnnonce(annonce, titre, prixAnnonce, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab);
        }
    }
    public Annonce majAnnonce(String id, String titre, String prix, String numeroOverride, String emailOverride, String Description, String strDateFin, boolean active, String[] categories, String[] etablissements) {
     return majAnnonce(getAnnonceById(id), titre, prix, numeroOverride, emailOverride, Description, strDateFin, active, categories, etablissements);
    }

    public Annonce getAnnonceById(String strid) {
        int idAnnonce = -1;
        try {
            idAnnonce = Integer.parseInt(strid);
        } catch (Exception e) {
            System.err.println(strid + "is not an id (getAnnonceByStrId)");
            return null;
        }
        return getAnnonceById(idAnnonce);
    }

    public Annonce getAnnonceById(int id) {
        System.out.println("-->>getAnnonceById()");
        Query q = em.createQuery("SELECT a from Annonce a where a.id=:id");
        q.setParameter("id", id);
        List<Annonce> listAnnonce = q.getResultList();

        Iterator<Annonce> i = listAnnonce.iterator();
        if (i.hasNext()) {
            return i.next();
        }
        System.out.println("Annonce : " + id + " is not an id in base");
        return null;
    }

    public List<Annonce> getAnnonces() {
        System.out.println("-->>getAnnonces()");
        return em.createQuery("SELECT a from Annonce a").getResultList();
    }
}
