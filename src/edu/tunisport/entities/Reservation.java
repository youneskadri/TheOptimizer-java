/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Reservation {
    private int id;
    private String user_id;
    private String match_id;
    private Date date;
    private int nbr_billet;
    private String etat;
    
    
    
    

    public Reservation() {
    }

    public Reservation(int id, String user_id, String match_id, Date date, int nbr_billet, String etat) {
        this.id = id;
        this.user_id = user_id;
        this.match_id = match_id;
        this.date = date;
        this.nbr_billet = nbr_billet;
        this.etat = etat;
    }

    public Reservation(String user_id, String match_id, Date date, int nbr_billet, String etat) {
        this.user_id = user_id;
        this.match_id = match_id;
        this.date = date;
        this.nbr_billet = nbr_billet;
        this.etat = etat;
    }

    public Reservation(int id, String user_id, Date date, int nbr_billet, String etat) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.nbr_billet = nbr_billet;
        this.etat = etat;
    }

    public Reservation(String user_id, Date date, int nbr_billet, String etat) {
        this.user_id = user_id;
        this.date = date;
        this.nbr_billet = nbr_billet;
        this.etat = etat;
    }
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbr_billet() {
        return nbr_billet;
    }

    public void setNbr_billet(int nbr_billet) {
        this.nbr_billet = nbr_billet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", user_id=" + user_id + ", match_id=" + match_id + ", date=" + date + ", nbr_billet=" + nbr_billet + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.user_id);
        hash = 11 * hash + Objects.hashCode(this.match_id);
        hash = 11 * hash + Objects.hashCode(this.date);
        hash = 11 * hash + this.nbr_billet;
        hash = 11 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbr_billet != other.nbr_billet) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        if (!Objects.equals(this.match_id, other.match_id)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
}
