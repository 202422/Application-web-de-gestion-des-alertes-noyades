package com.JAVA.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.JAVA.DAO.DAOFactory;
import com.JAVA.Beans.AlerteNoy;
import com.JAVA.Beans.MaitNag;
import com.JAVA.DAO.AlerteNoyDAO;
import com.JAVA.DAO.AlerteNoyDaoImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@WebServlet("/AlerteNoyadeServlet2")
public class AlerteNoyadeServlet2 extends HttpServlet {
    private AlerteNoyDAO alerteDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        alerteDAO = new AlerteNoyDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "setEnCours":
                    setEnCours(request, response);
                    break;
                default:
                    listAlertes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listAlertes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<AlerteNoy> alertes = alerteDAO.getAll();
        
        // Trier les alertes : "Non Traité" en premier
        Collections.sort(alertes, new Comparator<AlerteNoy>() {
            @Override
            public int compare(AlerteNoy a1, AlerteNoy a2) {
                // Si "etat" est "Non Traité", on le met en premier
                if (a1.getEtat().equals("Non Traité") && !a2.getEtat().equals("Non Traité")) {
                    return -1;
                } else if (!a1.getEtat().equals("Non Traité") && a2.getEtat().equals("Non Traité")) {
                    return 1;
                }
                return 0; // Sinon, garder l'ordre actuel
            }
        });
        
        request.setAttribute("alertes", alertes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("alertenoyadelist.jsp");
        dispatcher.forward(request, response);
    }

    private void setEnCours(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer l'ID de l'alerte depuis les paramètres
            int id_al_N = Integer.parseInt(request.getParameter("id"));

            // Appeler la méthode DAO pour mettre à jour l'état
            alerteDAO.setEnCours(id_al_N);

            // Rediriger vers la liste des alertes après la mise à jour
            response.sendRedirect("AlerteNoyadeServlet2");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la mise à jour de l'état de l'alerte", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("add")) {
        	
        	HttpSession session = request.getSession();
        	MaitNag maitre = (MaitNag) session.getAttribute("Mait_sess");
        	
        	LocalDate date_form = LocalDate.parse(request.getParameter("date"));
        	
        	LocalTime time_form = LocalTime.parse(request.getParameter("heure"));
        	
        	float lat = Float.parseFloat(request.getParameter("latitude"));
        	
        	float lon = Float.parseFloat(request.getParameter("longitude"));

        	int nb = Integer.parseInt(request.getParameter("nombre_personnes"));
        	
        	
        	
        	AlerteNoy alerte_n = new AlerteNoy(0,0,0,date_form,time_form,nb,"Non Traité",null,lat,lon,maitre.getId());
        	
        	alerteDAO.create(alerte_n);
        	
        	request.getRequestDispatcher("addalertenoyadesuc.jsp").forward(request, response);
        	
        }

    }
    
}
