/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class BlogDetailsController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tfdescreption;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TextField tfimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
     public void setTxtTitre(String text) {
        // TODO
        this.tftitre.setText(text);
        
    }    
    public void setTxtDescreption(String text) {
        // TODO
        this.tfdescreption.setText(text);
        
    }
    
    public void setTxtContenu(String text) {
        // TODO
        this.tfcontenu.setText(text);
        
    }
    public void setTxtImage(String text) {
        // TODO
        this.tfimage.setText(text);
        
    }
    
    
}
