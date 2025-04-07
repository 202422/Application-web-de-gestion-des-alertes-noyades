package com.JAVA.Beans;

import java.time.LocalTime;

public class AFeedback {

	private int id;
	private int id_al_N;
	private String Etat;
	private LocalTime Duree;
	private int Nb_person;
	
	public AFeedback() {
        
    }
	
	
	
	public AFeedback(int id, int id_al_N, String etat, LocalTime duree, int nb_person) {
		this.id = id;
		this.id_al_N = id_al_N;
		Etat = etat;
		Duree = duree;
		Nb_person = nb_person;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_al_N() {
		return id_al_N;
	}
	public void setId_al_N(int id_al_N) {
		this.id_al_N = id_al_N;
	}
	public String getEtat() {
		return Etat;
	}
	public void setEtat(String etat) {
		Etat = etat;
	}
	public LocalTime getDuree() {
		return Duree;
	}
	public void setDuree(LocalTime duree) {
		Duree = duree;
	}
	public int getNb_person() {
		return Nb_person;
	}
	public void setNb_person(int nb_person) {
		Nb_person = nb_person;
	}
	
}
