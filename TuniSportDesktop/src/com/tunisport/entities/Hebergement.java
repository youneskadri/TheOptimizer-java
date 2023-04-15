/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.entities;

/**
 *
 * @author kadri younes
 */
public class Hebergement {
    private int id;
    private String nom_heberg;
    private String deschebergement;
    private String image;
    private localisation l;
    private Category_hebergement c;
    public Hebergement() {
    }

    public Hebergement(int id, String nom_heberg, String deschebergement, String image, localisation l, Category_hebergement c) {
        this.id = id;
        this.nom_heberg = nom_heberg;
        this.deschebergement = deschebergement;
        this.image = image;
        this.l = l;
        this.c = c;
    }

    public Hebergement(String nom_heberg, String deschebergement, String image, localisation l, Category_hebergement c) {
        this.nom_heberg = nom_heberg;
        this.deschebergement = deschebergement;
        this.image = image;
        this.l = l;
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_heberg() {
        return nom_heberg;
    }

    public void setNom_heberg(String nom_heberg) {
        this.nom_heberg = nom_heberg;
    }

    public String getDeschebergement() {
        return deschebergement;
    }

    public void setDeschebergement(String deschebergement) {
        this.deschebergement = deschebergement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public localisation getL() {
        return l;
    }

    public void setL(localisation l) {
        this.l = l;
    }

    public Category_hebergement getC() {
        return c;
    }

    public void setC(Category_hebergement c) {
        this.c = c;
    }
@Override
    public String toString() {
        return "Hebergement{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", nom_heberg='" + nom_heberg + '\'' +
                ", deschebergement='" + deschebergement + '\'' +
                ", localisation=" + l +
                ", category_hebergement=" + c +
                '}';
    }
    
}

