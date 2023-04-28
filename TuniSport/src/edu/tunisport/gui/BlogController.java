/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import Views.getData;
import edu.tunisport.entities.Blog;
import edu.tunisport.services.BlogCRUD;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class BlogController implements Initializable {
    
   public Stage stage;

    
    private TextField tfdescreption;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfdescreption1;
    
    String img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterBlog(ActionEvent event) {
        /*
          if (tftitle.getText().isEmpty() || tfdescreption.getText().isEmpty()|| tfcontenu.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Champs vide");
        alert.showAndWait();
        return;
    }*/

              System.out.println("1");
          Blog p = new Blog(tftitle.getText(),tfdescreption1.getText(),tfcontenu.getText(),img);
                        System.out.println("2");
          BlogCRUD pc = new BlogCRUD();
          
          pc.addEntity(p);
    }

    @FXML
    private void insererImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
				
				
				fileChooser.setTitle("Open My File");
				
				
				
				File selectedFile = fileChooser.showOpenDialog(stage);
				if (selectedFile != null) {
					System.out.println("Open File");
					this.img = selectedFile.getPath()
                                                ;
				}
    
}
}