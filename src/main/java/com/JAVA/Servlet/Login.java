package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.JAVA.Beans.Administrateur;
import com.JAVA.Beans.MaitNag;
import com.JAVA.Beans.Personne;
import com.JAVA.DAO.AdministrateurDAO;
import com.JAVA.DAO.AlerteNoyDAO;
import com.JAVA.DAO.DAOConfigurationException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.MaitNagDAO;
import com.JAVA.DAO.PersonneDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
    private PersonneDAO personneDAO;
    private AlerteNoyDAO alertenoyDao;
    private MaitNagDAO maitnagDAO;
    private AdministrateurDAO adminDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
        try {
            // Initialisation de DAOFactory et du DAO de specialite
            DAOFactory daoFactory = DAOFactory.getInstance();
            this.personneDAO = daoFactory.getPersonneDao();
            this.alertenoyDao = daoFactory.getAlerteNoyDao();
            this.maitnagDAO = daoFactory.getMaitNagDAO();
            this.adminDAO = daoFactory.getAdminDAO();
            
        } catch (DAOConfigurationException e) {
            throw new ServletException(e);
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");
		if(action != null & action.equals("home")) {
		
			int count = alertenoyDao.count_encours();
			request.setAttribute("count", count);
			
			request.getRequestDispatcher("Accueil2.jsp").forward(request, response);
		}
		else if(action!=null && action.equals("declare")) {
			request.getRequestDispatcher("Declaration.jsp").forward(request, response);
		}
		else {
			
			request.getRequestDispatcher("Accueil2.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");
		if(action == null) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

		
		if(adminDAO.auth(email, password)) {
			
			
			HttpSession session = request.getSession();
			Administrateur admin = adminDAO.find_one(email);
			session.setAttribute("admin", admin);
			
			request.getRequestDispatcher("statistiquesAlertesNoyade").forward(request, response);
			return;
		}
		else {
		boolean bool = personneDAO.auth(email,password);
		
		if(bool) {
			
			HttpSession session = request.getSession();
			Personne pers = personneDAO.find_one(email);
			
			int count = alertenoyDao.count_encours();
			
			
			session.setAttribute("Pers_sess", pers);
			
			request.setAttribute("count", count);
			

			request.getRequestDispatcher("Accueil2.jsp").forward(request, response);
		}
		else if(maitnagDAO.auth(email, password)){
			
			HttpSession session = request.getSession();
			MaitNag mait = maitnagDAO.find_one(email);
			
			session.setAttribute("Mait_sess", mait);
		
			request.getRequestDispatcher("Declaration.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("bool", bool);
            request.getRequestDispatcher("Authentif.jsp").forward(request, response);
		}
		}
		
		}
	}

}
