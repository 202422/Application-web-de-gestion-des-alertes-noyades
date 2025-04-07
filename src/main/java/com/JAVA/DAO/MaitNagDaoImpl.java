package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import com.JAVA.Beans.AlerteNoy;
import com.JAVA.Beans.MaitNag;
import com.JAVA.Beans.Personne;

public class MaitNagDaoImpl implements MaitNagDAO {

	
	private DAOFactory  daoFactory;

    public MaitNagDaoImpl( DAOFactory daoFactory ) {
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
	public ArrayList<MaitNag> getAll() throws DAOException {
		
		ArrayList<MaitNag> liste_maitre = new ArrayList<>();
	    final String SQL_SELECT = "SELECT * FROM maitre_nageur";
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
	        	
	        	int id = resultSet.getInt("id_maitre");
	        	String nom = resultSet.getString("nom");
	        	String prenom = resultSet.getString("prenom");
	        	String password = resultSet.getString("password");
	        	String email = resultSet.getString("email");
	        	
	        	liste_maitre.add(new MaitNag(id,nom,prenom,password,email));
	               
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		
		return liste_maitre;
	}

	@Override
	public boolean auth(String email, String pass) throws DAOException {
	
		boolean bool = false;
		String  password = null;
		final String SQL_SELECT = "Select password from maitre_nageur where email = ?";
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
	public MaitNag find_one(String email) throws DAOException {
		
		MaitNag maitnageur = null;
	    final String SQL_SELECT = "SELECT * FROM maitre_nageur where email = ?";
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
	               int id_m = resultSet.getInt( "id_maitre" );
	               String  nom = resultSet.getString( "nom" );
	               String  prenom = resultSet.getString( "prenom" );
	               String pass = resultSet.getString("password");
	               String email2 = resultSet.getString( "email" );
	               
	               
	               /*Ajout des données dans l'ArrayList de javabean*/
	               
	               maitnageur = new MaitNag(id_m,nom,prenom,pass,email2);
	               
	               }
	  
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return maitnageur;
	}

}
