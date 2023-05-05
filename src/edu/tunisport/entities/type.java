 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

/**
 *
 * @author mohamed
 */
public class type {
    
    private int id;
    private String  nom_type;
     
    public type(){
        
    }

    public type(int id, String nom_type) {
        this.id = id;
        this.nom_type = nom_type;
    }

    public type(String nom_type) {
        this.nom_type = nom_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_type() {
        return nom_type;
    }

    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }

    @Override
    public String toString() {
        return "type{" + "id=" + id + ", nom_type=" + nom_type + '}';
    }
    
    
}
