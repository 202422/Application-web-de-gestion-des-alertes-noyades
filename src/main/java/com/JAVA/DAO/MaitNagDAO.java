package com.JAVA.DAO;

import java.util.ArrayList;

import com.JAVA.Beans.MaitNag;


public interface MaitNagDAO {

	ArrayList<MaitNag> getAll() throws DAOException;
	boolean auth(String email, String pass) throws DAOException;
	MaitNag find_one(String email) throws DAOException;
	
}
