package edu.tunisport.entities;


/**
 *
 * @author SIRIUS
 */
public class Reponse {
    private int id;
    private int id_rec;
    private String reponse;
  

    public Reponse(int id, int id_rec, String reponse) {
        this.id = id;
        this.id_rec = id_rec;
        this.reponse = reponse;
       
    }
     public Reponse(int id_rec, String reponse) {
        this.id_rec = id_rec;
        this.reponse = reponse;     
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", id_rec=" + id_rec + ", reponse=" + reponse + '}';
    }

    public Reponse() {
    }

    public String getReponse() {
        return reponse;
    }

    public int getId() {
        return id;
    }

    public int getId_rec() {
        return id_rec ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getStatus() {
        return reponse;
    }

   
}