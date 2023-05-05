/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

/**
 *
 * @author kadri younes
 */
public class Transport {
    private  int id;
    private String image_transport;
    private String nom_transport;
    private Category_transport c;

    public Transport() {
    }

    public Transport(String image_transport, String nom_transport, Category_transport c) {
        this.image_transport = image_transport;
        this.nom_transport = nom_transport;
        this.c = c;
    }

    public Transport(int id, String image_transport, String nom_transport, Category_transport c) {
        this.id = id;
        this.image_transport = image_transport;
        this.nom_transport = nom_transport;
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_transport() {
        return image_transport;
    }

    public void setImage_transport(String image_transport) {
        this.image_transport = image_transport;
    }

    public String getNom_transport() {
        return nom_transport;
    }

    public void setNom_transport(String nom_transport) {
        this.nom_transport = nom_transport;
    }

    public Category_transport getC() {
        return c;
    }

    public void setC(Category_transport c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", image_transport=" + image_transport + ", nom_transport=" + nom_transport + ", c=" + c + '}';
    }

    
    
    
}
