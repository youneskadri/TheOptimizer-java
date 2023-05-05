/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import edu.tunisport.entities.user;
import edu.tunisport.services.UserCrud;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.tunisport.tools.MyConnection;

import java.net.URI;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private JFXTextField txtPhone;
    @FXML
    private JFXButton registerButton;
    @FXML
    private AnchorPane inscriptionPanel;
       @FXML
    private Label MailLabel;
    @FXML
    private Label PwLabel;
    @FXML
    private Label prenLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label phoneL;
    
 public static final String ACCOUNT_SID = "AC082309bd905281fd03e8565b2ad57920";
  public static final String AUTH_TOKEN = "aed0976348e960d35fe37df207e1e26f";
  
    ObservableList<String> choiceboxList = FXCollections.observableArrayList("What's your pet's name?","What's your favorite food?","Who was your childhood hero?");
    @FXML
    private JFXComboBox<String> choicebox;
    @FXML
    private JFXTextField answer;
    private AnchorPane verifAccount;
    @FXML
    private JFXTextField codetxt;
    @FXML
    private AnchorPane verifAccoun;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    choicebox.setValue("Choose your question");
    choicebox.setItems(choiceboxList);
     verifAccoun.setVisible(false);
     
    }

 

    @FXML
    private void action(ActionEvent event) {
            verifAccoun.setVisible(false);
int phone = Integer.parseInt(txtPhone.getText());
String securityAnswer = answer.getText();
String securityQuestion = choicebox.getValue().toString();
        user p = new user(txtFName.getText(),txtSName.getText(),txtEmail.getText(),txtFPass.getText(),phone,securityAnswer,securityQuestion);
        UserCrud pc = new UserCrud();
        System.out.println(pc.displayEntities());
           int err = 0 ; //compteur d'erreur
        // controle de saisie sur les infos inscription
        Boolean MailConf = ControlEmail(txtEmail.getText());
       
       if (txtFName.getText().length() == 0) {
            nomLabel.setText("Veuillez inserer votre prenom");
            err++;
        } 
          if (txtSName.getText().length() == 0) {
            prenLabel.setText("Veuillez inserer votre nom");
            err++;
        } 
       
         
                //controle de mot de passe
        if (txtFPass.getText().length()<6) {
            PwLabel.setText("Mot de passe trop court");
            err++;
        }    
               if (txtPhone.getText().length() > 8 ||txtPhone.getText().length() < 8   ) {
            phoneL.setText("phone number not Correct");
            err++;
        }  

       
                 // controle de l'email 
                
        //Controle de saisie de l'email (avec @ etc )        
        if (MailConf == false) {
            MailLabel.setText("Veuillez verifier votre mail");
            err++;
        }
     
      
        if (err > 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inscription");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez verifier les champs");

                alert.showAndWait();
        }
        
        else {
         /*      verifAccoun.setVisible(true);

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      int code = p.getToken();
       
        String msg = "your code verification is : " +code;
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+21628683199"),
      new com.twilio.type.PhoneNumber("+16074247052"),
      msg )
    .create();

    System.out.println(message.getSid());
    System.out.println(code);
    System.out.println(msg);
              verifAccoun.setVisible(true);
    
   */
    
        addEntity(p);
        login();
  

        
}}
    
    
 public boolean ControlEmail(String mail) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
    Pattern pattern = Pattern.compile(masque);
    Matcher controler = pattern.matcher(mail);
    if (controler.matches()){
        return true ; 
    }
    else 
        return false ;
        
    }

   
    public void addEntity(user t) {
        try {
         
            String requete = "INSERT INTO user (id,first_name,second_name,email,phone,password,roles,token,answer,question)"+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
                    
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setString(1, Integer.toString(t.getId()));
            st.setString(2, t.getFirst_name());
            st.setString(3, t.getSecond_name());
            st.setString(4, t.getEmail());
            st.setString(5, Integer.toString(t.getPhone()));
            st.setString(6, t.getPassword());
            st.setString(7, t.getRoles());
            st.setString(8, Integer.toString(t.getToken()));
            st.setString(9,answer.getText());
            st.setString(10, choicebox.getValue().toString());

            
           
            st.executeUpdate();
            System.out.println("user ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
   String getMail() {
       this.MailLabel.getText();
        return null;
    }

   /* @FXML
      private void validateAcc(ActionEvent event) {
 
      
       
         if(validateAccountUser()){
             try {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("loginView.fxml"));
            Parent root = loader.load();
           LoginViewController controller = loader.getController();
       
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
    
    }
*/
    @FXML
    private void resendCode(ActionEvent event) {
    }
   void Affiche(){
                verifAccoun.setVisible(false);
                verifAccount.setVisible(false);

   }

    @FXML
    private void validateAcc(ActionEvent event) {
    }

   /** private void loginShow(ActionEvent event) throws IOException {
       FXMLLoader.load(getClass().getResource("loginView.fxml"));
       * 

* }**/
    
    
    
    
     private void login() {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("loginView.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) txtFName.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
}
