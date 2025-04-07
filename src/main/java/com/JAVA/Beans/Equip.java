package com.JAVA.Beans;

public class Equip {
	
	private int id;
	private String nom;
	private int taille;
	
	public Equip() {
		
	}
	
	public Equip(int id, String nom, int taille) {
		this.id = id;
		this.nom = nom;
		this.taille = taille;
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
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
}
