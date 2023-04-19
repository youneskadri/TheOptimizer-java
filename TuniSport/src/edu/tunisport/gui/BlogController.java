/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Blog;
import edu.tunisport.services.BlogCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class BlogController implements Initializable {

    @FXML
    private TextField tfdescreption;
    @FXML
    private TextField tfcontenu;
    @FXML
    private Button tfvalidate;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfimage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterBlog(ActionEvent event) {
          if (tftitle.getText().isEmpty() || tfdescreption.getText().isEmpty()|| tfcontenu.getText().isEmpty()) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Champs vide");
        alert.showAndWait();
        return;
    }
               try {
            
            Blog p = new Blog(tftitle.getText(),tfdescreption.getText(),tfcontenu.getText(),tfimage.getText());
            
            BlogCRUD pc = new BlogCRUD();
            pc.addEntity(p);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dtx.fxml"));
            Parent root = loader.load();
            BlogDetailsController dc = loader.getController();
            dc.setTxtTitre(p.getTitre());
            dc.setTxtDescreption(p.getDescreption());
            dc.setTxtContenu(p.getContenu());
            dc.setTxtImage(p.getImage());
            
            tftitle.getScene().setRoot(root);
            
                } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
