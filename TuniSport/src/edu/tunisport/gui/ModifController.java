/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Blog;
import edu.tunisport.services.BlogCRUD;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class ModifController implements Initializable {
   public Stage stage;

    @FXML
    private TextField tftile;
    @FXML
    private TextField tfDescreption;
    @FXML
    private TextField tfContenu;
    @FXML
    private TextField tfimage;
    @FXML
    private Button ModifButton;
    @FXML
    private Button AnnulerButton;
    String img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Modif(Blog u ) {
        
        //Affichage des données
        tftile.setText(u.getTitre());
        tfDescreption.setText(u.getDescreption());
        tfContenu.setText(u.getContenu());
       
        BlogCRUD SU = new BlogCRUD();
       
        ModifButton.setOnAction((ActionEvent event)-> {
           
       
        // Modification
        u.setTitre(tftile.getText());
        u.setDescreption(tfDescreption.getText());
        u.setContenu(tfContenu.getText());
       
        u.setImage(this.img);
       
        if (tftile.getText().isEmpty() || tfDescreption.getText().isEmpty()|| tfContenu.getText().isEmpty()) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Champs vide");
        alert.showAndWait();
        return;
    }
        
        SU.updateEntity(u);
       
        });
       
        AnnulerButton.setOnAction((ActionEvent e)-> Platform.exit());
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
