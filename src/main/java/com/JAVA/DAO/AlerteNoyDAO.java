package com.JAVA.DAO;

import java.util.ArrayList;
import java.util.Map;

import com.JAVA.Beans.AlerteNoy;



public interface AlerteNoyDAO {

	ArrayList<AlerteNoy> get_encours() throws DAOException;
	void update_to_sauvetage(int id, int id_pers) throws DAOException;
	AlerteNoy getOne_ensauv(int id_pers) throws DAOException;
	void update_to_traite(int id) throws DAOException;
	void create(AlerteNoy alertnoy) throws DAOException;
	
	int count_encours() throws DAOException;
	
	
	//Admin
	
	Map<String, Integer> getAlertesParEtat() throws DAOException;
	int getTotalAlertes() throws DAOException;
	ArrayList<AlerteNoy> getAll() throws DAOException;
	void setEnCours(int id_al_N) throws DAOException;
	
	
}
