/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

/**
 *
 * @author ASUS
 */
public class Classement {
    private int rang;
    private String equipe;
    private int points;
    private int differenceBut;
  private int joues;
  private int victoire;
  private int nul;
  private int defaite;
  private int butPour;
  private int butContre;

    public Classement() {
    }

    public Classement(int rang, String equipe, int points, int differenceBut, int joues, int victoire, int nul, int defaite, int butPour, int butContre) {
        this.rang = rang;
        this.equipe = equipe;
        this.points = points;
        this.differenceBut = differenceBut;
        this.joues = joues;
        this.victoire = victoire;
        this.nul = nul;
        this.defaite = defaite;
        this.butPour = butPour;
        this.butContre = butContre;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDifferenceBut() {
        return differenceBut;
    }

    public void setDifferenceBut(int differenceBut) {
        this.differenceBut = differenceBut;
    }

    public int getJoues() {
        return joues;
    }

    public void setJoues(int joues) {
        this.joues = joues;
    }

    public int getVictoire() {
        return victoire;
    }

    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }

    public int getNul() {
        return nul;
    }

    public void setNul(int nul) {
        this.nul = nul;
    }

    public int getDefaite() {
        return defaite;
    }

    public void setDefaite(int defaite) {
        this.defaite = defaite;
    }

    public int getButPour() {
        return butPour;
    }

    public void setButPour(int butPour) {
        this.butPour = butPour;
    }

    public int getButContre() {
        return butContre;
    }

    public void setButContre(int butContre) {
        this.butContre = butContre;
    }
 
 

    
    
  
  
}
