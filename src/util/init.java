/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controller.UserController;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import model.Etablissement;
import model.Utilisateur;

/**
 *
 * @author Thomas
 */
@Singleton
@Startup
public class init {
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
        byte[] hash = digest.digest( (u.getUserSalt()+u.getId()+newPassWord+appsalt).getBytes(StandardCharsets.UTF_8));
       return new String(hash);
//        return ("#usa:"+u.getUserSalt()+"+#id:"+u.getId()+"#pw:"+newPassWord+"+#appsalt+"+appsalt);
    }

    @PostConstruct
    public void initbd() {
        
        uc.creerUser("Thomas.benhamou@hotmail.fr","passtom", "Benhamou", "Thomas", "ADMIN_ROLE", new ArrayList<Etablissement>());
        System.out.println("init finished");
    }

}
