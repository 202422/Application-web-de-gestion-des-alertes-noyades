package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.JAVA.Beans.AFeedback;

public class FeedbackDaoImpl implements FeedbackDAO {

	private DAOFactory  daoFactory;

    public FeedbackDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
    

	
	@Override
	public void create(AFeedback feedback) throws DAOException {
		// insert feedback
		
		final String SQL_Insert = "insert into feedback values(?,?,?,?,?)";
		PreparedStatement prepstatement = null;
	    Connection connexion = null;
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstatement = initRequestPrepare(connexion,SQL_Insert,0,feedback.getId_al_N(),
	        		feedback.getEtat(),feedback.getDuree(),feedback.getNb_person());
	        prepstatement.executeUpdate();
	      
	       
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

	}

	@Override
	public Map<String, Integer> getFeedbackStats() throws DAOException {
		String query = "SELECT Etat, COUNT(*) AS count FROM feedback GROUP BY Etat";
        Map<String, Integer> feedbackStats = new HashMap<>();
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String etat = resultSet.getString("Etat");
                int count = resultSet.getInt("count");
                feedbackStats.put(etat, count);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des statistiques de feedback", e);
        }

        return feedbackStats;
	}

	@Override
	public int getTotalPersonnesSauvees() throws DAOException {
		String query = "SELECT SUM(Nb_pers_sauve) AS total FROM feedback";
        int total = 0;
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors du calcul du total des personnes sauvées", e);
        }

        return total;
	}

}
