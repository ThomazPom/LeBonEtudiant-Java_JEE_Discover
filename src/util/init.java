/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.AnnonceController;
import controller.CategorieController;
import controller.DepartementController;
import controller.EtablissementController;
import controller.RegionController;
import controller.UserController;
import controller.VilleController;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
@Singleton
@Startup
public class init {

    @EJB
    private AnnonceController ac;
    @EJB
    private CategorieController cc;
    @EJB
    private DepartementController dc;
    @EJB
    private RegionController rc;
    @EJB
    private VilleController vc;
    @EJB
    private EtablissementController ec;
    @EJB
    private UserController uc;
    private static String appsalt = "AKWVVxpE9NdaZ5yBLfZMYEUmkcLPQTLjdPAQQvjF3wgmGq8QvwWUJLpugbmA5brE9s42gDYwbTYLdTBAa6JPcVj7G69jaexp";

    public static String saltPassWord(Utilisateur u, String newPassWord) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(init.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] hash = digest.digest((u.getUserSalt() + u.getId() + newPassWord + appsalt).getBytes(StandardCharsets.UTF_8));
        return new String(hash);
//        return ("#usa:"+u.getUserSalt()+"+#id:"+u.getId()+"#pw:"+newPassWord+"+#appsalt+"+appsalt);
    }

    @PostConstruct
    public void initbd() {
        uc.creerUser("Thomas.benhamou@hotmail.fr", "passtom", "Benhamou", "Thomas", "ADMIN_ROLE", "0674597826", new ArrayList<Etablissement>());
        uc.creerUser("myriamrouis@gmail.com", "passmyriam", "Rouis", "Myriam", "ADMIN_ROLE", "0678097898", new ArrayList<Etablissement>());
        uc.creerUser("benoit.silvestro@gmail.com", "passbenoit", "Silvestro", "Benoit", "ADMIN_ROLE", "0678094526", new ArrayList<Etablissement>());
        initEtabRegionDeptVille();
        initCategorie();
        initEtudiantsL3();
        initProfs();
        initEtudiantsM1();
        initEtudiants500();
        initAnnonces();
        System.out.println("init finished");
    }

    public void initEtabRegionDeptVille() {
        System.out.println("-------->public void initEtabRegionDeptVille()");
        try {

            System.out.println("------->TRY");
            InputStream is = getClass().getResourceAsStream("etablissement_superieur.csv");
            java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\r\n");
            s.next();//On ignore l'entete du CSV
            System.out.println("->>>>>>#########Amptempting to feed the database".toUpperCase());
            while (s.hasNext()) {
                String[] etabStrings = s.next().split(";");
                System.out.println("------->while (s.hasNext())" + etabStrings[0]);
                //0nom;1sigle;2adresse;3CP;4commune;5département;6région;7longitude (X);8latitude (Y)

                if (true || rc.getRegionByName(etabStrings[6], false) == null) {
                    Region region = rc.getRegionByName(etabStrings[6], true);
                    
                    Departement departement = dc.getDepartementByName(etabStrings[5], true, region);

                    Ville ville = vc.getVilleByName(etabStrings[4], true, departement);
                    Etablissement Etablissement = ec.createEtablissement(etabStrings[0], etabStrings[1], etabStrings[2], etabStrings[3], ville, Double.parseDouble(etabStrings[7]), Double.parseDouble(etabStrings[8]));
                } else {

                    System.out.println("->>>>>#####Base was already ready".toUpperCase());
                    break;
                }
            }
            s.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void initCategorie() {
        System.out.println("-------->public void initCategorie()");

        try {
            System.out.println("------->TRY");
            InputStream is = getClass().getResourceAsStream("categorie.csv");
            java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\r\n");
            while (s.hasNext()) {
                String nomCategorie = s.next();
                System.out.println("------->while (s.hasNext())" + nomCategorie);

                cc.getCategorieByName(nomCategorie, true);

            }
        } catch (Exception e) {
        }

    }

    public void initAnnonces() {
        System.out.println("-------->public void initAnnonce()");
        List<Categorie> listCateg = cc.getCategories();
        List<Utilisateur> listUser = uc.getUsers();
        Random randUser = new Random(listUser.size());
        List<Etablissement> listEtab = ec.getEtablissements();
        Random rand = new Random(listEtab.size());
        try {
            System.out.println("------->TRY");
            InputStream is = getClass().getResourceAsStream("listeproduits.csv");
            java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\r\n");
            while (s.hasNext()) {
                String titreAnnonce = s.next();
                System.out.println("------->while (s.hasNext())" + titreAnnonce);

                ac.creerAnnonce(listUser.get(rand.nextInt(listUser.size())),
                        titreAnnonce,
                        rand.nextInt(20000),
                        "",
                        "",
                        "Je me sépares de  : " + titreAnnonce + " mais croyez moi, il est en bon état",
                        Calendar.getInstance().getTime(),
                        true,
                        Arrays.asList(listCateg.get(rand.nextInt(listCateg.size()))),
                        Arrays.asList(listEtab.get(rand.nextInt(listEtab.size()))),
                        true
                        
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } 

    public void initEtudiantsM1(){
        System.out.println("-------->public void initEtudiantsM1()");
        Random rand = new Random();
        List<Etablissement> allEtab = ec.getEtablissements();
        Random randEtab = new Random(allEtab.size());
        try {
            System.out.println("------->TRY");
            
            //On initialise la liste des etudiants de M1 Miage
            InputStream is = getClass().getResourceAsStream("etudiantsM1.csv");
            Scanner s = new Scanner(is, "UTF-8").useDelimiter("\r\n");
            s.next();                           //On ignore l'entete du CSV
            
            System.out.println("Taille de la collection Etablissement: " + allEtab.size());
            while (s.hasNext()) {
                String[] userStrings = s.next().split(";");
                System.out.println("------->while (s.hasNext())" + userStrings[0]);
                //0Nom;1Prenom;2Email
                uc.creerUser(userStrings[2], "pass" + userStrings[1],
                        userStrings[0], userStrings[1], "Etudiant", "0" + rand.nextInt(1000000000), Arrays.asList(allEtab.get(randEtab.nextInt(allEtab.size()))));
            }
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initProfs(){
        System.out.println("-------->public void initProfs()");
        List<Etablissement> listEtab = ec.getEtablissements();
        Random randEtab = new Random(listEtab.size());
        
        try {
            System.out.println("------->TRY");
            
            //On initialise la liste des enseignants
            InputStream isProfs = getClass().getResourceAsStream("Enseignants.csv");
            Scanner sc = new Scanner(isProfs, "UTF-8").useDelimiter("\r\n");
            sc.next();                       //On ignore l'entete du CSV
            
            System.out.println("Taille de la collection Etablissement: " + listEtab.size());
            while (sc.hasNext()) {
                String[] enseignantsStrings = sc.next().split(";");
                System.out.println("------->while (sc.hasNext())" + enseignantsStrings[0]);
                //les utilisateurs par défaut sont enseignants. A l'exception de deux utilisateurs qui sont secrétaires
                String role = "Enseigant";
                if(enseignantsStrings[0].equals("Bloise") || enseignantsStrings[0].equals("Deparis")){
                    role = "Secrétaire";
                }
                //0Nom;1Prenom;2Email;3telephone
                uc.creerUser(enseignantsStrings[2], "pass" + enseignantsStrings[1],
                        enseignantsStrings[0], enseignantsStrings[1], role, enseignantsStrings[3], Arrays.asList(listEtab.get(randEtab.nextInt(listEtab.size()))));
            }
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initEtudiantsL3() {
        System.out.println("-------->public void initEtudiantsL3()");
        Random rand = new Random();
        List<Etablissement> allEtab = ec.getEtablissements();
        Random randEtab = new Random(allEtab.size());
        
        try {
            System.out.println("------->TRY");
            //On initialise la liste des etudiants en L3 Miage
            InputStream is = getClass().getResourceAsStream("etudiantL3.csv");
            Scanner s = new Scanner(is, "UTF-8").useDelimiter("\r\n");
            s.next();                           //On ignore l'entete du CSV
            
            System.out.println("Taille de la collection Etablissement: " + allEtab.size());
            while (s.hasNext()) {
                String[] userStrings = s.next().split(";");
                System.out.println("------->while (s.hasNext())" + userStrings[0]);
                //0Nom;1Prenom;2Email
                uc.creerUser(userStrings[2], "pass" + userStrings[1],
                        userStrings[0], userStrings[1], "Etudiant", "0" + rand.nextInt(1000000000), Arrays.asList(allEtab.get(randEtab.nextInt(allEtab.size()))));
            }
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initEtudiants500(){
        System.out.println("-------->public void initEtudiants500()");
        List<Etablissement> allEtab = ec.getEtablissements();
        Random randEtab = new Random(allEtab.size());
        
        try {
            System.out.println("-------->TRY");
            //On initialise la liste de 500 etudiants
            InputStream is = getClass().getResourceAsStream("data500Etudiants.csv");
            Scanner s = new Scanner(is, "UTF-8").useDelimiter("\r\n");
            s.next();                           //On ignore l'entete du CSV
            
            System.out.println("Taille de la collection Etablissement: " + allEtab.size());
            while (s.hasNext()) {
                String[] userStrings = s.next().split(";");
                System.out.println("------->while (s.hasNext())" + userStrings[0]);
                //0Nom;1Prenom;2Email;3telephone
                uc.creerUser(userStrings[2], "pass" + userStrings[1],
                        userStrings[0], userStrings[1], "Etudiant", userStrings[3], Arrays.asList(allEtab.get(randEtab.nextInt(allEtab.size()))));
            }
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
