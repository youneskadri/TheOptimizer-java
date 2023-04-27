/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginView;
import com.jfoenix.controls.JFXButton;
import entity.user;
import services.UserCrud;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import gui.LoginViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 

    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        
        String first_name = txtFName.getText();
        String second_name = txtSName.getText();
        String email = txtEmail.getText();
        String password = txtFPass.getText();
        String phone = txtPhone.getText();
        UserCrud pcd = new UserCrud();
        user t = new user(first_name, second_name,email,password,phone);
        pcd.addEntity(t);

        //REDIRECTION
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("loginView.fxml"));
            Parent root = loader.load();
            LoginViewController controller = loader.getController();
            controller.setEmail(email);
            controller.setPassword(password);
            txtEmail.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
