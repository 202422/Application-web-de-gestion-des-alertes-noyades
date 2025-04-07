package com.JAVA.DAO;

import com.JAVA.Beans.Administrateur;

public interface AdministrateurDAO {

	boolean auth(String email, String pass) throws DAOException;
	
	Administrateur find_one(String email) throws DAOException;
}
