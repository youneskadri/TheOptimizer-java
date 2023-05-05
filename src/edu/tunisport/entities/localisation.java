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
public class localisation {
  
    
    private int id;
    private String lieux;

    public localisation() {
    }

    public localisation(int id, String lieux) {
        this.id = id;
        this.lieux = lieux;
    }

    public localisation(String lieux) {
        this.lieux = lieux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }
     @Override
    public String toString() {
        return  id+" "+lieux ;
    }
}
