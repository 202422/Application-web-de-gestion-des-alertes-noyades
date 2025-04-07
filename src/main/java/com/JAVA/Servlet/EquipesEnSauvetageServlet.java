package com.JAVA.Servlet;

import com.JAVA.DAO.EquipDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.JAVA.Beans.Equip;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.EquipDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/equipesEnSauvetage")
public class EquipesEnSauvetageServlet extends HttpServlet {
    private EquipDAO equipeDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        equipeDAO = new EquipDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            ArrayList<Equip> equipes = equipeDAO.getEquipesEnSauvetage();
            request.setAttribute("equipes", equipes);
            request.getRequestDispatcher("equipesEnSauvetage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des équipes.");
        }
    }
}
