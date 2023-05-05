/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;


import edu.tunisport.entities.MatchF;
import edu.tunisport.entities.Reservation;
import edu.tunisport.services.ReservationCRUD;
import edu.tunisport.tools.MyConnection;
import edu.tunisport.tools.session;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationController implements Initializable {

    @FXML
    private Button btnmatchs;
    @FXML
    private AnchorPane reservation_form;
    @FXML
    private TableView<Reservation> reservation_table;
    @FXML
    private TableColumn<Reservation, String> date_col;
    @FXML
    private TableColumn<Reservation, String> etat_col;
    @FXML
    private TableColumn<Reservation, String> match_col;
    @FXML
    private TableColumn<Reservation, String> nbrbillet_col;
    @FXML
    private TableColumn<Reservation, String> user_col;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnupdate;
    private AnchorPane add_match_form;
    @FXML
    private ComboBox<String> cbuser;
    @FXML
    private ComboBox<String> cbmatch;
    @FXML
    private DatePicker dpdate;
    @FXML
    private Button btnajouter;
    @FXML
    private Label letat;
    @FXML
    private Label ldate;
    @FXML
    private Label luser;
    @FXML
    private Label lmatch;
    @FXML
    private Label lnbrbillet;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private TextField tfetat;
    @FXML
    private Button btnrevenir;
    @FXML
    private AnchorPane update_reservation_form;
    @FXML
    private Label ldate1;
    @FXML
    private Label lrA1;
    @FXML
    private Label lrB1;
    @FXML
    private Label lprix1;
    @FXML
    private Label leqA1;
    @FXML
    private Label leqB1;
    @FXML
    private Label ltype1;
    @FXML
    private Label lstade1;
    @FXML
    private Label ltournoi1;
    @FXML
    private Button btnmodifier;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private ComboBox<String> cbmatch1;
    @FXML
    private ComboBox<String> cbuser1;
    @FXML
    private TextField tfid1;
    @FXML
    private Button btnrevenir1;
    @FXML
    private TextField tfnbrbillet1;
    @FXML
    private TextField tfetat1;
    
    private MatchF selectedMatch;
    
    
    ObservableList<String> optionstype = FXCollections.observableArrayList();
    ObservableList<String> optionstype1 = FXCollections.observableArrayList();
    
    Map<String, Integer> maMap = new HashMap<String, Integer>();
    Map<String, Integer> maMap1 = new HashMap<String, Integer>();
   
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private TableColumn<Reservation, String> id_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnmatchs.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/Views/Match.fxml"));
               Scene scene=btnmatchs.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               
           }
        });
        try {
            ReservationShowListData();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        fillcomboUser();
        cbuser.setItems(optionstype);
        cbuser1.setItems(optionstype);
        
        fillcomboMatch();
        cbmatch.setItems(optionstype1);
        cbmatch1.setItems(optionstype1);
        
        
        
       
    }    

    @FXML
    private void switchform(ActionEvent event) {
        if (event.getSource() == btnrevenir) {
            reservation_form.setVisible(true);
            add_reservation_form.setVisible(false);
            
        } else if (event.getSource() == btnadd) {
            reservation_form.setVisible(false);
            add_reservation_form.setVisible(true);
            
        }
    }
    
    @FXML
    private void switchform2(ActionEvent event) {
        if (event.getSource() == btnrevenir1) {
            reservation_form.setVisible(true);
            update_reservation_form.setVisible(false);
            
        } else if (event.getSource() == btnupdate) {
            reservation_form.setVisible(false);
            update_reservation_form.setVisible(true);
            
        }
    }

    @FXML
    private void supprimerReservation(ActionEvent event) throws SQLException {
        Reservation r = reservation_table.getSelectionModel().getSelectedItem();
        Alert alert2;
        if (r != null) {
            alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Message de confirmation");
            alert2.setHeaderText(null);
            alert2.setContentText("Voulez vous supprimer cette reservation?");
            Optional<ButtonType> option = alert2.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                ReservationCRUD us = new ReservationCRUD();
                us.delete(r.getId());
                alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Information Message");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Reservation supprimée avec succès!");
                        alert2.showAndWait();
                ReservationShowListData();
            }

        } else {
            alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Message d'erreur");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Aucune reservation selectionnée!");
                        alert2.showAndWait();
        }
    }

    

    @FXML
    private void ajouterReservation(ActionEvent event) {
        LocalDate selectedDate = dpdate.getValue();
            LocalDate today = LocalDate.now();
            
            try{
                Alert alert2;
                
            if (cbuser.getSelectionModel().getSelectedItem() == null
                    || selectedDate == null
                    || cbmatch.getSelectionModel().getSelectedItem() == null
                    
                    || tfnbrbillet.getText().isEmpty()
                    || tfetat.getText().isEmpty()
                    ){
                    luser.setVisible(true);
                    lmatch.setVisible(true);
                    lnbrbillet.setVisible(true);
                    letat.setVisible(true);
                    
                    alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Message d'erreur");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Veuillez remplir tous les champs!");
                    alert2.showAndWait();
                 
            }   else if (Integer.parseInt(tfnbrbillet.getText()) < 0){
                     tfnbrbillet.setVisible(true);
                }
                else if (selectedDate.isBefore(today)){
                    ldate.setVisible(true);
                }
                
            else {
                luser.setVisible(false);
                lmatch.setVisible(false);
                lnbrbillet.setVisible(false);
                letat.setVisible(false);
                
                Reservation r = new Reservation( 
                        maMap.get(cbuser.getValue()).toString(),
                        maMap1.get(cbmatch.getValue()).toString(),
                        Date.valueOf(dpdate.getValue()),
                        Integer.parseInt(tfnbrbillet.getText()),
                        tfetat.getText()
                    );
                alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Message de confirmation");
                alert2.setHeaderText(null);
                alert2.setContentText("Voulez vous ajouter cette reservation?");
                Optional<ButtonType> option = alert2.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    ReservationCRUD pc = new ReservationCRUD();
                    pc.add(r);

                    alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information Message");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Reservation ajoutée avec succès!");
                    alert2.showAndWait();

                    ReservationShowListData();
                    
                }
            }
            }
            catch (SQLException ex) {
                    System.out.println(ex.getMessage());;
                }
    }
    
    @FXML
    private void modifierReservation(ActionEvent event) {
        
        String req = "UPDATE reservation SET user_id = '"
                + maMap.get(cbuser1.getValue()) + "', match_f_id = '"
                + maMap1.get(cbmatch1.getValue()) + "', date_resevation = '"
                + Date.valueOf(dpdate1.getValue()) + "', nombre_billet = '"
                + tfnbrbillet1.getText()+ "', etat = '"
                + tfetat1.getText()
                + "' WHERE id ='"
                + tfid1.getText() + "'";

       
            con  = MyConnection.getInstance().getCnx();
         try {
            Alert alert;
            if (cbuser1.getSelectionModel().getSelectedItem() == null
                    || cbmatch1.getSelectionModel().getSelectedItem() == null
                    || tfnbrbillet1.getText().isEmpty()
                    || tfetat1.getText().isEmpty()
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
                alert.setContentText("Voulez vous modifier la reservation d'ID: " + tfid1.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement st = con.createStatement();
                    st.executeUpdate(req);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Reservation mise à jour avec succès!");
                    alert.showAndWait();

                    ReservationShowListData();
                    
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public ObservableList<Reservation> ReservationListData() throws SQLException {

        return new ReservationCRUD().read();
    }
    
    private ObservableList<Reservation> ReservationList;
    
    public void ReservationShowListData() throws SQLException {
        ReservationList = ReservationListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
         etat_col.setCellValueFactory(new PropertyValueFactory<>("etat"));
         match_col.setCellValueFactory(new PropertyValueFactory<>("match_id"));
         nbrbillet_col.setCellValueFactory(new PropertyValueFactory<>("nbr_billet"));
        user_col.setCellValueFactory(new PropertyValueFactory<>("user_id"));
 
        reservation_table.setItems(ReservationList);

    }
    
    
    
    public void ReservationSelect() {

        Reservation r = reservation_table.getSelectionModel().getSelectedItem();
        int num = reservation_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tfid1.setText(String.valueOf(r.getId()));
        cbuser1.setValue(r.getUser_id());
        cbmatch1.setValue(String.valueOf(r.getMatch_id()));
        tfnbrbillet1.setText(String.valueOf(r.getNbr_billet()));
        tfetat1.setText(String.valueOf(r.getEtat()));
        java.sql.Date sqlDate = (java.sql.Date) r.getDate();
    
    // Convert java.sqZl.Date to LocalDate
        LocalDate localDate = sqlDate.toLocalDate();
        
        dpdate1.setValue(localDate);

        
    }
   
    
    public void fillcomboUser() {
       
        con  = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from user";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap.put(rs.getString("username"), rs.getInt("id"));
                       
                optionstype.add(rs.getString("username"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    public void fillcomboMatch() {
       
        con  = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from match_f";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap1.put(rs.getString("id"), rs.getInt("id"));
                       
                optionstype1.add(rs.getString("id"));
            }

        } catch (SQLException ex) {

        }   
    }
    public void setSelectedMatch(MatchF match) {
        this.selectedMatch = match;
        cbmatch.setValue(String.valueOf(match.getId())); 
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat1.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    @FXML
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
                        
        Stage stage1 = (Stage) tfnbrbillet1.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
}
