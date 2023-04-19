/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type.gui;

import event.entities.event;
import event.entities.type;
import event.services.eventcrud;
import event.services.typecrud;
import event.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private void ajouterevent(ActionEvent event) throws SQLException {
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
       
   
java.util.Date date = u.getDate_event();if (date != null) {
    Instant instant = date.toInstant();
    ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    LocalDate localDate = zdt.toLocalDate();
    tfdate.setValue(localDate);
}
     

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
}
