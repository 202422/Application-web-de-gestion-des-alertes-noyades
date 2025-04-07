package com.JAVA.DAO;

import java.util.ArrayList;

import com.JAVA.Beans.Equip;





public interface EquipDAO {


	void create(Equip equip) throws DAOException;
	
	ArrayList<Equip> getAll() throws DAOException;
	
	//Admin
	
	int getTotalEquipes() throws DAOException;
	
	void update(Equip equipe) throws DAOException; // Mettre à jour une équipe
	
	void delete(int id_eq) throws DAOException;  // Supprimer une équipe

	ArrayList<Equip> getEquipesEnSauvetage() throws DAOException;
}
