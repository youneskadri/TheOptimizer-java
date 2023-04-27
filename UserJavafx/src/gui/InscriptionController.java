/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author belha
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtSName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtFPass;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXTextField txtPhone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
    }
    
}
