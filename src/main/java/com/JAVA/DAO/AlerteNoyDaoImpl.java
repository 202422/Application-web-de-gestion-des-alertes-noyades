package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.JAVA.Beans.*;



public class AlerteNoyDaoImpl implements AlerteNoyDAO {

	private DAOFactory  daoFactory;

    public AlerteNoyDaoImpl( DAOFactory daoFactory ) {
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
	public ArrayList<AlerteNoy> get_encours() throws DAOException {
		
		ArrayList<AlerteNoy> liste_al = new ArrayList<>();
	    final String SQL_SELECT = "SELECT * FROM alerte_noyade where Etat = 'En cours'";
	    Connection connexion = null;
	    Statement statement = null;
	    ResultSet resultSet = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        statement = connexion.createStatement();
	        resultSet = statement.executeQuery(SQL_SELECT);
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        while ( resultSet.next() ) {
	        	
	        	int id = resultSet.getInt("id_al_N");
	        	int id_pers = resultSet.getInt("id_pers_F");
	        	int id_dr = resultSet.getInt("id_dr_F");
	        	Date date = resultSet.getDate("Date");
	        	Time heure = resultSet.getTime("Heure");
	        	float lat = resultSet.getFloat("latitude");
	        	float lon = resultSet.getFloat("longitude");
	        	int Nb_pers = resultSet.getInt("Nb_pers");
	        	String Etat = resultSet.getString("Etat");
	        	int idm = resultSet.getInt("id_maitre_F");
	        	
	        	liste_al.add(new AlerteNoy(id,id_pers,id_dr,date.toLocalDate(),heure.toLocalTime(),Nb_pers,Etat, null,lat,lon,idm));
	               
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return liste_al;
	}

	@Override
	public void update_to_sauvetage(int id, int idpers) throws DAOException {
		
		final String SQL_UPDATE = "UPDATE alerte_noyade SET Etat = 'En sauvetage', id_pers_F = ? WHERE id_al_N =?";
		Connection conn = null;
		PreparedStatement prepstatement = null;
		
		 try {
		        /* Récupération d'une connexion depuis la Factory */
		        conn = daoFactory.getConnection();
		        prepstatement = initRequestPrepare(conn,SQL_UPDATE,idpers,id);
		        prepstatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        //ClosingAll( resultSet, preparedStatement, connexion );
		    }

		
	}

	@Override
	public AlerteNoy getOne_ensauv(int id_pers) throws DAOException {
		
		AlerteNoy alerte = null;
		
		final String sql = "SELECT * FROM alerte_noyade where id_pers_F = ? and Etat = 'En sauvetage'";
		Connection connexion = null;
	    PreparedStatement prepstat = null;
	    ResultSet resultSet = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstat = initRequestPrepare(connexion,sql,id_pers);
	        resultSet = prepstat.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        while ( resultSet.next() ) {
	        	
	        	int id = resultSet.getInt("id_al_N");
	        	int id_pers2 = resultSet.getInt("id_pers_F");
	        	int id_dr = resultSet.getInt("id_dr_F");
	        	Date date = resultSet.getDate("Date");
	        	Time heure = resultSet.getTime("Heure");
	        	float lat = resultSet.getFloat("latitude");
	        	float lon = resultSet.getFloat("longitude");
	        	int Nb_pers = resultSet.getInt("Nb_pers");
	        	String Etat = resultSet.getString("Etat");
	        	 int idm = resultSet.getInt("id_maitre_F");
	        	
	      
	        	alerte = new AlerteNoy(id,id_pers2,id_dr,date.toLocalDate(),heure.toLocalTime(),Nb_pers,Etat, null,lat,lon,idm);
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		
		
		return alerte;
	}

	@Override
	public void update_to_traite(int id) throws DAOException {
		// TODO Auto-generated method stub
		final String SQL_UPDATE = "UPDATE alerte_noyade SET Etat = 'Traité' WHERE id_al_N =?";
		Connection conn = null;
		PreparedStatement prepstatement = null;
		
		 try {
		        /* Récupération d'une connexion depuis la Factory */
		        conn = daoFactory.getConnection();
		        prepstatement = initRequestPrepare(conn,SQL_UPDATE,id);
		        prepstatement.executeUpdate();
		       
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        //ClosingAll( resultSet, preparedStatement, connexion );
		    }

		
	}

	@Override
	public int count_encours() throws DAOException {
		
		int nbr = 0;
		
		final String sql = "SELECT COUNT(*) as cnt FROM alerte_noyade where Etat = 'En cours'";
		Connection connexion = null;
	    PreparedStatement prepstat = null;
	    ResultSet resultSet = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstat = initRequestPrepare(connexion,sql);
	        resultSet = prepstat.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if ( resultSet.next() ) {
	        	
	        	nbr = resultSet.getInt("cnt");
	   
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		
		return nbr;
	}

	@Override
	public Map<String, Integer> getAlertesParEtat() throws DAOException {
		 Map<String, Integer> stats = new HashMap<>();
	        String query = "SELECT Etat, COUNT(*) AS count FROM alerte_noyade GROUP BY Etat";

	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                String etat = resultSet.getString("Etat");
	                int count = resultSet.getInt("count");
	                stats.put(etat, count);
	            }
	        } catch (SQLException e) {
	            throw new DAOException("Erreur lors de la récupération des statistiques : " + e.getMessage(), e);
	        }

	        return stats;
	}

	@Override
	public int getTotalAlertes() throws DAOException {
		String query = "SELECT COUNT(*) AS total FROM alerte_noyade";
        int total = 0;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du total des alertes : " + e.getMessage(), e);
        }

        return total;
	}

	@Override
	public ArrayList<AlerteNoy> getAll() throws DAOException {
		ArrayList<AlerteNoy> alertes = new ArrayList<>();
        String query = "select * from alerte_noyade";

        try (Connection connection = daoFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id_al_N = resultSet.getInt("id_al_N");
                Date date = resultSet.getDate("date");
                Time heure = resultSet.getTime("heure");
                float lat = resultSet.getFloat("latitude");
                float lon = resultSet.getFloat("longitude");
                int nb_pers = resultSet.getInt("Nb_pers");
                String etat = resultSet.getString("Etat");
                int idm = resultSet.getInt("id_maitre_F");
                
                

                AlerteNoy alerte = new AlerteNoy(id_al_N, 0, 0, date.toLocalDate(), heure.toLocalTime(), nb_pers, etat, null, lat, lon,idm);
                alertes.add(alerte);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'accès à la base de données", e);
        }
        return alertes;
	}

	@Override
	public void setEnCours(int id_al_N) throws DAOException {
		
		String query = "UPDATE alerte_noyade SET etat = 'En cours' WHERE id_al_N = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id_al_N);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de l'état de l'alerte : " + e.getMessage(), e);
        }
		
	}

	@Override
	public void create(AlerteNoy alertnoy) throws DAOException {
		
	    final String SQL_INSERT = "insert into alerte_noyade values(0,null,null,?,?,?,'Non Traité',?,?,?)";
	    Connection connexion = null;
	    PreparedStatement prepstatement = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstatement = initRequestPrepare(connexion,SQL_INSERT,
	        		alertnoy.getDate(),alertnoy.getHeure(),alertnoy.getNb_pers(),
	        		alertnoy.getId_maitre(),alertnoy.getLatitude(),alertnoy.getLongitude());
	        prepstatement.executeUpdate();
	       
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		
	}

}
