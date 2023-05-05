/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Status;
import edu.tunisport.entities.event;
import edu.tunisport.tools.MyConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javax.swing.text.Document;
import java.io.FileOutputStream;

import org.openqa.selenium.chrome.ChromeDriver;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import edu.tunisport.services.GeneratorPDF;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import edu.tunisport.services.eventcrud;
import edu.tunisport.tools.session;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.controlsfx.control.Notifications;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.rmi.transport.Transport;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class EventController implements Initializable {
    @FXML
    private TextField tfnomev;
    @FXML
    private TextField tfdebut;
    @FXML
    private TextField tffin;
    @FXML
    private TextField tflocalisation;
    @FXML
    private DatePicker tfdate;
    @FXML
    private ComboBox<String> tftype;
    ObservableList<String> optionstype = FXCollections.observableArrayList();
    @FXML
    private TableView<event> table_event;
    @FXML
    private TableColumn<event, String> col_idevent;
    @FXML
    private TableColumn<event, String> col_date;
    @FXML
    private TableColumn<event, String> coledebut;
    @FXML
    private TableColumn<event, String> colefin;
    @FXML
    private TableColumn<event, String> colelocalisation;
    @FXML
    private TableColumn<event, String> coletype;
Map<String, Integer> maMap = new HashMap<String, Integer>();
    @FXML
    private TableColumn<?, ?> colnomevent;
    @FXML
    private TextField ffid;
    @FXML
    private TableColumn<?, ?> colvideo;
    @FXML
    private TextField tfvideo;
    @FXML
    private TextField tfrechercher;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcomboExercice();
        tftype.setItems(optionstype);
        try {
            EventShowListData();
        } catch (SQLException ex) {
           
        }
    }    
 public void fillcomboExercice() {
        

        try {
            String req = "select * from type_event";
            PreparedStatement pst = new MyConnection() .getCnx() .prepareStatement(req);
            ResultSet rs = pst.executeQuery(req);
           
            while (rs.next()) {
                maMap.put(rs.getString("nom_type"), rs.getInt("id"));
                        


                optionstype.add(rs.getString("nom_type"));
            }

        } catch (SQLException ex) {

        }
    }

    @FXML
    private void ajouterevent(ActionEvent event) throws SQLException, javax.mail.MessagingException {
        LocalDate selectedDate = tfdate.getValue();
LocalDate today = LocalDate.now();

if (selectedDate == null || selectedDate.isBefore(today)) {
Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("minimum vous devez choisir la date d aujourd hui");
    alert.showAndWait();    

// Afficher un  d'erreur ou effectuer une autre action si la date sélectionnée est antérieure à la date d'aujourd'hui
} else {
        String nom = tfnomev.getText();
     Date date_event = Date.valueOf(tfdate.getValue());
     String heure_debut = tfdebut.getText();
     String heure_fin = tffin.getText();
     String localisation = tflocalisation.getText();
    String video = tfvideo.getText();

     
     int resultat = maMap.get(tftype.getValue());
     System.out.println(resultat);

       if (!nom.matches("[a-zA-Z]+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ du nom doit contenir uniquement des lettres");
    alert.showAndWait();
    return;
    // afficher un message d'erreur ou une alerte pour indiquer que le champ doit contenir uniquement des lettres
     
// sortir de la méthode pour éviter d'exécuter le reste du code
}
     
         if (nom.length() < 5) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ du nom doit avoir au moins 5 caractères");
    alert.showAndWait();
    return;
    // afficher un message d'erreur ou une alerte pour indiquer que le champ doit avoir au moins 5 caractères
     
// sortir de la méthode pour éviter d'exécuter le reste du code
}
     
     if (!heure_debut.matches("\\d{1,2}:\\d{2}")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ de l'heure de début doit être au format 'hh:mm'");
    alert.showAndWait();
    return;
}
     if (!heure_fin.matches("\\d{1,2}:\\d{2}")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ de l'heure de fin doit être au format 'hh:mm'");
    alert.showAndWait();
    return;
}
    if (localisation.length() < 5) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ de la localisation doit avoir au moins 5 caractères");
    alert.showAndWait();
    return;
    // afficher un message d'erreur ou une alerte pour indiquer que le champ doit avoir au moins 5 caractères
     
// sortir de la méthode pour éviter d'exécuter le reste du code
}
    if (!localisation.matches("[a-zA-Z]+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Le champ de la localisation doit contenir uniquement des lettres");
    alert.showAndWait();
    return;
    // afficher un message d'erreur ou une alerte pour indiquer que le champ doit contenir uniquement des lettres
     
// sortir de la méthode pour éviter d'exécuter le reste du code
}
     
     
       event e = new event(nom,date_event,heure_debut,heure_fin,localisation,resultat);
       System.out.println(e);
        eventcrud pc = new eventcrud();
      pc.ajouterevent(e);
        
       
        RefreshEventShowListData();
        Notifications.create()
        .title("Nouveau evenement ajouté")
        .text("L'evenement a été ajouté avec succès.")
        .showInformation();
        
        
    }}
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
   



    public void RefreshEventShowListData() throws SQLException {
        EventList.clear();
        
        EventList = EventListData();

         col_idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
             colnomevent.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
        coledebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
         colefin.setCellValueFactory(new PropertyValueFactory<>("heyre_fin"));
colelocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
coletype.setCellValueFactory(new PropertyValueFactory<>("type_event_id"));
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
    ffid.setText(String.valueOf(u.getId()));
   tfnomev.setText(u.getNom_event());
   tfdebut.setText(u.getHeure_debut());
          tffin.setText(u.getHeyre_fin());
          tflocalisation.setText(u.getLocalisation());
       
   
java.sql.Date sqlDate = (java.sql.Date) u.getDate_event();
    
    // Convert java.sqZl.Date to LocalDate
        LocalDate localDate = sqlDate.toLocalDate();
        
        tfdate.setValue(localDate);

     tftype.setValue(String.valueOf(u.getType_event_id()));

}

    @FXML
    private void supprimerevent(ActionEvent event) throws SQLException {
           // Récupérer l'utilisateur sélectionné
   event u = table_event.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        eventcrud us = new eventcrud();
        us.supprimerevents(u.getId());
        table_event.refresh();
        RefreshEventShowListData();
    } 
    else {
        System.out.println("Aucun utilisateur sélectionné");
    }
    }

    @FXML
    private void retour7(ActionEvent event) {
        try {
    FXMLLoader loader = new FXMLLoader( 
        getClass().getResource("DetailsWindow.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}
    }

    @FXML
    private void modifiertype(ActionEvent event) {
       String req = "UPDATE event SET nom_event = '"
                +tfnomev.getText()+"',date_event ='"+Date.valueOf(tfdate.getValue())+"',heure_deb ='"+tfdebut.getText()+"',heure_fin='"+tffin.getText()+"',localisation='"+tflocalisation.getText()+"',type_event_id='"+maMap.get(tftype.getValue()) + "' WHERE id ='"
                + ffid.getText() + "'";
      
           
         try {
            Alert alert;
            if (tfnomev.getText().isEmpty()
                        
                   
                    
                 
                 
                    
                    ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE event ID: " + ffid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                     Statement st = new MyConnection() .getCnx() .prepareStatement(req);
                    st.executeUpdate(req);
 
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                   RefreshEventShowListData();
                    
                }

            }

    
    }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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
    private void mail(ActionEvent event) {
  

    }

@FXML
    private void reservationclick(MouseEvent event) {
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
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void blogclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("afficherBlog.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void commentaireclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AfficherCommentaire.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void typeclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("InscriptionType.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void evenementclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("event.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reclamationclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AdminInterface.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reponseclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AdminInterface.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void hebergementclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("tunisport.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
        
    }

    @FXML
    private void transportclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("transport.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void matchclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Match.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }@FXML
    private void logout(MouseEvent event) {
        
           session US = session.getInstance(session.getId(),session.getFirst_name(),session.getSecond_name(),session.getEmail(),session.getPassword(),session.getPhone());
        US.cleanUserSession();
        
        // redirection vers accueil
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
        loader.setLocation(getClass().getResource("loginView.fxml"));
        try {
        loader.load();

        } catch (IOException ex) {
        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("failed to load");
        System.out.println(ex);
        }
       // LoginController LC = loader.getController();
       
        
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }
      @FXML
    private void Dashboardclick(MouseEvent event) {
        
                   javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Dashboard.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfdebut.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
  
    }

