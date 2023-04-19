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
public class Blog {

    private int id;
    private String titre;
    private String descreption;
    private String contenu;
    private String image;
    
    
  public Blog() {
    }

    public Blog(int id, String titre, String descreption, String contenu, String image) {
        this.id = id;
        this.titre = titre;
        this.descreption = descreption;
        this.contenu = contenu;
        this.image = image;
    }

    public Blog(String titre, String descreption, String contenu, String image) {
        this.titre = titre;
        this.descreption = descreption;
        this.contenu = contenu;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", titre=" + titre + ", descreption=" + descreption + ", contenu=" + contenu + ", image=" + image + '}';
    }
  
  
  
}
