package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.JAVA.Beans.AlerteNoy;
import com.JAVA.Beans.Drone;
import com.JAVA.Beans.Personne;
import com.JAVA.DAO.AlerteNoyDAO;
import com.JAVA.DAO.DAOConfigurationException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.DroneDAO;

/**
 * Servlet implementation class Consulter_Alerte
 */
@WebServlet("/Consulter_Alerte")
public class Consulter_Alerte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private AlerteNoyDAO alertenoyDao;
	private DroneDAO dronedao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Consulter_Alerte() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
        try {
            // Initialisation de DAOFactory et du DAO de specialite
            DAOFactory daoFactory = DAOFactory.getInstance();
            this.alertenoyDao = daoFactory.getAlerteNoyDao();
            this.dronedao = daoFactory.getDroneDao();
        
        } catch (DAOConfigurationException e) {
            throw new ServletException(e);
        }
    }   
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Personne pers = (Personne) session.getAttribute("Pers_sess");
		
		action = request.getParameter("action");
		
		if(action == null || action.equals("list")) {
			
				
			
			
			// Récupération des alertes en cours et des drones
			ArrayList<AlerteNoy> liste_al = alertenoyDao.get_encours();
			
			ArrayList<Drone> liste_dr = dronedao.getAll();
			
			request.setAttribute("lst_al", liste_al);
			request.setAttribute("lst_dr", liste_dr);
			
			request.getRequestDispatcher("Consulter_al.jsp").forward(request, response);
			
			
		}
		else if(action.equals("repondre")) {
			
			if(alertenoyDao.getOne_ensauv(pers.getId()) == null) {
			
			int id_al_nform = Integer.parseInt(request.getParameter("id_aln"));
			
			
			alertenoyDao.update_to_sauvetage(id_al_nform, pers.getId());
			request.getRequestDispatcher("Success_Reponse.jsp").forward(request, response);
		}
			
		else {
			request.getRequestDispatcher("Already_on_mission.jsp").forward(request, response);
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
