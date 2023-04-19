/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.services;

import event.entities.event;
import event.entities.type;

import event.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public class eventcrud {
        public void ajouterevent(event e){
        try {
            String requete="INSERT INTO event (nom_event,date_event,heure_deb,heure_fin,localisation,type_event_id) "
                   
                    + "VALUES ('"+e.getNom_event()+"','"+e.getDate_event()+"','"+e.getHeure_debut()+"','"+e.getHeyre_fin()+"','"+e.getLocalisation()+"','"+e.getType_event_id()+"')";
            System.out.println(requete);
            Statement st = new MyConnection() .getCnx() .createStatement();
            st.executeUpdate(requete);
            
            System.out.println("evenement ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
            public ObservableList<event> afficherevent() throws SQLException{
    ObservableList<event> ls = FXCollections.observableArrayList();
    
    String req = "select * from event";
    PreparedStatement pst = new MyConnection() .getCnx() .prepareStatement(req);
    ResultSet rs = pst.executeQuery(req);
     
    while(rs.next()){
        int id = rs.getInt("id");
        String nom_event = rs.getString("nom_event");
        Date date_event = rs.getDate("date_event");
        String heure_debut = rs.getString("heure_deb");
        String heure_fin = rs.getString("heure_fin");
        String localisation = rs.getString("localisation");
        int type_event_id = rs.getInt("type_event_id");
        
        

        event e = new event(id,nom_event, date_event, heure_debut, heure_fin, localisation, type_event_id);
        ls.add(e);

    }
    
    return ls;

    }
                public void supprimerevents(int id) {
        try {
            String req = "DELETE FROM event WHERE id = " + id;
          Statement st = new MyConnection() .getCnx() .prepareStatement(req);
            st.executeUpdate(req);
            System.out.println("evenement supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
