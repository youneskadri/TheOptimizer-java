 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author belha
 */
public class LoginViewController implements Initializable {

    @FXML
    private JFXTextField textEmail;
    @FXML
    private JFXPasswordField textPassword;
    @FXML
    private Label inscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setEmail(String message) {
        this.textEmail.setText(message);
    }

    public void setPassword(String message) {
        this.textPassword.setText(message);
    }

    @FXML
    private void inscriptionShow(MouseEvent event) {
        
        
    }
    
}
