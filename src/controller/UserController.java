/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Etablissement;
import model.User;

/**
 *
 * @author Thomas
 */
@Stateless
public class UserController {
  @PersistenceContext
    private EntityManager em;

  public User creerUser(String login, String passWord, String nom, String prenom, String role, ArrayList<Etablissement> etabsUser)
  {
      User u = new User(login, passWord, nom, prenom, role, etabsUser);
      em.persist(u);
      return u;
  }   
}
