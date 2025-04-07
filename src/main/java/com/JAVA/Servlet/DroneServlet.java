package com.JAVA.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.JAVA.Beans.Drone;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.DroneDAO;
import com.JAVA.DAO.DroneDaoImpl;

@WebServlet("/DroneServlet")
public class DroneServlet extends HttpServlet {
    private DroneDAO droneDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        droneDAO = new DroneDaoImpl(daoFactory);
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
                case "delete":
                    deleteDrone(request, response);
                    break;
                default:
                    listDrones(request, response);
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
                addDrone(request, response);
            } else if ("update".equals(action)) {
                updateDrone(request, response);
            } else {
                listDrones(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listDrones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Drone> drones = droneDAO.getAll();
        request.setAttribute("drones", drones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("drone-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("drone-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_dr = Integer.parseInt(request.getParameter("id_dr"));
        Drone drone = droneDAO.getAll().stream()
                .filter(d -> d.getId() == id_dr)
                .findFirst().orElse(null);
        request.setAttribute("drone", drone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("drone-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addDrone(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nom = request.getParameter("Nom");
        int pourBat = Integer.parseInt(request.getParameter("Pour_bat"));
        Drone drone = new Drone(0, nom, pourBat);
        droneDAO.add(drone);
        response.sendRedirect("DroneServlet?action=list");
    }

    private void updateDrone(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id_dr = Integer.parseInt(request.getParameter("id_dr"));
        String nom = request.getParameter("Nom");
        int pourBat = Integer.parseInt(request.getParameter("Pour_bat"));
        Drone drone = new Drone(id_dr, nom, pourBat);
        droneDAO.update(drone);
        response.sendRedirect("DroneServlet?action=list");
    }

    private void deleteDrone(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id_dr = Integer.parseInt(request.getParameter("id_dr"));
        droneDAO.delete(id_dr);
        response.sendRedirect("DroneServlet?action=list");
    }
}
