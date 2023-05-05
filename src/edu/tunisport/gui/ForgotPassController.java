/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.tunisport.entities.user;
import static edu.tunisport.gui.InscriptionController.ACCOUNT_SID;
import static edu.tunisport.gui.InscriptionController.AUTH_TOKEN;
import edu.tunisport.services.UserCrud;
import edu.tunisport.tools.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author belha
 */
public class ForgotPassController implements Initializable {

    @FXML
    private JFXTextField emailReset;
    @FXML
    private JFXTextField answer;
    @FXML
  private Label  ErreurLabel;
    private AnchorPane verifAccoun;
  PreparedStatement ps,ps1; 
     String ans;
       String pass;
    @FXML
    private AnchorPane shoawpass;
    private JFXTextField password;
    @FXML
    private Label back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void resetPass(ActionEvent event) {
        
        try {
        // Create database connection

        // Create SQL query to check if email and answer match a record in the database
        String query = "SELECT password FROM user WHERE email = ? AND answer = ?";
 PreparedStatement stmt = MyConnection.getInstance().getCnx()
                    .prepareStatement(query);
 stmt.setString(1, emailReset.getText());
        stmt.setString(2, answer.getText());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // If email and answer match a record, display password
            String password = rs.getString("password");
            ErreurLabel.setText("Your password is: " + password);
               Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

   
       
     
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+21628683199"),
      new com.twilio.type.PhoneNumber("+16074247052"),
     "your password is"+password )
    .create();
        } else {
            // If email and answer do not match a record, display error message
            password.setText("Email and/or answer are incorrect.");
        }

        // Close database connection
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
                    
             
              
              
              /*    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    
                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                    loader.setLocation(getClass().getResource("NewPassword.fxml"));
                    try {
                        loader.load();
                      
                    } catch (IOException ex) {
                        Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    NewPasswordController NPC = loader.getController();
                    NPC.Newpw(EmailLabel.getText());
                    
                    Parent parent = loader.getRoot();
                    stage.setScene(new Scene(parent));
                    stage.show();
             } 
             });*/
                    
         
   
    
    
    }
    
     void retrivePsw(ActionEvent event) throws IOException {
        
          if(ans.equals(answer.getText().trim())){
            password.setText(pass);
        }
        else {
         ErreurLabel.setText("Your answer is wrong. Please try again");
        }
            
    }

    @FXML
    private void GoBack(MouseEvent event) {
          try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("loginView.fxml"));
            Parent root = loader.load();
          LoginViewController controller = loader.getController();
       
            emailReset.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    }

  /*  @FXML
    private void validateAcc(ActionEvent event) {
        
         if(validateAccountUser()){
             try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("newPassView.fxml"));
            Parent root = loader.load();
           NewPassViewController controller = loader.getController();
       
            codetxt.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Token Verification Error");
        alert.setHeaderText(null);
        alert.setContentText("The token you entered is incorrect. Please try again.");
        alert.showAndWait();
         }
    }

    @FXML
    private void resendCode(ActionEvent event) {
    }
    
    
     private boolean validateAccountUser() {
 boolean isTokenValid = false;
        try {
           String sql = "SELECT COUNT(*) FROM user WHERE token = ?";
         PreparedStatement statement = MyConnection.getInstance().getCnx()
                    .prepareStatement(sql);
            statement.setString(1,codetxt.getText());
           
            
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int count = result.getInt(1);
                System.out.println(count);
                if (count > 0) {
                    isTokenValid = true;
                }
               
            }
        
                 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTokenValid;   
    
    }*/

