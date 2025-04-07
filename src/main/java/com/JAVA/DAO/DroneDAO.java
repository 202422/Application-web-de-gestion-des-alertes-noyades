package com.JAVA.DAO;

import java.util.ArrayList;

import com.JAVA.Beans.Drone;

public interface DroneDAO {

	ArrayList<Drone> getAll() throws DAOException;
	
	//Admin
	
	void add(Drone drone) throws DAOException; // Ajouter un drone
    void update(Drone drone) throws DAOException; // Mettre à jour un drone
    void delete(int id_dr) throws DAOException; // Supprimer un drone
}
