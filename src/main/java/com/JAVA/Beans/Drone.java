package com.JAVA.Beans;

public class Drone {
	
	private int id;
	private String nom;
	private int pour_bat;
	
	public Drone() {
		
	}
	
	public Drone(int id, String nom, int pour_bat) {
		
		this.id = id;
		this.nom = nom;
		this.pour_bat = pour_bat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPour_bat() {
		return pour_bat;
	}

	public void setPour_bat(int pour_bat) {
		this.pour_bat = pour_bat;
	}
	
	
	
}
