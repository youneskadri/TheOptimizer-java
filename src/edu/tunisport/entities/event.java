/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

import java.util.Date;

/**
 *
 * @author mohamed
 */
public class event {
public String getName() {
    
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public Date getDate_event() {
        return date_event;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public String getHeyre_fin() {
        return heyre_fin;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getType_event_id() {
        return type_event_id;
    }
     private int id;
    private String  nom_event;
     private Date  date_event;
    private String  heure_debut;
     private String  heyre_fin;
      private String  localisation;
       private int  type_event_id;
       private String video;

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public event(int id, String nom_event, Date date_event, String heure_debut, String heyre_fin, String localisation, int type_event_id, String video, String name) {
        this.id = id;
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.heure_debut = heure_debut;
        this.heyre_fin = heyre_fin;
        this.localisation = localisation;
        this.type_event_id = type_event_id;
        this.video = video;
        this.name = name;
    }
   private String  name;
    public event(String nom_event, Date date_event, String heure_debut, String heyre_fin, String localisation, int type_event_id) {
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.heure_debut = heure_debut;
        this.heyre_fin = heyre_fin;
        this.localisation = localisation;
        this.type_event_id = type_event_id;
    }

    public event(int id, String nom_event, Date date_event, String heure_debut, String heyre_fin, String localisation, int type_event_id) {
        this.id = id;
        this.nom_event = nom_event;
        this.date_event = date_event;
        this.heure_debut = heure_debut;
        this.heyre_fin = heyre_fin;
        this.localisation = localisation;
        this.type_event_id = type_event_id;
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", nom_event=" + nom_event + ", date_event=" + date_event + ", heure_debut=" + heure_debut + ", heyre_fin=" + heyre_fin + ", localisation=" + localisation + ", type_event_id=" + type_event_id + '}';
    }
    public String getNom_type() {
    switch (type_event_id) {
        case 1: return "Type 1";
        case 2: return "Type 2";
        // Ajoutez d'autres cas pour chaque type d'événement que vous avez
        default: return "";
    }
}

    public event() {
    }

    public Object getTypeEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
