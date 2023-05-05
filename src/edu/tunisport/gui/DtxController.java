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
public class DtxController implements Initializable {

    @FXML
    private TextField tfnomshow;
    @FXML
    private TextField tfprenomshow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setTxtNom(String text) {
        // TODO
        this.tfnomshow.setText(text);
        
    }    
    public void setTxtPreNom(String text) {
        // TODO
        this.tfprenomshow.setText(text);
        
    }   
    
}
