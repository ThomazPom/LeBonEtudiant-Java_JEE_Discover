/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import controller.AnnonceController;
import controller.CategorieController;
import controller.DepartementController;
import controller.EtablissementController;
import controller.RegionController;
import controller.UserController;
import controller.VilleController;
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
import model.Etablissement;
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
    @EJB
    private RegionController regionController;
    @EJB
    private VilleController villeController;
    @EJB
    private DepartementController deptController;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forwardTo = "";
        String message = "";
        String redirect = ".";
        // int i =Integer.parseInt("a500"); //test de l'erreur 500
        String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");
        System.out.println("Action : " + action);
        if (action.equals("opt_etab")) {

            System.out.println("In action " + action);
            request.setAttribute("opt_etab", etabController.getEtablissements());
            forwardTo = "ajax/opt_etab.jsp";
        }
        if (action.equals("opt_ville")) {

            System.out.println("In action " + action);
            request.setAttribute("opt_ville", villeController.getVilles());
            forwardTo = "ajax/opt_ville.jsp";
        }
        if (action.equals("opt_dept")) {

            System.out.println("In action " + action);
            request.setAttribute("opt_dept", deptController.getDepartements());
            forwardTo = "ajax/opt_dept.jsp";
        }
        if (action.equals("opt_region")) {

            System.out.println("In action " + action);
            request.setAttribute("opt_region", regionController.getRegions());
            forwardTo = "ajax/opt_region.jsp";
        }
        if (action.equals("lst_etab")) {
            System.out.println("In action " + action);
            request.setAttribute("lst_etab", etabController.getEtablissements());
            forwardTo = "ajax/listEtab.jsp";
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

            String[] idEtabs = ((request.getParameter("etabSelectSearch") != null) ? request.getParameterValues("etabSelectSearch") : new String[0]);
            String[] idCategs = ((request.getParameter("categSelectSearch") != null) ? request.getParameterValues("categSelectSearch") : new String[0]);
            String[] idRegions = ((request.getParameter("regionSelectSearch") != null) ? request.getParameterValues("regionSelectSearch") : new String[0]);
            String[] idDepts = ((request.getParameter("deptSelectSearch") != null) ? request.getParameterValues("deptSelectSearch") : new String[0]);
            String[] idVilles = ((request.getParameter("villeSelectSearch") != null) ? request.getParameterValues("villeSelectSearch") : new String[0]);

            List<Annonce> listann = annonController.searchAnnonce(request.getParameter("searchtitre"),
                    request.getParameter("radioSelecTypAnn"), request.getParameter("prixmin-search"), request.getParameter("prixmax-search"), idEtabs, idVilles, idDepts, idRegions, idCategs);
            request.setAttribute("annonces", listann);

            forwardTo = "ajax/listAnnonces.jsp";
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
        if (request.getSession(true).getAttribute("userlogged") == null) {
            if (action.equals("annonce" ) || action.equals("utilisateur")) {
                request.setAttribute("opt_etab", etabController.getEtablissements());
                forwardTo = "ajax/needToConnect.jsp";
            }
        } else if (request.getSession(true).getAttribute("userlogged") != null) {
            //Code securisé ici;
            System.out.println("###################");
            System.out.println(request.getSession(false).getAttribute("email").toString());
            System.out.println("##########");
            Utilisateur userLogged = userController.getOneLogin(request.getSession(false).getAttribute("email").toString());

            if (action.equals("listAllAnnonces")) {
                System.out.println("In action " + action);

                request.setAttribute("annonces", request.getParameter("forUser") == null ? annonController.getAnnonces() : userLogged.getAnnonces());
                forwardTo = "ajax/listAnnonces.jsp";
            }
            if (action.equals("majUtilisateur"))// majUtilisateur
            {
                forwardTo = "ajax/generalAnnonce.jsp";
                if (!"".equals(request.getParameter("email"))
                        && request.getParameter("email") != null
                        && !"".equals(request.getParameter("nom"))
                        && request.getParameter("nom") != null
                        && !"".equals(request.getParameter("prenom"))
                        && request.getParameter("prenom") != null
                        && !"".equals(request.getParameter("telephonne"))
                        && request.getParameter("telephonne") != null) {

                    Utilisateur utilToEdit = userLogged;
                    Boolean userIsAdmin = userLogged.getRole().equals("Administrateur");
                    Boolean isThatUser = true;
                    Boolean hasRights = true;

                    if (request.getParameter("idUtilisateur") != null) {
                        Utilisateur utilAskedForUpdate = userController.getUtilisateurById(request.getParameter("idUtilisateur"));
                        if (utilAskedForUpdate != null) {
                            isThatUser = userLogged.getId().equals(utilAskedForUpdate.getId());
                            hasRights = isThatUser || userIsAdmin;
                            if (hasRights) {
                                utilToEdit = utilAskedForUpdate;
                            }
                        }
                    }
                    if (hasRights) {

                        if (!"".equals(request.getParameter("password"))
                                && request.getParameter("password") != null) {
                            if (request.getParameter("password").equals(request.getParameter("confirm_password"))) {
                                utilToEdit.setPass(request.getParameter("password"));
                            } else {
                                request.getSession(false).setAttribute("danger", "Le mot de passe n'a pas été mis à jour : la confirmation ne correspondait pas");
                            }
                        }

                    }
                    utilToEdit = userController.majUtilisateur(utilToEdit,
                            request.getParameter("email"),
                            request.getParameter("nom"),
                            request.getParameter("prenom"),
                            request.getParameter("telephonne"),
                            userIsAdmin ? null : request.getParameter("role"),
                            request.getParameterValues("registerRegionSelect"));
                    request.getSession(false).setAttribute("success", "Les modifications on été prises en compte");
                    if (!isThatUser) {
                        forwardTo = "ajax/confirmUtilisateur.jsp";
                    } else {
                        request.getSession(false).setAttribute("userlogged", utilToEdit.getPrenom() + " " + utilToEdit.getNom());
                        request.getSession(false).setAttribute("nom", utilToEdit.getNom());
                        request.getSession(false).setAttribute("prenom", utilToEdit.getPrenom());
                        request.getSession(false).setAttribute("email", utilToEdit.getLogin());
                        request.getSession(false).setAttribute("telephone", utilToEdit.getNumtel());

                        request.getSession(false).setAttribute("info", "Rafraichissement dans 5 secondes ... <meta http-equiv='refresh' content='5'>");
                        forwardTo = "ajax/generalAnnonce.jsp";
                    }

                }

            }

            if (action.equals("utilisateur")) {
                Utilisateur userToView = null;

                userToView = request.getParameter("loginToView") == null ? userLogged : userController.getOneLogin(request.getParameter("loginToView"));
                userToView = userToView == null ? userLogged : userToView;
                String typeres = (request.getParameter("typeres") == null) ? "edit" : request.getParameter("typeres");
                Boolean canEdit = userLogged.getRole().equals("Administrateur") || userLogged.getId().equals(userToView.getId());
                request.setAttribute("opt_etab", etabController.getEtablissements());
                forwardTo = typeres.equals("edit")
                        ? "ajax/form_edit_user.jsp" : "ajax/confirmUser.jsp";

                request.setAttribute("userToView", userToView);
                request.setAttribute("isUserTheLoggedOne", canEdit);
            }
            if (action.equals("annonce")) {
                System.out.println("#######ANNONCE##################");

                String idAnnonce = (request.getParameter("idAnnonce") == null) ? "-1" : request.getParameter("idAnnonce");
                String typeAnnonce = (request.getParameter("typeAnnonce") == null) ? "vente" : request.getParameter("typeAnnonce");
                String typeres = (request.getParameter("typeres") == null) ? "edit" : request.getParameter("typeres");
                System.out.println("idAnnonce" + idAnnonce);
                System.out.println("typeAnnonce" + typeAnnonce);
                System.out.println("typeres" + typeres);
                Annonce ann = annonController.getAnnonceById(idAnnonce);
                request.setAttribute("annonce", ann);
                request.setAttribute("opt_etab", etabController.getEtablissements());
                request.setAttribute("opt_categ", categController.getCategories());
                boolean isUserProprietaire = ann == null ? false : userLogged.getId().equals(ann.getProprietaire().getId());
                Boolean canEdit = isUserProprietaire || userLogged.getRole().equals("Administrateur");

                forwardTo = typeres.equals("edit")
                        ? ann == null
                                ? typeAnnonce.equals("vente") ? "ajax/form_vente.jsp" : "ajax/form_demande.jsp"
                                : ann.isTypeVente() ? "ajax/form_vente.jsp" : "ajax/form_demande.jsp"
                        : ann == null ? "." : "ajax/confirmAnnonce.jsp";
                if (isUserProprietaire) {
                    request.getSession(false).setAttribute("success", "Vous êtes le propriétaire de cette annonce, " + userLogged.getPrenom() + " <i class=\"fa fa-smile-o\"></i>");
                }
                if (ann != null) {
                    Etablissement et = ann.getEtablissements().get(0);
                    request.setAttribute("isUserProprietaire", canEdit);
                    System.out.println(userLogged.toString() + userLogged.getRole() + isUserProprietaire + " " + canEdit);
                    if (!ann.isActive()) {
                        if (!canEdit) {
                            forwardTo = "ajax/generalAnnonce.jsp";
                            request.getSession(false).setAttribute("html2", "<div style='padding:20px;'><button data-dismiss=\"modal\" class=\"btn btn-success\">Fermer cette page</button></div>");
                        }
                        request.getSession(false).setAttribute("warning", "Cette annonce a été désactivée.");

                    } else if (ann.isActive()) {
                        request.getSession(false).setAttribute("info", "<b>" + ann.getType() + " en région " + et.getVille().getDepartement().getRegion().getLibelle()
                                + " ( " + et.getVille().getLibelle() + ", " + et.getVille().getDepartement().getLibelle() + " ): " + ann.getTitre() + "</b>");
                    }

                }

            }
            if (action.equals("disableAnnonce")) {
                forwardTo = "ajax/generalAnnonce.jsp";
                if (request.getParameter("idAnnonce") != null && userLogged != null) {
                    Annonce annonce = annonController.getAnnonceById(request.getParameter("idAnnonce"));

                    if (annonce != null && userLogged.getId().equals(annonce.getProprietaire().getId()) || userLogged.getRole().equals("Administrateur")) {

                        Boolean active = !"false".equals(request.getParameter("activeAnnonce"));
                        String formReactive = "<form style='display: inline;' class='alert alert-success' role='alert' method='post' name='formVente' action='AjaxServlet'>\n"
                                + (active ? "L'annonce est à présent re-activée " : "L'annonce a bien été supprimée !")
                                + "    <input required='' value='" + annonce.getId() + "' name='idAnnonce' hidden/>\n"
                                + "    <input required name='activeAnnonce' value='" + !active + "' hidden/>\n"
                                + "    <input required name='action' value='disableAnnonce' hidden/>\n"
                                + "    <input type='submit' class='btn btn-primary' value='" + (active ? "Désactiver" : "Annuler") + "?'>\n"
                                + "</form>";

                        request.getSession(false).setAttribute("html", formReactive);
                        annonController.majAnnonce(annonce, null, null, null, null, null, null,
                                !"false".equals(request.getParameter("activeAnnonce")), new String[0], new String[0]);

                    }
                }
            }
            if (action.equals("sendAnnonce")) {
                //request.getSession(false).setAttribute("danger", "Il y a eu un probleme...");
                forwardTo = "ajax/generalAnnonce.jsp";

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
                            && userLogged != null
                            && request.getParameterValues("categSelect-annonce").length > 0
                            && !request.getParameter("titre").isEmpty()
                            && !request.getParameter("description").isEmpty()
                            && !request.getParameter("amountAnnonce").isEmpty()
                            && !request.getParameter("typeAnnonce").isEmpty()) {

                        Annonce annonce = annonController.getAnnonceById(request.getParameter("idAnnonce"));

                        if (annonce == null) {
                            annonce = annonController.creerAnnonce(userLogged,
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
                        } else if (userLogged.getId().equals(annonce.getProprietaire().getId()) || userLogged.getRole().equals("Administrateur")) {
                            annonController.majAnnonce(annonce,
                                    request.getParameter("titre"),
                                    request.getParameter("amountAnnonce"),
                                    request.getParameter("telephone"),
                                    request.getParameter("email"),
                                    request.getParameter("description"),
                                    request.getParameter("date-fin"),
                                    !"false".equals(request.getParameter("activeAnnonce")),
                                    request.getParameterValues("categSelect-annonce"),
                                    request.getParameterValues("etabSelectAnnonce")
                            );
                        }

                        if (annonce != null) {
                            //System.err.println("eq(\"userObject\") " + request.getSession(false).getAttribute("userObject").equals(annonce.getProprietaire()));

                            request.getSession(false).setAttribute("success", "Félicitations ! Ton annonce est en ligne !<br/>Voici à quoi elle ressemble :");

                            forwardTo = "ajax/confirmAnnonce.jsp";
                            System.out.println("###CONFIRM ANNONCE APRES MAJ/CREA");
                            request.setAttribute("isUserProprietaire", userLogged.getId().equals(annonce.getProprietaire().getId()) || userLogged.getRole().equals("Administrateur"));
                            request.setAttribute("annonce", annonce);

                        }
                    }

                }
            }

        }

        if (!forwardTo.isEmpty()) {
            System.out.println("Forward to " + forwardTo + request.getParameterMap());
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
