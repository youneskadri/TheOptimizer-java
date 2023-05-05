/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import edu.tunisport.entities.Reclamation;
import edu.tunisport.entities.Reponse;
import edu.tunisport.services.ReclamationCRUD;
import edu.tunisport.services.ReponseCRUD;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cherif
 */
public class ClientInterfaceController implements Initializable {

    @FXML
    private ListView<MyData> lv;
    @FXML
    private TextField tfsujet;
    @FXML
    private TextArea tfdesc;
    
    @FXML
    private TextArea tfrep;

    public MyData v ; 
    @FXML
    private AnchorPane pane;
    @FXML
    private RadioButton tb;
    @FXML
    private RadioButton recrep;
    @FXML
    private RadioButton recnonrep;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup toggleGroup = new ToggleGroup();
        recrep.setToggleGroup(toggleGroup);
        recnonrep.setToggleGroup(toggleGroup);
        tb.setToggleGroup(toggleGroup);
        tb.setSelected(true);

         lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData>() {
            @Override
            public void changed(ObservableValue<? extends MyData> observable, MyData oldValue, MyData newValue) {
           if(lv.getSelectionModel().getSelectedItem()!=null)
           {
                v = lv.getSelectionModel().getSelectedItem();
             if (v != null) 
             {
              tfsujet.setText(v.getSujet());
              tfdesc.setText(v.getDescription());
              Reponse repn = new Reponse();
              ReponseCRUD repc = new ReponseCRUD();
              repn = repc.getByRec(v.getId());
              if(repn.getReponse()!=null)
              {
              tfrep.setText(repn.getReponse());          
              tfsujet.setEditable(false);
              tfdesc.setEditable(false);
                 }
                else
                {
                 tfrep.setText("");          

               tfsujet.setEditable(true);
               tfdesc.setEditable(true);
                 }
                }
            }
            }

        });
        /**
         * *******************************************************************
         */
        
        ReclamationCRUD vc = new ReclamationCRUD() ;
        List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) 
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
          

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
    }    

    @FXML
    private void modifierbtn(ActionEvent event) {
        ReclamationCRUD rc = new ReclamationCRUD() ;
        Reclamation r = rc.getByID(v.getId()); 
        if(r.getSujet().equals(tfsujet.getText()) && r.getDescription().equals(tfdesc.getText()))
        {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("veuillez modifier au moins un champ!");
            alert.showAndWait();
        }
        else
        {
            rc.modifier(new Reclamation(v.getId(),v.getUser_id(),tfsujet.getText(),0,tfdesc.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Modification reussite");
            alert.showAndWait();
             List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(rc.afficher());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) 
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
          

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
        }
    }

    @FXML
    private void supprimerbtn(ActionEvent event) {
          ReclamationCRUD rc = new ReclamationCRUD() ;
        rc.supprimer(v.getId());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("suppression reussite");
            alert.showAndWait();
             List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(rc.afficher());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) 
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
          

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);
        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
        tfdesc.setText(""); 
        tfsujet.setText("");
        tfrep.setText("");
    }

    @FXML
    private void ajouterbtn(ActionEvent event) {
         try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("AjouterRec.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(AjouterRecController.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }

    @FXML
    private void rechercherbtn(ActionEvent event) {
       
        ReclamationCRUD vc = new ReclamationCRUD() ;
        List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());

        List<MyData> lmd = new ArrayList<>();
        {
            if(tb.isSelected())
                    for (int i = 0; i < lv2.size(); i++) 
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
            if (recrep.isSelected())
            {
                for (int i = 0; i < lv2.size(); i++) 
                {
                    Reclamation r = vc.getByID(lv2.get(i).getId()); 
                    if(r.getStatut()==1)
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user())) ;
                }
            }
            if (recnonrep.isSelected())
             {
                for (int i = 0; i < lv2.size(); i++) 
                {
                    Reclamation r = vc.getByID(lv2.get(i).getId()); 
                    if(r.getStatut()==0)
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user())) ;
                }
            }
        }

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
       
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
      
        @FXML
    private void gotoblog(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
        
        @FXML
    private void gotoreclamation(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
        
        @FXML
    private void gotoevent(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
        
        @FXML
    private void gotohet(ActionEvent event) {

         javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("ClientH.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
        
        @FXML
    private void gotomatch(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
        
        @FXML
    private void gotoreservation(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    }
    

