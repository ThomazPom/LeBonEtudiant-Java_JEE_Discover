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
import java.util.Arrays;
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
import model.Departement;
import model.Etablissement;
import model.Region;
import model.Utilisateur;
import model.Ville;

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
    @EJB
    VilleController vc;
    @EJB
    DepartementController dc;
    @EJB
    RegionController rc;

    //Classe anonyme pour la pagination des annonces
    public static class AnnoncePage {

        private int nbPages;
        private int nbresultPage;
        private int pageCourante;
        private Collection<Annonce> resultList;

        public AnnoncePage(int nbresSultPage, int pageCourante) {
            this.nbresultPage = nbresSultPage;
            this.pageCourante = pageCourante;
        }

        public int getNbPages() {
            return nbPages;
        }

        public void setNbPages(int nbPages) {
            this.nbPages = nbPages;
            if (nbPages < pageCourante) {
                pageCourante = nbPages;
            }
        }

        public int getNbresultPage() {
            return nbresultPage;
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

    public int countAllAds() {
        Query query = em.createQuery("select COUNT(a) from Annonce a");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public void getOnePageAds(AnnoncePage annoncePage) {

        int nbAds = countAllAds();                 //on recupere le nombre d'annonces
        int nbPages = (int) Math.ceil(nbAds / annoncePage.getNbresultPage());
        annoncePage.setNbPages(nbPages);

        Query query = em.createQuery("select a from Annonce a where a.active =:active");
        
        query.setParameter("active", true);
        query.setFirstResult(annoncePage.getPageCourante() * annoncePage.getNbresultPage());
        query.setMaxResults(annoncePage.getNbresultPage());

        annoncePage.setResultList(query.getResultList());
    }

    public Annonce creerAnnonce(Utilisateur Proprietaire, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements, Boolean typeAnnonce) {
        System.out.println("------>public Annonce creerAnnonce(Utilisateur Proprietaire, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin ....");
        Annonce a = new Annonce(Proprietaire, titre, prix, numeroOverride, emailOverride, Description, dateFin, active, categories, etablissements, typeAnnonce);
        Proprietaire.addAnnonce(a);
        em.persist(a);
        em.merge(Proprietaire);
        return a;
    }
    List allowedTypes = Arrays.asList("vente", "demande");

    public List<Annonce> searchAnnonce(String titre, String type, String prixMin, String prixMax, String[] idEtabs, String[] idVilles, String[] idDepts, String[] idRegions, String[] idCategs) {
        String[] titreSplit = titre.split(" ");
        
        int prixmin = 0;
        int prixmax = 20000;
        List<Categorie> categSelect = cc.getCategoriesById(idCategs);
        List<Region> regionSelect = rc.getRegionsById(idRegions);
        List<Ville> villesSelect = vc.getVillesById(idVilles);
        List<Departement> deptSelect = dc.getDepartementById(idDepts);
        List<Etablissement> etabSelect = ec.getEtablissementsById(idEtabs);
        Boolean geolocEmpty = regionSelect.isEmpty() && villesSelect.isEmpty() && deptSelect.isEmpty() && etabSelect.isEmpty();
        try {
            prixmin = Integer.parseInt(prixMin);
            prixmax = Integer.parseInt(prixMax);
        } catch (Exception e) {
            System.err.println(prixMin + " or " + prixMax + " is not a valid price");
        }
        StringBuilder queryString = new StringBuilder();
        queryString.append("Select a from Annonce a where a.active =:active AND a.prix BETWEEN :prixmin AND :prixmax");
        queryString.append(titreSplit.length > 0 ? " AND (" : "");
        for (int i = titreSplit.length -1; i >-1 ; i--) {
            String s = titreSplit[i].trim();
            queryString.append("lower(a.Titre) LIKE lower(:q"+i+")" + (i!=0? " OR " :"")  );
        }
        
        queryString.append(titreSplit.length > 0 ? ")" : "");
        if (allowedTypes.contains(type)) {
            queryString.append(" AND a.typeVente = :typeVente");
        }
        Query q = em.createQuery(queryString.toString());
        q.setParameter("active", true);
        q.setParameter("prixmin", prixmin);
        q.setParameter("prixmax", prixmax);
        if (allowedTypes.contains(type)) {
            q.setParameter("typeVente", allowedTypes.get(0).equals(type));
        }
         for (int i = titreSplit.length -1; i >-1 ; i--) {
            String s = titreSplit[i].trim();
            q.setParameter("q"+i, "%"+s+"%");
        }
        List<Annonce> resultBrut = q.getResultList();
        List<Annonce> resultList = new ArrayList<>(resultBrut);
        for (Annonce ann : resultBrut) {
            boolean keep = true;
            boolean keepGeoloc = geolocEmpty;
            for (int i = 0; i < categSelect.size() && keep; i++) {
                keep = ann.getCategories().contains(categSelect.get(i));
            }
            for (int i = 0; i < ann.getEtablissements().size() && keep && !keepGeoloc; i++) {
                Etablissement etab = ann.getEtablissements().get(i);
                keepGeoloc = etabSelect.contains(etab);
                keepGeoloc = keepGeoloc ? true : villesSelect.contains(etab.getVille());
                keepGeoloc = keepGeoloc ? true : deptSelect.contains(etab.getVille().getDepartement());
                keepGeoloc = keepGeoloc ? true : regionSelect.contains(etab.getVille().getDepartement().getRegion());
            }
            if (!keep || !keepGeoloc) {
                resultList.remove(ann);
            }
        }
        return resultList;
    }

    public Annonce creerAnnonce(Utilisateur Proprietaire, String Titre, String prix, String numeroOverride, String emailOverride, String Description, String strDateFin, boolean active, String[] categories, String[] etablissements, String typeAnnonce) {
        Boolean typeAnnonceBool = allowedTypes.get(0).equals(typeAnnonce);
        List<Categorie> arcateg = cc.getCategoriesById(categories);
        List<Etablissement> aretab = ec.getEtablissementsById(etablissements);
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

        return AnnonceController.this.creerAnnonce(Proprietaire, Titre, prixCreate, numeroOverride, emailOverride, Description, dateFin, active, arcateg, aretab, typeAnnonceBool);
    }

    public Annonce majAnnonce(Annonce annonce, String titre, int prix, String numeroOverride, String emailOverride, String Description, Date dateFin, boolean active, List<Categorie> categories, List<Etablissement> etablissements) {
        System.out.println("------->majAnnonce(Annonce annonce, String titre, int prix, String numeroOverride, String emailOverr...;");
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
            System.out.println(active+"|"+annonce.isActive());
            if (active != annonce.isActive()) {
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

            List<Categorie> arcateg = cc.getCategoriesById(categories);
            List<Etablissement> aretab = ec.getEtablissementsById(categories);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dateFin = Calendar.getInstance().getTime();
            try {
                dateFin = df.parse(strDateFin);
            } catch (Exception e) {
                System.out.println(strDateFin + " is not a valid date string");
            }
            int prixAnnonce = -1;
            try {
                prixAnnonce = Integer.parseInt(prix);
            } catch (Exception e) {
                System.err.println(prix + "is not a number (majAnnonce)");
                
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
        Query q = em.createQuery("SELECT a from Annonce a where a.active =:active");
        q.setParameter("active", true);
        return q.getResultList();
    }
}
