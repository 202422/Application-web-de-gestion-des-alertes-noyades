package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.JAVA.Beans.Equip;
import com.JAVA.DAO.DAOConfigurationException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.EquipDAO;

/**
 * Servlet implementation class Equip_servlet
 */
@WebServlet("/Equip_servlet")
public class Equip_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String action;
	private EquipDAO equipDao;
	
	
	@Override
    public void init() throws ServletException {
        try {
            // Initialisation de DAOFactory et du DAO de specialite
            DAOFactory daoFactory = DAOFactory.getInstance();
            this.equipDao = daoFactory.getEquipDao();
        } catch (DAOConfigurationException e) {
            throw new ServletException(e);
        }
    }   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Equip_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		if(action == null || action.equals("list")) {
			
        ArrayList<Equip> lst_equip = equipDao.getAll();
        
	
		request.setAttribute("liste_equip", lst_equip);
		
		request.getRequestDispatcher("all_equip.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		action = request.getParameter("action");
		if(action.equals("add_equip")) {
			String name_form = request.getParameter("nom_form");

			
			Equip equip = new Equip(0,name_form,0);
			equipDao.create(equip);
			response.sendRedirect("Equip_servlet?action=list");
		}
	}
		

}
