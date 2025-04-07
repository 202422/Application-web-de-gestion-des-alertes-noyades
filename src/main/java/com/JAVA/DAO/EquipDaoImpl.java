package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.JAVA.Beans.Equip;


public class EquipDaoImpl implements EquipDAO {

	private DAOFactory  daoFactory;

    public EquipDaoImpl( DAOFactory daoFactory ) {
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
	public ArrayList<Equip> getAll() throws DAOException {
		// TODO Auto-generated method stub
		
			ArrayList<Equip> liste_equip = new ArrayList<>();
		    final String SQL_SELECT = "SELECT * FROM Equipe_de_secours";
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
		               int id_eq = resultSet.getInt( "id_eq" );
		               String  nom = resultSet.getString( "nom" );
		               int taille = resultSet.getInt( "taille" );
		               
		               /*Ajout des données dans l'ArrayList de javabean*/
		               liste_equip.add(new Equip(id_eq,nom,taille));
		               }
		  
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        //ClosingAll( resultSet, preparedStatement, connexion );
		    }

		    return liste_equip;
		}


	@Override
	public void create(Equip equip) throws DAOException {
		
		String nom_equip = equip.getNom();
		final String SQL_SELECT = "insert into Equipe_de_secours(nom,taille) values(?,?)";
		PreparedStatement prepstatement = null;
	    Connection connexion = null;
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstatement = initRequestPrepare(connexion,SQL_SELECT,nom_equip,0);
	        prepstatement.executeUpdate();
	      
	       
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }
		
		
	}

	@Override
	public int getTotalEquipes() throws DAOException {
		String query = "SELECT COUNT(*) AS total FROM equipe_de_secours";
        int total = 0;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération du total des équipes : " + e.getMessage(), e);
        }

        return total;
	}
	
	@Override
    public void update(Equip equipe) throws DAOException {
        String query = "UPDATE equipe_de_secours SET nom = ?, taille = ? WHERE id_eq = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, equipe.getNom());
            preparedStatement.setInt(2, equipe.getTaille());
            preparedStatement.setInt(3, equipe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la mise à jour de l'équipe", e);
        }
    }
	
	 @Override
	    public void delete(int id_eq) throws DAOException {
	        String query = "DELETE FROM equipe_de_secours WHERE id_eq = ?";

	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setInt(1, id_eq);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DAOException("Erreur lors de la suppression de l'équipe", e);
	        }
	    }

	 @Override
	    public ArrayList<Equip> getEquipesEnSauvetage() throws DAOException {
	        ArrayList<Equip> equipes = new ArrayList<>();
	        String query = "SELECT DISTINCT e.id_eq, e.nom, e.taille " +
	                       "FROM equipe_de_secours e " +
	                       "JOIN personne p ON e.id_eq = p.id_eq_F " +
	                       "JOIN alerte_noyade an ON p.id_pers = an.id_pers_F " +
	                       "WHERE an.etat = 'En sauvetage'";

	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int id_eq = resultSet.getInt("id_eq");
	                String nom = resultSet.getString("nom");
	                int taille = resultSet.getInt("taille");

	                Equip equipe = new Equip(id_eq, nom, taille);
	                equipes.add(equipe);
	            }
	        } catch (SQLException e) {
	            throw new DAOException("Erreur lors de la récupération des équipes en sauvetage", e);
	        }
	        return equipes;
	    }


}
