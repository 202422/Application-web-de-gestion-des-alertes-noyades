package com.JAVA.Beans;

public class Administrateur {

	private int id;
	private String nom;
	private String prenom;
	private String password;
	private String email;
	
    public Administrateur() {
    }
    
	public Administrateur(int id, String nom, String prenom, String password, String email) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.email = email;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
