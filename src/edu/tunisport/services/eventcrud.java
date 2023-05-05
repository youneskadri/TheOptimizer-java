/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;



import edu.tunisport.entities.event;


import edu.tunisport.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
              
public void EnvoyerMail(String userEmail) throws MessagingException, AddressException, javax.mail.MessagingException {

        // Adresse e-mail de l'expéditeur
        String from = "mhamed92111@gmail.com";

        // Informations d'authentification pour se connecter au serveur SMTP
        final String username = "mhamed92111@gmail.com";
        final String password = "masqavwrfeukssgl";

        // Configuration des propriétés pour la session de messagerie
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Création de la session de messagerie
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        // Création du message de réinitialisation de mot de passe
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(userEmail));
        message.setSubject("nouveau evenement");
message.setText("Bonjour,\n\nNous avons un nouveau evenement, Merci de conculter notre site web pour connaitre les details de notre evenement et Bienvenue \n\nCordialement,\nL'équipe de support");
        // Envoi du message de réinitialisation de mot de passe
        
        
        Transport.send(message);
        

        System.out.println("Le message d'ajout est envoyé avec succés");
    }
      
}
