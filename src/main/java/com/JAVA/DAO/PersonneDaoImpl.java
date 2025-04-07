package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.JAVA.Beans.Personne;


public class PersonneDaoImpl implements PersonneDAO {

	private DAOFactory  daoFactory;

    public PersonneDaoImpl( DAOFactory daoFactory ) {
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
	public boolean auth(String email, String pass) throws DAOException {
		
		boolean bool = false;
		String  password = null;
		final String SQL_SELECT = "Select password from Personne where email = ?";
		Connection connexion = null;
		PreparedStatement prepstatement = null;
		ResultSet resultset = null;
		
		try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstatement = initRequestPrepare(connexion,SQL_SELECT,email);
	        resultset = prepstatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if(!resultset.next()) {
	        	bool = false;
	        }
	        else {
	        	
	               password = resultset.getString( "password" );
	               bool = password.equals(pass);
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return bool;
	}

	@Override
	public Personne find_one(String email) throws DAOException {
		
		Personne personne = null;
	    final String SQL_SELECT = "SELECT * FROM Personne where email = ?";
	    Connection connexion = null;
	    PreparedStatement prepstatement = null;
	    ResultSet resultSet = null;
	   

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        prepstatement = initRequestPrepare(connexion,SQL_SELECT,email);
	        resultSet = prepstatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        while ( resultSet.next() ) {
	               int id_p = resultSet.getInt( "id_pers" );
	               int id_eq = resultSet.getInt( "id_eq_f" );
	               String  nom = resultSet.getString( "nom" );
	               String  prenom = resultSet.getString( "prenom" );
	               int chef_eq = resultSet.getInt( "chef_eq" );
	               String pass = resultSet.getString("password");
	               String email2 = resultSet.getString("email");
	               
	               
	               /*Ajout des données dans l'ArrayList de javabean*/
	               personne = new Personne(id_p,id_eq,nom,prenom,chef_eq,pass,email2);
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return personne;
	}

	@Override
    public ArrayList<Personne> getPersonnesByEquipe(int idEquipe) throws DAOException {
        ArrayList<Personne> membres = new ArrayList<>();
        String query = "SELECT * FROM personne WHERE id_eq_F = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idEquipe);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idPers = resultSet.getInt("id_pers");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    boolean chefEq = resultSet.getBoolean("chef_eq");
                    int chefEqInt = chefEq ? 1 : 0;
                    String pass = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    
                    
                    Personne personne = new Personne(idPers, idEquipe, nom, prenom, chefEqInt, pass,email);
                    membres.add(personne);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la récupération des membres de l'équipe", e);
        }

        return membres;
    }
	
	 @Override
	    public void insert(Personne personne) throws DAOException {
	        String query = "INSERT INTO personne (nom, prenom, id_eq_F, chef_eq, password,email) VALUES (?, ?, ?, ?, ?,?)";

	        try (Connection connection = daoFactory.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, personne.getNom());
	            preparedStatement.setString(2, personne.getPrenom());
	            preparedStatement.setInt(3, personne.getId_eq());
	            preparedStatement.setBoolean(4, personne.getChef_eq() == 1);
	            preparedStatement.setString(5, personne.getPassword());
	            preparedStatement.setString(6, personne.getEmail());
	            
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            throw new DAOException("Erreur lors de l'insertion de la personne", e);
	        }
	    }
	
}
