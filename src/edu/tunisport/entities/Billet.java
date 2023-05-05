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
public class Billet {
    private int id;
    private int reservation_id;
    
    
    private int prix;

    public Billet() {
    }

    public Billet(int id, int reservation_id, int prix) {
        this.id = id;
        this.reservation_id = reservation_id;
        this.prix = prix;
    }

    public Billet(int reservation_id, int prix) {
        this.reservation_id = reservation_id;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    
}
