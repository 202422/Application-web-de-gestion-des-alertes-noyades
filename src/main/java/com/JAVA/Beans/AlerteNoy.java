package com.JAVA.Beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDateTime;

public class AlerteNoy {

	private int id;
	private int id_pers;
	private int id_dr;
	private LocalDate date;
	private LocalTime heure;
	private int Nb_pers;
	private String Etat;
	private Drone drone;
	private float latitude;
	private float longitude;
	private int id_maitre;
	
	public AlerteNoy(int id, int id_pers, int id_dr, LocalDate date, LocalTime heure, int nb_pers, String etat,
			Drone drone, float latitude, float longitude, int id_maitre) {
		this.id = id;
		this.id_pers = id_pers;
		this.id_dr = id_dr;
		this.date = date;
		this.heure = heure;
		Nb_pers = nb_pers;
		Etat = etat;
		this.drone = drone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.id_maitre = id_maitre;
	}


	public int getId_maitre() {
		return id_maitre;
	}


	public void setId_maitre(int id_maitre) {
		this.id_maitre = id_maitre;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public AlerteNoy() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_pers() {
		return id_pers;
	}


	public void setId_pers(int id_pers) {
		this.id_pers = id_pers;
	}


	public int getId_dr() {
		return id_dr;
	}


	public void setId_dr(int id_dr) {
		this.id_dr = id_dr;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getHeure() {
		return heure;
	}


	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}


	public int getNb_pers() {
		return Nb_pers;
	}


	public void setNb_pers(int nb_pers) {
		Nb_pers = nb_pers;
	}


	public String getEtat() {
		return Etat;
	}


	public void setEtat(String etat) {
		Etat = etat;
	}


	public Drone getDrone() {
		return drone;
	}


	public void setDrone(Drone drone) {
		this.drone = drone;
	}
	
	public String getTempsEcoule() {
	    if (date != null && heure != null) {
	        try {
	            // Combiner la date et l'heure pour créer un LocalDateTime
	            LocalDateTime dateHeureAlerte = LocalDateTime.of(date, heure);
	            LocalDateTime maintenant = LocalDateTime.now();

	            // Calculer la durée entre la date/heure de l'alerte et maintenant
	            Duration duree = Duration.between(dateHeureAlerte, maintenant);
	            long heures = duree.toHours();
	            long minutes = duree.toMinutes() % 60;

	            return heures + "h " + minutes + "min";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Erreur de calcul";
	        }
	    }
	    return "Date/heure non définie";
	}
	
}
