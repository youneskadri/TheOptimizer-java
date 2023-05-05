/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.user;
import edu.tunisport.services.UserCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author belha
 */
public class ModifController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    private TextField tfAdresse;
    @FXML
    private Button ModifButton;
    @FXML
    private Button AnnulerButton;
    @FXML
    private TextField tfphone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void Modif(user u ) {
        
        //Affichage des données 
        tfEmail.setText(u.getEmail());
        tfNom.setText(u.getFirst_name());
        tfPrenom.setText(u.getSecond_name());
        tfphone.setText(Integer.toString(u.getPhone()));
        
        UserCrud SU = new UserCrud();
       
        ModifButton.setOnAction((ActionEvent event)-> {
            
        
        // Modification
        u.setEmail(tfEmail.getText());
        u.setFirst_name(tfNom.getText());
        u.setSecond_name(tfPrenom.getText());
       
        u.setPhone(Integer.parseInt(tfphone.getText()));
        
        SU.modifier(u);
        
        });
        
        AnnulerButton.setOnAction((ActionEvent e)-> Platform.exit());
    }
    
}
