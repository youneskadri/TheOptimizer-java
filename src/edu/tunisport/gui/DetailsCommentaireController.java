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
public class DetailsCommentaireController implements Initializable {

    @FXML
    private TextField tfBlogcom;
    @FXML
    private TextField tfusercom;
    @FXML
    private TextField tfcommentaire;
    @FXML
    private TextField tfdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setTxtBlogId(String text) {
        // TODO
        this.tfBlogcom.setText(text);
        
    }    
    public void setTxtUser(String text) {
        // TODO
        this.tfusercom.setText(text);
        
    }
    
    public void setTxtCommentaire(String text) {
        // TODO
        this.tfcommentaire.setText(text);
        
    }
    public void setTxtDate(String text) {
        // TODO
        this.tfdate.setText(text);
        
    }
}
