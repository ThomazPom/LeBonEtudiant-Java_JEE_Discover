/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
    public static class UserPage{
        private int nbPages;
        private int nbresultPage;
        private int pageCourante;
        private Collection<Utilisateur> resultList;

        public UserPage( int nbresSultPage, int pageCourante) {
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
        public Collection<Utilisateur> getResultList() {
            return resultList;
        }
        public void setResultList(Collection<Utilisateur> resultList) {
            this.resultList = resultList;
        } 
    }
    
    public int countAllUsers(){
        Query query = em.createQuery("select COUNT(u) from Utilisateur u");
        return Integer.parseInt(query.getSingleResult().toString());
        
    }
    
    public void getOnePageUser(UserPage userPage)
    {
        Query query = em.createQuery("select u from Utilisateur u");
        query.setFirstResult(userPage.getPageCourante() * userPage.getNbresultPage());
        query.setMaxResults(userPage.getNbresultPage());
        
        int nbUsers = countAllUsers();                 //on recupere le nombre d'utlisateurs
        int NbPages = (int) Math.ceil(nbUsers / userPage.getNbresultPage());
        
        userPage.setNbPages(NbPages);
        userPage.setResultList(query.getResultList());
    }
    
    public List<Utilisateur> getUsers() {
        System.out.println("---->public Collection<Utilisateur> getUsers()");
        return em.createQuery("select u from Utilisateur u").getResultList();
        
        
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
     public List<Utilisateur> getUtilisateursById(List<Long> idUtilisateur) {
         
        System.out.println("-->>getUtilisateursById()");
        if (idUtilisateur.size()==0) {
            return new ArrayList<>();
        }
        Query q = em.createQuery("SELECT r from Utilisateur r WHERE r.id IN :idUtilisateur");
        q.setParameter("idUtilisateur", idUtilisateur);
        return q.getResultList();
    }
    public List<Utilisateur> getUtilisateursById(String[] idUtilisateur) {
        List<Long> arIdsList = new ArrayList<Long>() ;
        for(String s : idUtilisateur)
        {
            try {
                arIdsList.add(Long.parseLong(s));
            } catch (Exception e) {
                System.err.println(s + " is not a valid id ");
            }
        }
        return getUtilisateursById(arIdsList);
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
    
    public Utilisateur creerUser(String login, String passWord, String nom, String prenom, String role,String numtel, List<Etablissement> etabsUser) {
        System.out.println("------>public Utilisateur creerUser(String login, String passWord, String nom, String prenom, String role, Collection<Etablissement> etabsUser)");
        Utilisateur u = new Utilisateur(login, nom, prenom,numtel, role,etabsUser);
        em.persist(u);
        u.setPass(passWord);
        em.persist(u);
        return u;
    }
}
