/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author ASUS
 */
public class MatchF {
    private int id;
    private String equipeA;
    private String equipeB;
    private Date date;
    private Time heureDeb;
    private Time heureFin;
    private String type;
    private String stade;
    private String tournoi;
    private int resultatA;
    private int resultatB;
    private int prix;
    private String image;
    private String image2;
    private int nbrBilletTotal;
    private int nbrBilletReserve;

    public MatchF(int id, String equipeA, String equipeB, Date date, String type, String stade, String tournoi, int resultatA, int resultatB, int prix, String image, String image2, int nbrBilletTotal, int nbrBilletReserve) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.date = date;
        this.type = type;
        this.stade = stade;
        this.tournoi = tournoi;
        this.resultatA = resultatA;
        this.resultatB = resultatB;
        this.prix = prix;
        this.image = image;
        this.image2 = image2;
        this.nbrBilletTotal = nbrBilletTotal;
        this.nbrBilletReserve = nbrBilletReserve;
    }

    public MatchF(String equipeA, String equipeB, Date date, String type, String stade, String tournoi, int resultatA, int resultatB, int prix, String image, String image2, int nbrBilletTotal, int nbrBilletReserve) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.date = date;
        this.type = type;
        this.stade = stade;
        this.tournoi = tournoi;
        this.resultatA = resultatA;
        this.resultatB = resultatB;
        this.prix = prix;
        this.image = image;
        this.image2 = image2;
        this.nbrBilletTotal = nbrBilletTotal;
        this.nbrBilletReserve = nbrBilletReserve;
    }

    public MatchF(int id, String equipeA, String equipeB, Date date, Time heureDeb, Time heureFin, String type, String stade, String tournoi, int resultatA, int resultatB, int prix, String image, String image2, int nbrBilletTotal, int nbrBilletReserve) {
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.date = date;
        this.heureDeb = heureDeb;
        this.heureFin = heureFin;
        this.type = type;
        this.stade = stade;
        this.tournoi = tournoi;
        this.resultatA = resultatA;
        this.resultatB = resultatB;
        this.prix = prix;
        this.image = image;
        this.image2 = image2;
        this.nbrBilletTotal = nbrBilletTotal;
        this.nbrBilletReserve = nbrBilletReserve;
    }

    public MatchF(String equipeA, String equipeB, Date date, Time heureDeb, Time heureFin, String type, String stade, String tournoi, int resultatA, int resultatB, int prix, String image, String image2, int nbrBilletTotal, int nbrBilletReserve) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.date = date;
        this.heureDeb = heureDeb;
        this.heureFin = heureFin;
        this.type = type;
        this.stade = stade;
        this.tournoi = tournoi;
        this.resultatA = resultatA;
        this.resultatB = resultatB;
        this.prix = prix;
        this.image = image;
        this.image2 = image2;
        this.nbrBilletTotal = nbrBilletTotal;
        this.nbrBilletReserve = nbrBilletReserve;
    }

    
    
    
    
    
    
    
    
    
    

    
    
    
    public int getNbrBilletTotal() {
        return nbrBilletTotal;
    }

    public void setNbrBilletTotal(int nbrBilletTotal) {
        this.nbrBilletTotal = nbrBilletTotal;
    }

    public int getNbrBilletReserve() {
        return nbrBilletReserve;
    }

    public void setNbrBilletReserve(int nbrBilletReserve) {
        this.nbrBilletReserve = nbrBilletReserve;
    }

    
   
    

    

    public MatchF() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipeA() {
        return equipeA;
    }

    public String getEquipeB() {
        return equipeB;
    }

    public Date getDate() {
        return date;
    }

    
    public String getType() {
        return type;
    }

    public String getStade() {
        return stade;
    }

    public String getTournoi() {
        return tournoi;
    }

    public int getResultatA() {
        return resultatA;
    }

    public int getResultatB() {
        return resultatB;
    }

    public int getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public String getImage2() {
        return image2;
    }

    
    
    
    
    public void setEquipeA(String equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(String equipeB) {
        this.equipeB = equipeB;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    

    public void setType(String type) {
        this.type = type;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public void setTournoi(String tournoi) {
        this.tournoi = tournoi;
    }

    public void setResultatA(int resultatA) {
        this.resultatA = resultatA;
    }

    public void setResultatB(int resultatB) {
        this.resultatB = resultatB;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public Time getHeureDeb() {
        return heureDeb;
    }

    public void setHeureDeb(Time heureDeb) {
        this.heureDeb = heureDeb;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final MatchF other = (MatchF) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.resultatA != other.resultatA) {
            return false;
        }
        if (this.resultatB != other.resultatB) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.nbrBilletTotal != other.nbrBilletTotal) {
            return false;
        }
        if (this.nbrBilletReserve != other.nbrBilletReserve) {
            return false;
        }
        if (!Objects.equals(this.equipeA, other.equipeA)) {
            return false;
        }
        if (!Objects.equals(this.equipeB, other.equipeB)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.stade, other.stade)) {
            return false;
        }
        if (!Objects.equals(this.tournoi, other.tournoi)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.image2, other.image2)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.heureDeb, other.heureDeb)) {
            return false;
        }
        if (!Objects.equals(this.heureFin, other.heureFin)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "MatchF{" + "id=" + id + ", equipeA=" + equipeA + ", equipeB=" + equipeB + ", type=" + type + ", stade=" + stade + ", tournoi=" + tournoi + ", resultatA=" + resultatA + ", resultatB=" + resultatB + ", prix=" + prix + '}';
    }

   
}
