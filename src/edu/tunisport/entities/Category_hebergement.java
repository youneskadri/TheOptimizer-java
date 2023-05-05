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
public class Category_hebergement {
    
    private int id;
    private String nomcategory;

    public Category_hebergement() {
    }

    public Category_hebergement(String nomcategory) {
        this.nomcategory = nomcategory;
    }

    public Category_hebergement(int id, String nomcategory) {
        this.id = id;
        this.nomcategory = nomcategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategory() {
        return nomcategory;
    }

    public void setNomcategory(String nomcategory) {
        this.nomcategory = nomcategory;
        
    }

    @Override
    public String toString() {
        return id+" "+nomcategory  ;
    }
    
}
