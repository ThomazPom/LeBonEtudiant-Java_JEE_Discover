/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AnnonceController;
import controller.CategorieController;
import controller.DepartementController;
import controller.EtablissementController;
import controller.RegionController;
import controller.UserController;
import controller.VilleController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Etablissement;
import model.Utilisateur;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "StaticServlet", urlPatterns = {"/StaticServlet"})
public class StaticServlet extends HttpServlet {

    @EJB
    private UserController userController;
    @EJB
    private EtablissementController etabController;
    @EJB
    private CategorieController categController;
    @EJB
    private VilleController villeController;
    @EJB
    private RegionController regionController;
    @EJB
    private DepartementController deptController;
    @EJB
    private AnnonceController annonController;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("---->protected void processRequest(HttpServletRequest request, HttpServletResponse response)");
        String forwardTo = "";
        String redirect = ".";
        String message = "";

        String action = (request.getParameter("action") == null) ? "entry" : request.getParameter("action");

        if (action.equals("entry")) {
            request.setAttribute("opt_etab", etabController.getEtablissements());
            request.setAttribute("opt_ville", villeController.getVilles());
            request.setAttribute("opt_dept", deptController.getDepartements());
            request.setAttribute("opt_region", regionController.getRegions());
            request.setAttribute("opt_categ", categController.getCategories());
            forwardTo = "entry.jsp";
        }

        if (action.equals("registerUser")) {

            if (!"".equals(request.getParameter("email"))
                    && request.getParameter("email") != null
                    && !"".equals(request.getParameter("password"))
                    && request.getParameter("password") != null
                    && !"".equals(request.getParameter("nom"))
                    && request.getParameter("nom") != null
                    && !"".equals(request.getParameter("prenom"))
                    && request.getParameter("prenom") != null
                    && !"".equals(request.getParameter("telephonne"))
                    && request.getParameter("telephonne") != null) {

                if (userController.getOneLogin(request.getParameter("email")) != null) {
                    request.getSession(false).setAttribute("danger", "Impossible d'enregistrer ce compte, car cet email est déja inscrit");
                } else if (util.validator.validateEmail(request.getParameter("email"))) {
                    List<Etablissement> etabsNewUser = new ArrayList<Etablissement>();
                    if (request.getParameterValues("registerRegionSelect") != null) {
                        etabsNewUser = etabController.getEtablissementsById(request.getParameterValues("registerRegionSelect"));

                    }
                    userController.creerUser(
                            request.getParameter("email"),
                            request.getParameter("password"),
                            request.getParameter("nom"),
                            request.getParameter("prenom"),
                            "ROLEUSER",
                            request.getParameter("telephonne"),
                            etabsNewUser
                    );
                    action = "connect";
                } else {
                    request.getSession(false).setAttribute("danger", "L'email envoyé à l'inscription (" + request.getParameter("email") + ") est invalide");

                }

            } else {
                request.getSession(false).setAttribute("warning", "Les champs obligatoires d'inscription n'ont pas été correctement remplis");
            }

        }
        System.out.println(action);
        if (action.equals("listAdsPagination") || action.equals("listUtilisateurs")) {
            System.out.println("if(action.equals(\"listAdsPagination\")  || action.....");
            if (request.getSession(true).getAttribute("userlogged") == null) {
                request.setAttribute("opt_etab", etabController.getEtablissements());
            }
            if (action.equals("listAdsPagination")) {
                AnnonceController.AnnoncePage wrapListPage = new AnnonceController.AnnoncePage(15, 0);
                annonController.getOnePageAds(wrapListPage);
                request.setAttribute("wrapListPage", wrapListPage);
            } else {
                UserController.UserPage wrapListPage = new UserController.UserPage(15, 0);
                userController.getOnePageUser(wrapListPage);
                request.setAttribute("wrapListPage", wrapListPage);
            }
            forwardTo = "suivi.jsp";

        }
        if (action.equals("connect")) {
            System.out.println("if (action.equals(\"connect\"))");

            if (!"".equals(request.getParameter("email"))
                    && request.getParameter("email") != null
                    && !"".equals(request.getParameter("password"))
                    && request.getParameter("password") != null) {

                Utilisateur userFound = userController.getOneConnect(request.getParameter("email"), request.getParameter("password"));

                if (userFound != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userlogged", userFound.getPrenom() + " " + userFound.getNom());
                    session.setAttribute("nom", userFound.getNom());
                    session.setAttribute("prenom", userFound.getPrenom());
                    session.setAttribute("email", userFound.getLogin());
                    session.setAttribute("success", "Heureux de vous revoir, " + userFound.getPrenom() + " ! <i class=\"fa fa-smile-o\"></i>");
                    session.setAttribute("userID", userFound.getId());
                    session.setAttribute("userObject", userFound);

                } else {

                    HttpSession session = request.getSession(false);
                    session.setAttribute("danger", "Impossible de se connecter : L'email ou le mot de passe est incorrect");

                }
            }

        } else if (action.equals("disconnect")) {
            System.out.println("if (action.equals(\"disconnect\"))");
            HttpSession session = request.getSession(true);
            session.setAttribute("userlogged", null);
            session.setAttribute("nom", null);
            session.setAttribute("prenom", null);
            session.setAttribute("email", null);
            session.setAttribute("userID", null);
            session.setAttribute("userObject", null);
            session.invalidate();
        }
        if (request.getSession(true).getAttribute("userlogged") != null) {
            //Code secuisé ici;
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
