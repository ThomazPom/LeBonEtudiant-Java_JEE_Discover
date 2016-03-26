/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Thomas
 */
@Entity
public class User {

    private static SecureRandom random = new SecureRandom();

    public User(String login, String passWord, String nom, String prenom, String role, ArrayList<Etablissement> etabsUser) {
        this.userSalt = new BigInteger(130, random).toString(32);
        this.setPass(passWord);
        this.login = login;
        this.nom = prenom;
        this.role = role;
        this.etabsUser = etabsUser;
    }
    ArrayList<Etablissement> etabsUser;

    private String userSalt;

    private String role;
    private String login;
    private String nom;
    private String prenom;

    @Id
    private Long id;

    private String saltedPass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the saltedPass
     */
    public String getSaltedPass() {
        return saltedPass;
    }

    public void setPass(String newPass) {
        saltedPass = util.init.saltPassWord(this, newPass);
    }

    /**
     * @return the userSalt
     */
    public String getUserSalt() {
        return userSalt;
    }

}
