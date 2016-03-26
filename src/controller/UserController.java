/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Etablissement;
import model.Utilisateur;
import util.init;

/**
 *
 * @author Thomas
 */
@Stateless
public class UserController {
    
    public Collection<Utilisateur> getUsers() {
        System.out.println("---->public Collection<Utilisateur> getUsers()");
        Query q = em.createQuery("select u from Utilisateur u");
        
        Collection<Utilisateur> result = q.getResultList();
        Iterator<Utilisateur> iterator = result.iterator();
        Utilisateur checkedUser = null;
        
        if (!result.isEmpty()) {
            while (iterator.hasNext()) {
                checkedUser = iterator.next();
                System.out.println(checkedUser.toString());
            }
        }
        
        System.out.println("###########ALL RESULT SIZE{{" + result.size() + "}}#############");
        return result;
    }
    
    public Utilisateur getOneLogin(String email ) {
        System.out.println("------>public Utilisateur getOneLogin(String email )");
        Query q = em.createQuery("select u from Utilisateur u where u.login =:login");
        q.setParameter("login", email.toLowerCase());
        Collection<Utilisateur> result = q.getResultList();
        Iterator<Utilisateur> iterator = result.iterator();
        
        if (!result.isEmpty()) {
            return iterator.next();
        }
        return null;
    }
    public Utilisateur getOneConnect(String email, String password) {
        System.out.println("------>public Utilisateur getOneConnect(String email, String password)");
        Query q = em.createQuery("select u from Utilisateur u where u.login =:login");
        q.setParameter("login", email.toLowerCase());
        Collection<Utilisateur> result = q.getResultList();
        
//        System.out.println("############################################");
//        System.out.println("sEmail-" + email);
        System.out.println("###########RESULT SIZE{{" + result.size() + "}}#############");
//        
        Iterator<Utilisateur> iterator = result.iterator();
        Utilisateur checkedUser = null;
        
        if (!result.isEmpty()) {
            while (iterator.hasNext()) {
                checkedUser = iterator.next();
//                System.out.println("############################################");
//                System.out.println("sEmail-" + email);
//                System.out.println("uEmail-" + checkedUser.getLogin());
//                System.out.println("sSalt-" + init.saltPassWord(checkedUser, password));
//                System.out.println("uSalt-" + checkedUser.getSaltedPass());
//                System.out.println("############################################");
//                
                if (init.saltPassWord(checkedUser, password).equals(checkedUser.getSaltedPass())) {
                    
                    return checkedUser;
                    
                }
            }
        }
        return null;
    }
    @PersistenceContext
    private EntityManager em;
    
    public Utilisateur creerUser(String login, String passWord, String nom, String prenom, String role,String numtel, Collection<Etablissement> etabsUser) {
        System.out.println("------>public Utilisateur creerUser(String login, String passWord, String nom, String prenom, String role, Collection<Etablissement> etabsUser)");
        Utilisateur u = new Utilisateur(login, nom, prenom,numtel, role, etabsUser);
        em.persist(u);
        u.setPass(passWord);
        em.persist(u);
        return u;
    }
}
