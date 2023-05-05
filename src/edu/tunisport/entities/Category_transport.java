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
public class Category_transport {
    private int id;
    private String typetransport;

    public Category_transport() {
    }

    public Category_transport(String typetransport) {
        this.typetransport = typetransport;
    }

    public Category_transport(int id, String typetransport) {
        this.id = id;
        this.typetransport = typetransport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypetransport() {
        return typetransport;
    }

    public void setTypetransport(String typetransport) {
        this.typetransport = typetransport;
    }

    @Override
    public String toString() {
        return id+" "+typetransport ;
    }
    
}
