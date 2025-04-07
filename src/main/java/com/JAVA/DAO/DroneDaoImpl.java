package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.JAVA.Beans.Drone;
import com.JAVA.Beans.Equip;

public class DroneDaoImpl implements DroneDAO {

	private DAOFactory  daoFactory;

    public DroneDaoImpl( DAOFactory daoFactory ) {
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
	public ArrayList<Drone> getAll() throws DAOException {
		
		ArrayList<Drone> liste_dr = new ArrayList<>();
	    final String SQL_SELECT = "SELECT * FROM drone";
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
	               int id = resultSet.getInt( "id_dr" );
	               String  nom = resultSet.getString( "nom" );
	               int pour_bat = resultSet.getInt( "pour_bat" );
	               
	               /*Ajout des données dans l'ArrayList de javabean*/
	               liste_dr.add(new Drone(id,nom,pour_bat));
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return liste_dr;
	}
	
	@Override
    public void add(Drone drone) throws DAOException {
        String query = "INSERT INTO drone (Nom, Pour_bat) VALUES (?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, drone.getNom());
            preparedStatement.setInt(2, drone.getPour_bat());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'insertion du drone", e);
        }
    }

    @Override
    public void update(Drone drone) throws DAOException {
        String query = "UPDATE drone SET Nom = ?, Pour_bat = ? WHERE id_dr = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, drone.getNom());
            preparedStatement.setInt(2, drone.getPour_bat());
            preparedStatement.setInt(3, drone.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour du drone", e);
        }
    }

    @Override
    public void delete(int id_dr) throws DAOException {
        String query = "DELETE FROM drone WHERE id_dr = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id_dr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du drone", e);
        }
    }

}
