/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.event;
import edu.tunisport.services.eventcrud;
import edu.tunisport.services.typecrud;

import edu.tunisport.services.GeneratorPDF;
import edu.tunisport.tools.MyConnection;
import edu.tunisport.services.eventcrud;
import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class EventfController implements Initializable {

    @FXML
    private TableView<event> table_event;
    @FXML
    private TableColumn<?, ?> col_idevent;
    @FXML
    private TableColumn<?, ?> colnomevent;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> coledebut;
    @FXML
    private TableColumn<?, ?> colefin;
    @FXML
    private TableColumn<?, ?> colelocalisation;
    @FXML
    private TableColumn<?, ?> coletype;
    @FXML
    private TableColumn<?, ?> colvideo;
  

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            EventShowListData();
        } catch (SQLException ex) {
           
        }
    }    


   
     public ObservableList<event> EventListData() throws SQLException {

        return new eventcrud().afficherevent();
    }
    
    private ObservableList<event> EventList;
    
    public void EventShowListData() throws SQLException {
        
        EventList = EventListData();

        col_idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnomevent.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
        coledebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
         colefin.setCellValueFactory(new PropertyValueFactory<>("heyre_fin"));
colelocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));


        table_event.setItems(EventList);
        

    }


         @FXML
    public void selectItem() {
    event u = table_event.getSelectionModel().getSelectedItem();
    int num = table_event.getSelectionModel().getSelectedIndex();
        System.out.println(u);
         
    if (u == null) {
        // aucun élément sélectionné
        return;
    }
    if ((num - 1)<-6) {
        return;
    }
 
       
   


}

  

  

    

    
  

    private void triernom(ActionEvent event) {
                // Trier la liste des types par ordre alphabétique croissant
  EventList.sort((event1, event2) -> event1.getNom_event().compareTo(event2.getNom_event()));
    
    // Rafraîchir la table pour afficher la liste triée
    table_event.refresh();
    }

   @FXML
private void pdf(ActionEvent event)  {

event u = table_event.getSelectionModel().getSelectedItem();
    if (u == null) {
        // aucun élément sélectionné
        return;
    }
    String id = String.valueOf(u.getId());
    String nom_event = u.getNom_event();
    String heure_debut = u.getHeure_debut();
    String heyre_fin = u.getHeyre_fin();
    String localisation = u.getLocalisation();
    
    GeneratorPDF.generer(id, nom_event, heure_debut, heyre_fin, localisation);
}

    @FXML
    private void partagertw(ActionEvent event) throws UnsupportedEncodingException {// Récupérer l'événement sélectionné dans la table
event u = table_event.getSelectionModel().getSelectedItem();
if (u == null) {
    // aucun élément sélectionné, ne rien faire
    return;
}

// Construire le message à partager sur Twitter
String message = "Notre nouveau evenement \"" + u.getNom_event() + "\" pour la date de  " + u.getDate_event() + " à l heure  " + u.getHeure_debut() + " à " + u.getLocalisation() + " soyier le bienvenu" ;

// Encodage du message pour être utilisé dans l'URL
String encodedMessage = URLEncoder.encode(message, "UTF-8");

// Construire l'URL avec le message pré-rempli dans le champ de texte
String url = "https://twitter.com/intent/tweet?text=" + encodedMessage;

// Ouvrir la page Twitter avec le message pré-rempli dans le navigateur par défaut
try {
    Desktop.getDesktop().browse(new URI(url));
} catch (IOException | URISyntaxException e) {
    e.printStackTrace();
}

    }

    @FXML
    private void partagefb(ActionEvent event) throws UnsupportedEncodingException, URISyntaxException, IOException {
            // Récupérer l'événement sélectionné dans la table
   event u = table_event.getSelectionModel().getSelectedItem();
    if (u == null) {
        // aucun élément sélectionné, ne rien faire
        return;
    }

    // Construire le message à partager sur Facebook
    String message = "Notre nouveau evenement \"" + u.getNom_event() + "\" pour la date de  " + u.getDate_event() + " à l heure  " + u.getHeure_debut() + " à " + u.getLocalisation() + " soyier le bienvenu" ;

    // Encodage du message pour être utilisé dans l'URL
    String encodedMessage = URLEncoder.encode(message, "UTF-8");

    // Construire l'URL avec le message pré-rempli dans le champ de texte
    String url = "https://www.facebook.com/sharer/sharer.php?u=" + encodedMessage;

    // Ouvrir la page Facebook avec le message pré-rempli dans le navigateur par défaut
    Desktop.getDesktop().browse(new URI(url));
    }

    @FXML
    private void blogclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("front.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
  
    @FXML
    private void blogbouton(ActionEvent event) {
          javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("front.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void showEventF(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("eventf.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
        
        
    }

    @FXML
    private void showHebergF(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("frontH.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void showMatch(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("MatchUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reservationbouton(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Reservation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
        @FXML
    private void reclamationShow(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("ajouterRec.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) table_event.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    }

