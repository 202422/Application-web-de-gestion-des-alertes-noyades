package com.JAVA.Beans;

public class Personne {

    private int id;
    private int id_eq;
    private String nom;
    private String prenom;
    private int chef_eq;
    private String password;
    private String email; // Ajout de l'attribut email

    public Personne() {
    }

    public Personne(int id, int id_eq, String nom, String prenom, int chef_eq, String password, String email) {
        this.id = id;
        this.id_eq = id_eq;
        this.nom = nom;
        this.prenom = prenom;
        this.chef_eq = chef_eq;
        this.password = password;
        this.email = email; // Initialisation de l'attribut email
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_eq() {
        return id_eq;
    }

    public void setId_eq(int id_eq) {
        this.id_eq = id_eq;
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

    public int getChef_eq() {
        return chef_eq;
    }

    public void setChef_eq(int chef_eq) {
        this.chef_eq = chef_eq;
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
