package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import com.JAVA.Beans.AFeedback;
import com.JAVA.Beans.AlerteNoy;
import com.JAVA.Beans.Drone;
import com.JAVA.Beans.Personne;
import com.JAVA.DAO.AlerteNoyDAO;
import com.JAVA.DAO.DAOConfigurationException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.DroneDAO;
import com.JAVA.DAO.FeedbackDAO;

/**
 * Servlet implementation class Feedback
 */
@WebServlet("/Feedback")
public class Feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
	private AlerteNoyDAO alertenoydao;
	private DroneDAO dronedao;
    private FeedbackDAO feedbackdao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
        try {
            // Initialisation de DAOFactory et du DAO de specialite
            DAOFactory daoFactory = DAOFactory.getInstance();
            this.alertenoydao = daoFactory.getAlerteNoyDao();
            this.dronedao = daoFactory.getDroneDao();
            this.feedbackdao = daoFactory.getFeedbackDAO();
            
        } catch (DAOConfigurationException e) {
            throw new ServletException(e);
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");
		if(action!=null && action.equals("fairefdb")) {
			HttpSession session = request.getSession();
			Personne personne = (Personne) session.getAttribute("Pers_sess");
			
			AlerteNoy alertnoy = alertenoydao.getOne_ensauv(personne.getId());
			
			if(alertnoy == null) {
				
				request.getRequestDispatcher("NoFeedbackToDo.jsp").forward(request, response);
			}
			
			ArrayList<Drone> lst_drone = dronedao.getAll();
			
			request.setAttribute("alertenoy", alertnoy);
			request.setAttribute("lst_drone", lst_drone);
			
			request.getRequestDispatcher("Feedback.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		action = request.getParameter("action");
		
		if(action != null && action.equals("addfd")) {
			
			int id_al_N = Integer.parseInt(request.getParameter("id_al_N"));
			String etat = request.getParameter("Etat");
			LocalTime duree = LocalTime.parse(request.getParameter("durée"));
			int nb_person = Integer.parseInt(request.getParameter("Nbpers"));
			
			feedbackdao.create(new AFeedback(0,id_al_N,etat,duree,nb_person));
			alertenoydao.update_to_traite(id_al_N);
			
			request.getRequestDispatcher("SuccessFeedback.jsp").forward(request, response);
			
		}
		
		doGet(request, response);
	}

}
