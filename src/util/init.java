/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
        uc.creerUser("Thomas.benhamou@hotmail.fr", "passtom", "Benhamou", "Thomas", "0678097826", "ADMIN_ROLE", new ArrayList<Etablissement>());
        uc.creerUser("myriamrouis@gmail.com", "passmyriam", "Rouis", "Myriam", "0102030405", "ADMIN_ROLE", new ArrayList<Etablissement>());
        uc.creerUser("benoit.silvestro@gmail.com", "passbenoit", "Silvestro", "Benoit", "0102030405", "ADMIN_ROLE", new ArrayList<Etablissement>());

        initEtabRegionDeptVille();
        initCategorie();
        initUsers();
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
                
                if(true || rc.getRegionByName(etabStrings[6], false)==null)
                {
                Region region = rc.getRegionByName(etabStrings[6], true);
                Departement departement = dc.createDepartement(etabStrings[5], region);
              
                Ville ville = vc.createVille(etabStrings[4], departement);
                Etablissement Etablissement = ec.createEtablissement(etabStrings[0], etabStrings[1], etabStrings[2], etabStrings[3], ville, Double.parseDouble(etabStrings[7]),Double.parseDouble(etabStrings[8]) );
                }
                else
                {
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
            while(s.hasNext()) {
                String nomCategorie = s.next();
                System.out.println("------->while (s.hasNext())" + nomCategorie);
                
                cc.getCategorieByName(nomCategorie, true);
                
            }
        } catch (Exception e) {
        }

    }
    
    public void initUsers() {
        System.out.println("-------->public void initUsers()");
        try {
            System.out.println("------->TRY");
            InputStream is = getClass().getResourceAsStream("etudiantL3.csv");
            java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\r\n");
            s.next();                           //On ignore l'entete du CSV
            while(s.hasNext()) {
                String[] userStrings = s.next().split(";");
                System.out.println("------->while (s.hasNext())" + userStrings[0]);
                //0Nom;1Prenom;2Email
                
               uc.creerUser(userStrings[2], "pass"+userStrings[1], 
                       userStrings[0], userStrings[1], "USER_ROLE", null, "Universite Sophia Antipolis");
            } 
            
            
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
