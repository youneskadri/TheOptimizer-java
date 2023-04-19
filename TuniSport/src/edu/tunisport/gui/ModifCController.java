/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Commentaire;
import edu.tunisport.services.CommentaireCRUD;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class ModifCController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfcontenu;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Button updatebuttom;
    @FXML
    private TextField tfblog;
    @FXML
    private Button AnnulerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

public void Modif(Commentaire u ) {
       
        //Affichage des données
        tfblog.setText(Integer.toString(u.getBlog_id()));
        tfuser.setText(Integer.toString(u.getUser_id()));
        tfcontenu.setText(u.getContenu_com());
       
       
        CommentaireCRUD SU = new CommentaireCRUD();
        
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
        
        updatebuttom.setOnAction((ActionEvent event)-> {
           
       
        // Modification
        u.setBlog_id(Integer.parseInt(tfblog.getText()));
        u.setUser_id(Integer.parseInt(tfuser.getText()));
        u.setContenu_com(tfcontenu.getText());
       
        u.setDate_c(tfdate.getValue().format(formatter));
       
        if (tfblog.getText().isEmpty() || tfuser.getText().isEmpty()|| tfcontenu.getText().isEmpty()) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Champs vide");
        alert.showAndWait();
        return;
    }
        
        SU.updateEntity(u);
       
        });
               AnnulerButton.setOnAction((ActionEvent e)-> Platform.exit());

    }    
}
