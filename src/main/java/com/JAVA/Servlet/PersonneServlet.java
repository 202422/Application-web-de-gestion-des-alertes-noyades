package com.JAVA.Servlet;

import com.JAVA.DAO.PersonneDAO;
import com.JAVA.DAO.PersonneDaoImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.EquipDAO;
import com.JAVA.Beans.Equip;
import com.JAVA.Beans.Personne;
import com.JAVA.DAO.DAOException;


import java.io.*;
import java.util.*;

@WebServlet("/PersonneServlet")
public class PersonneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonneDAO personneDAO;
    private EquipDAO equipeDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personneDAO = daoFactory.getPersonneDao();
        equipeDAO = daoFactory.getEquipDao();
    }

   

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                // Récupérer les équipes pour le formulaire
                List<Equip> equipes = equipeDAO.getAll();
                request.setAttribute("equipes", equipes);

                // Rediriger vers le formulaire d'ajout
                request.getRequestDispatcher("addPersonneForm.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int chefEq = Integer.parseInt(request.getParameter("chef_eq"));
        int idEquipe = Integer.parseInt(request.getParameter("id_eq")); // ID obtenu depuis la liste déroulante
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        
        Personne personne = new Personne(0, idEquipe, nom, prenom, chefEq, pass,email);

        try {
            personneDAO.insert(personne);
            response.sendRedirect("EquipeServlet?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
