/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.tunisport.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private AnchorPane log;
    @FXML
    private JFXButton Login_Button;
    @FXML
    private Label forgotPss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    @FXML
    private void loginButton(ActionEvent event) throws SQLException {
  
        ResultSet rs = null;
   
            
            String requete = "SELECT * FROM user WHERE EMAIL =? AND PASSWORD = ?";
             PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            st.setString(1,textEmail.getText());
            st.setString(2,textPassword.getText());
    
            rs = st.executeQuery();
            
            if (rs.next()){
                 String userRole = rs.getString("roles");
                if (userRole.equals("['ROLE_ADMIN']")) {
                 try {
                     
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
           DashboardController controller = loader.getController();
       
            textEmail.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            
   
    } if (userRole.equals("['ROLE_USER']")){
                   try {
                     
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("Front.fxml"));
            Parent root = loader.load();
           FrontController controller = loader.getController();
       
            textEmail.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
           
    
    }
}

    }

    void settNom(String msg) {
       this.textEmail.setText(msg);
    }

    void settPrenom(String msg) {
          this.textPassword.setText(msg);
    }

    @FXML
    private void resetPass(MouseEvent event) {
            try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("forgotPass.fxml"));
            Parent root = loader.load();
          ForgotPassController controller = loader.getController();
       
            textEmail.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
   
    }
    
        

