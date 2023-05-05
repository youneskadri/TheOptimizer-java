/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;


public class Reclamation {
    private int id ; 
    private int id_user ; 
    private String sujet ;
    private int statut ; 
    private String description ; 
    public Reclamation(int id, int id_user, String sujet, int statut,String description) {
        this.id = id;
        this.id_user = id_user;
        this.sujet = sujet;
        this.statut = statut;
        this.description  = description ; 
    }

        public Reclamation( int id_user, String sujet, int statut,String description) {
        this.id_user = id_user;
        this.sujet = sujet;
        this.statut = statut;
        this.description  = description ; 
    }
        
    public Reclamation() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getSujet() {
        return sujet;
    }

    public int getStatut() {
        return statut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "id=" + id + ", id_user=" + id_user + ", sujet=" + sujet + ", description = " + description + ", statut=" + statut + '}';
    }
    
    
    
}
