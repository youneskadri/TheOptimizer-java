/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import edu.tunisport.entities.Reclamation;
import edu.tunisport.services.ReclamationCRUD;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author cherif
 */
public class AjouterRecController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField tfsujet;
    @FXML
    private TextArea tfdesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public String filterBadWords(String inputText) {
    List<String> badWords = Arrays.asList("badword1", "badword2", "badword3");
    String regex = "\\b(" + String.join("|", badWords) + ")\\b";
    String filteredText = inputText.replaceAll(regex, "***");
    return filteredText;
}
    
    @FXML
    private void ajouterbtn(ActionEvent event) {

        
        ReclamationCRUD rc = new ReclamationCRUD() ; 
        if(!(tfsujet.getText().length()==0 && tfdesc.getText().length()==0 ))
        {
            rc.ajouter(new Reclamation(1,filterBadWords(tfsujet.getText()),0,filterBadWords(tfdesc.getText())));
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez ajouté la reclamation avec success");
            alert.showAndWait();
             try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ClientInterface.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(AjouterRecController.class.getName()).log(Level.SEVERE, null, ex);
         } 
        }
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champs invalides");
            alert.showAndWait();
        }
    }

    @FXML
    private void Retournerbtn(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("ClientInterface.fxml"));
          pane.getChildren().removeAll() ; 
          pane.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(AjouterRecController.class.getName()).log(Level.SEVERE, null, ex);
         } 
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
  stage1.close();
    } // do what you have to do
      @FXML
     private void reclamationbouton(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) tfdesc.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    
    
}
