/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

/**
 *
 * @author medah
 */
public class Commentaire {
    
    private int id;
    private int blog_id;
    private int user_id;
    private String contenu_com;
    private String date_c;

    public Commentaire() {
    }

 

    public Commentaire(int id, int blog_id, int user_id, String contenu_com, String date_c) {
        this.id = id;
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.contenu_com = contenu_com;
        this.date_c = date_c;
    }
    
    public Commentaire(int blog_id, int user_id, String contenu_com, String date_c) {
        this.user_id = user_id;
        this.blog_id = blog_id;
        this.contenu_com = contenu_com;
        this.date_c = date_c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlog_id() {
        return this.blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContenu_com() {
        return contenu_com;
    }

    public void setContenu_com(String contenu_com) {
        this.contenu_com = contenu_com;
    }

    public String getDate_c() {
        return date_c;
    }

    public void setDate_c(String date_c) {
        this.date_c = date_c;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", blog_id=" + blog_id + ", user_id=" + user_id + ", contenu_com=" + contenu_com + ", date_c=" + date_c + '}';
    }
    
    
    
    
    
    
    
}
