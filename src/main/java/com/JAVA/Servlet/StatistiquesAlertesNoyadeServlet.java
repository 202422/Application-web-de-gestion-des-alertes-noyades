package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import com.JAVA.DAO.AlerteNoyDAO;
import com.JAVA.DAO.AlerteNoyDaoImpl;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.EquipDAO;
import com.JAVA.DAO.EquipDaoImpl;
import com.JAVA.DAO.FeedbackDAO;
import com.JAVA.DAO.FeedbackDaoImpl;

@WebServlet("/statistiquesAlertesNoyade")
public class StatistiquesAlertesNoyadeServlet extends HttpServlet {
    private AlerteNoyDAO alerteNoyadeDAO;
    private EquipDAO equipeDAO;
    private FeedbackDAO feedbackDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.alerteNoyadeDAO = new AlerteNoyDaoImpl(daoFactory);
        this.equipeDAO = new EquipDaoImpl(daoFactory);
        this.feedbackDAO = new FeedbackDaoImpl(daoFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupération des statistiques par état
            Map<String, Integer> statistiques = alerteNoyadeDAO.getAlertesParEtat();
         // Récupération des statistiques des feedbacks (succès/échec)
            Map<String, Integer> feedbackStats = feedbackDAO.getFeedbackStats();
            // Récupération du total des alertes
            int totalAlertes = alerteNoyadeDAO.getTotalAlertes();
            // Récupération du total des équipes
            int totalEquipes = equipeDAO.getTotalEquipes();
            
            // Récupération du total des personnes sauvées
            int totalPersonnesSauvees = feedbackDAO.getTotalPersonnesSauvees();

            // Ajout des données dans les attributs de requête
            request.setAttribute("statistiques", statistiques);
            request.setAttribute("totalAlertes", totalAlertes);
            request.setAttribute("totalEquipes", totalEquipes);
            request.setAttribute("feedbackStats", feedbackStats);
            request.setAttribute("totalPersonnesSauvees", totalPersonnesSauvees);

            // Transfert vers la page JSP
            request.getRequestDispatcher("statistiques.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la récupération des statistiques", e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	try {
            // Récupération des statistiques par état
            Map<String, Integer> statistiques = alerteNoyadeDAO.getAlertesParEtat();
         // Récupération des statistiques des feedbacks (succès/échec)
            Map<String, Integer> feedbackStats = feedbackDAO.getFeedbackStats();
            // Récupération du total des alertes
            int totalAlertes = alerteNoyadeDAO.getTotalAlertes();
            // Récupération du total des équipes
            int totalEquipes = equipeDAO.getTotalEquipes();
            
            // Récupération du total des personnes sauvées
            int totalPersonnesSauvees = feedbackDAO.getTotalPersonnesSauvees();

            // Ajout des données dans les attributs de requête
            request.setAttribute("statistiques", statistiques);
            request.setAttribute("totalAlertes", totalAlertes);
            request.setAttribute("totalEquipes", totalEquipes);
            request.setAttribute("feedbackStats", feedbackStats);
            request.setAttribute("totalPersonnesSauvees", totalPersonnesSauvees);

            // Transfert vers la page JSP
            request.getRequestDispatcher("statistiques.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la récupération des statistiques", e);
        }
    }
    
}
