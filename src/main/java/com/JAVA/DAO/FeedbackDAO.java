package com.JAVA.DAO;

import java.util.Map;

import com.JAVA.Beans.AFeedback;

public interface FeedbackDAO {

	
	public void create(AFeedback feedback) throws DAOException;
	
	
	//Admin
	
	 Map<String, Integer> getFeedbackStats() throws DAOException;
	 
	 int getTotalPersonnesSauvees() throws DAOException;
}
