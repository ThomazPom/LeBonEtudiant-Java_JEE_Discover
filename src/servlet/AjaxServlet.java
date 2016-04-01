/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AnnonceController;
import controller.CategorieController;
import controller.EtablissementController;
import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Annonce;
import model.Utilisateur;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "AjaxServlet", urlPatterns = {"/AjaxServlet"})
public class AjaxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private UserController userController;
    @EJB
    private EtablissementController etabController;
    @EJB
    private CategorieController categController;
    @EJB
    private AnnonceController annonController;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forwardTo = "";
        String message = "";
        String redirect = "index.jsp";
       // int i =Integer.parseInt("a500"); //test de l'erreur 500
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("opt_etab")) {

                System.out.println("In action " + action);
                request.setAttribute("opt_etab", etabController.getEtablissements());
                forwardTo = "ajax/opt_etab.jsp";
            }

            if (action.equals("opt_categ")) {
                System.out.println("In action " + action);
                request.setAttribute("opt_categ", categController.getCategories());
                forwardTo = "ajax/opt_categ.jsp";
            }

            if (request.getSession(true).getAttribute("userlogged") != null) {
                //Code secuisé ici;

                if (action.equals("sendVente")) {
                    forwardTo = "ajax/erreurVente.jsp";

                    if (request.getParameter("idVenteAnnonce") != null
                            && request.getParameter("titre") != null
                            && request.getParameter("description") != null
                            && request.getParameter("telephone") != null
                            && request.getParameter("email") != null
                            && request.getParameterValues("regionSelect-vente") != null
                            && request.getParameter("amount-vente") != null
                            && request.getParameterValues("categSelect-vente") != null) {

                        if (request.getParameterValues("regionSelect-vente").length > 0
                                && request.getParameterValues("categSelect-vente").length > 0
                                && !request.getParameter("titre").isEmpty()
                                && !request.getParameter("description").isEmpty()
                                && !request.getParameter("amount-vente").isEmpty()) {
                            // Arrays.asList(request.getPAr)
                            Utilisateur userAnnonce = userController.getOneLogin(request.getSession(false).getAttribute("email").toString());
                            Annonce annonce = annonController.creerAnnonce(userAnnonce,
                                    request.getParameter("titre"),
                                    request.getParameter("amount-vente"),
                                    request.getParameter("telephone"),
                                    request.getParameter("email"),
                                    request.getParameter("description"),
                                    true,
                                    request.getParameterValues("categSelect-vente"),
                                    request.getParameterValues("regionSelect-vente")
                            );
                            if (annonce != null) {
                               request.getSession(false).setAttribute("success", "Félicitations ! Ton annonce est en ligne !<br/>Voici à quoi elle ressemble :");
                
                                forwardTo = "ajax/confirmVente.jsp";
                                   request.setAttribute("annonce", annonce);

                            }
                        }

                    }
                }

            }
        }

        if (!forwardTo.isEmpty()) {
            System.out.println("Forward to " + forwardTo);
            RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "?message=" + message);
            dp.forward(request, response);
        } else {
            response.sendRedirect(redirect);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
