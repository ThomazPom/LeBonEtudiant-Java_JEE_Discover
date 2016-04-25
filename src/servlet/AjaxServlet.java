/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import controller.AnnonceController;
import controller.CategorieController;
import controller.EtablissementController;
import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
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
        //  UserController.UserPage userPage = new UserController.UserPage(10, 20);
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
            if (action.equals("lst_etab")) {
                System.out.println("In action " + action);
                request.setAttribute("lst_etab", etabController.getEtablissements());
                forwardTo = "ajax/listEtab.jsp";
            }

            if (action.equals("listAllAnnonces")) {
                System.out.println("In action " + action);
                request.setAttribute("annonces", annonController.getAnnonces());
                forwardTo = "ajax/listAnnonces.jsp";
            }
            if (action.equals("3randAnnonce")) {
                System.out.println("In action " + action);
                List<Annonce> listann = annonController.getAnnonces();
                Random rand = new Random();
                request.setAttribute("annonces", Arrays.asList(listann.get(rand.nextInt(listann.size())), listann.get(rand.nextInt(listann.size())), listann.get(rand.nextInt(listann.size()))));
                forwardTo = "ajax/listAnnonces.jsp";
            }
            if (action.equals("searchAnnonce")) {
                System.out.println("In action " + action);

                if (request.getParameterValues("etabSelectSearch") != null) {

                    List<Annonce> listann = annonController.searchAnnonce(action, action, action, action, request.getParameterValues("etabSelectSearch"), null, null, action);
                    request.setAttribute("annonces", listann);
                    forwardTo = "ajax/listAnnonces.jsp";
                } else {
                    System.err.println("request.getParameterValues(\"etabSelectSearch\")NULL");
                }
            }

            if (action.equals("opt_categ")) {
                System.out.println("In action " + action);
                request.setAttribute("opt_categ", categController.getCategories());
                forwardTo = "ajax/opt_categ.jsp";
            }

            if (action.equals("listAdsPagination")) {
                //code pour afficher la pagination des annonces
                System.out.println("In action " + action);
                int nbResultPage = 10;
                int pageCourante = 0;

                try {
                    nbResultPage = Integer.parseInt(request.getParameter("nbResultPage"));
                    pageCourante = Integer.parseInt(request.getParameter("pageCourante"));
                } catch (Exception e) {
                    System.out.println("nbresultpage or pagecourante : NaN");
                }
                AnnonceController.AnnoncePage annoncePage = new AnnonceController.AnnoncePage(nbResultPage, pageCourante);
                annonController.getOnePageAds(annoncePage);

                request.setAttribute("wrapListPage", annoncePage);
                forwardTo = "ajax/listAdsPagination.jsp";
            }

            if (action.equals("listUtilisateurs")) {
                //code pour afficher la pagination des annonces
                System.out.println("In action " + action);
                int nbResultPage = 10;
                int pageCourante = 0;

                try {
                    nbResultPage = Integer.parseInt(request.getParameter("nbResultPage"));
                    pageCourante = Integer.parseInt(request.getParameter("pageCourante"));
                } catch (Exception e) {
                    System.out.println("nbresultpage or pagecourante : NaN");
                }
                UserController.UserPage userPage = new UserController.UserPage(nbResultPage, pageCourante);
                userController.getOnePageUser(userPage);

                request.setAttribute("wrapListPage", userPage);
                forwardTo = "ajax/listUtilisateurs.jsp";
            }

            if (request.getSession(true).getAttribute("userlogged") != null) {
                //Code securisé ici;

                if (action.equals("sendAnnonce")) {
                    //request.getSession(false).setAttribute("danger", "Il y a eu un probleme...");
                    forwardTo = "ajax/erreurAnnonce.jsp";

                    if (request.getParameter("idAnnonce") != null
                            && request.getParameter("titre") != null
                            && request.getParameter("description") != null
                            && request.getParameter("telephone") != null
                            && request.getParameter("email") != null
                            && request.getParameter("date-fin") != null
                            && request.getParameterValues("etabSelectAnnonce") != null
                            && request.getParameter("amountAnnonce") != null
                            && request.getParameterValues("typeAnnonce") != null) {

                        if (request.getParameterValues("etabSelectAnnonce").length > 0
                                && request.getParameterValues("categSelect-annonce").length > 0
                                && !request.getParameter("titre").isEmpty()
                                && !request.getParameter("description").isEmpty()
                                && !request.getParameter("amountAnnonce").isEmpty()
                                && !request.getParameter("typeAnnonce").isEmpty()) {

                            Utilisateur userAnnonce = userController.getOneLogin(request.getSession(false).getAttribute("email").toString());
                            Annonce annonce = annonController.getAnnonceById(request.getParameter("idAnnonce"));
                              
                            if (annonce == null) {
                                annonce = annonController.creerAnnonce(userAnnonce,
                                        request.getParameter("titre"),
                                        request.getParameter("amountAnnonce"),
                                        request.getParameter("telephone"),
                                        request.getParameter("email"),
                                        request.getParameter("description"),
                                        request.getParameter("date-fin"),
                                        true,
                                        request.getParameterValues("categSelect-annonce"),
                                        request.getParameterValues("etabSelectAnnonce"),
                                        request.getParameter("typeAnnonce")
                                );
                            } else if (userAnnonce.getId()==annonce.getProprietaire().getId()) {
                                annonController.majAnnonce(annonce,
                                        request.getParameter("titre"),
                                        request.getParameter("amountAnnonce"),
                                        request.getParameter("telephone"),
                                        request.getParameter("email"),
                                        request.getParameter("description"),
                                        request.getParameter("date-fin"),
                                        true,
                                        request.getParameterValues("categSelect-annonce"),
                                        request.getParameterValues("etabSelectAnnonce")
                                );
                            }

                            if (annonce != null) {
                                //System.err.println("eq(\"userObject\") " + request.getSession(false).getAttribute("userObject").equals(annonce.getProprietaire()));

                                request.getSession(false).setAttribute("success", "Félicitations ! Ton annonce est en ligne !<br/>Voici à quoi elle ressemble :");

                                forwardTo = "ajax/confirmAnnonce.jsp";
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
