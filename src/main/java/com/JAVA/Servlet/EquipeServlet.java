package com.JAVA.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.JAVA.Beans.Equip;
import com.JAVA.Beans.Personne;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.EquipDAO;
import com.JAVA.DAO.EquipDaoImpl;
import com.JAVA.DAO.PersonneDAO;
import com.JAVA.DAO.PersonneDaoImpl;

/**
 * Servlet implementation class EquipeSecoursServlet
 */
@WebServlet("/EquipeServlet")
public class EquipeServlet extends HttpServlet {
    private EquipDAO EquipDAO;
    private PersonneDAO personneDAO; // Ajout de PersonneDAO

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        EquipDAO = new EquipDaoImpl(daoFactory);
        personneDAO = new PersonneDaoImpl(daoFactory); // Initialisation de PersonneDAO

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "addForm":
                    showAddForm(request, response);
                    break;
                case "updateForm":
                    showUpdateForm(request, response);
                    break;
                case "details":
                    showEquipeDetails(request, response);
                    break;
                case "delete":
                    deleteEquipe(request, response);
                    break;
                default:
                    listEquipes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                addEquipe(request, response);
            } else if ("update".equals(action)) {
                updateEquipe(request, response);
            } else {
                listEquipes(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listEquipes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Equip> equipes = EquipDAO.getAll();
        request.setAttribute("equipes", equipes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipe-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipe-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_eq = Integer.parseInt(request.getParameter("id_eq"));
        Equip equipe = EquipDAO.getAll().stream()
                .filter(e -> e.getId() == id_eq)
                .findFirst().orElse(null);
        request.setAttribute("equipe", equipe);
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipe-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addEquipe(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nom = request.getParameter("nom");
        Equip equipe = new Equip(0, nom, 0);
        EquipDAO.create(equipe);
        response.sendRedirect("EquipeServlet?action=list");
    }

    private void updateEquipe(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id_eq = Integer.parseInt(request.getParameter("id_eq"));
        String nom = request.getParameter("nom");
        int taille = Integer.parseInt(request.getParameter("taille"));
        Equip equipe = new Equip(id_eq, nom, taille);
        EquipDAO.update(equipe);
        response.sendRedirect("EquipeServlet?action=list");
    }

    private void deleteEquipe(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id_eq = Integer.parseInt(request.getParameter("id_eq"));
        EquipDAO.delete(id_eq);
        response.sendRedirect("EquipeServlet?action=list");
    }
    private void showEquipeDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_eq = Integer.parseInt(request.getParameter("id_eq")); // Récupérer l'ID de l'équipe
        ArrayList<Personne> membres = null;

        try {
            // Récupérer les membres de l'équipe via PersonneDAO
            membres = personneDAO.getPersonnesByEquipe(id_eq);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la récupération des membres", e);
        }

        for (Personne membre : membres) {
            System.out.println("ID: " + membre.getId() + ", Nom: " + membre.getNom() + ", Chef_eq: " + membre.getChef_eq());
        }
        // Ajouter les membres comme attribut pour la JSP
        request.setAttribute("membres", membres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("equipe-details.jsp");
        dispatcher.forward(request, response); // Redirection vers le JSP
    }


}
