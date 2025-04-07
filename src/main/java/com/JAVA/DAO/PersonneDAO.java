package com.JAVA.DAO;

import java.util.ArrayList;

import com.JAVA.Beans.Personne;


public interface PersonneDAO {

	boolean auth(String email, String pass) throws DAOException;
	
	Personne find_one(String email) throws DAOException;
	
	//Admin
	
	void insert(Personne personne) throws DAOException; // Ajouter une personne
	
	ArrayList<Personne> getPersonnesByEquipe(int idEquipe) throws DAOException; // Ajouter cette méthode
}
