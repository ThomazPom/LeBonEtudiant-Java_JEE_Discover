/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utilisateur;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "StaticServlet", urlPatterns = {"/StaticServlet"})
public class StaticServlet extends HttpServlet {

    @EJB
    private UserController userController;

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
        String action = request.getParameter("action");
        String forwardTo = "";
        String redirect = "index.jsp";
        String message = "";
        String alert = "";

        if (action != null) {
            if (action.equals("connect")) {
                
                
                if (!"".equals(request.getParameter("email"))
                        && request.getParameter("email") != null
                        && !"".equals(request.getParameter("password"))
                        && request.getParameter("password") != null) {

                    Utilisateur userFound = userController.getOneConnect(request.getParameter("email"), request.getParameter("password"));

                    if (userFound != null) {
                        request.getSession(true).setAttribute("userlogged", userFound.getPrenom() + " " + userFound.getNom());
                        request.getSession(false).setAttribute("nom", userFound.getNom());
                        request.getSession(false).setAttribute("prenom", userFound.getPrenom());
                        request.getSession(false).setAttribute("email", userFound.getLogin());
                    }
                    else
                    {
                        System.out.println(" # #  #   ########################\nAUCUN UTILISATEUR CORRESPONDANT");
                    }
                }
            }
        }
        if (!forwardTo.isEmpty()) {
            RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);
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
