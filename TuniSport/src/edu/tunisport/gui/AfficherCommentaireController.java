/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Commentaire;
import edu.tunisport.services.CommentaireCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class AfficherCommentaireController implements Initializable {

    @FXML
    private TableColumn<Commentaire, String> tfid;
    @FXML
    private TableColumn<Commentaire, String> tfuser;
    @FXML
    private TableColumn<Commentaire, String> tfdate;
    
    @FXML
    private TableView<Commentaire> tftable;
    @FXML
    private TableColumn<Commentaire, String> tfblog;
    @FXML
    private TableColumn<Commentaire, String> tfcontenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficheCommentaire();
    }    
           public void AfficheCommentaire(){
        CommentaireCRUD u = new CommentaireCRUD();

        ObservableList<Commentaire> CommentaireList = u.displayEntities();
        
    tfid.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("id"));  
    tfblog.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("blog_id"));
    tfuser.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("user_id"));
    tfcontenu.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("contenu_com"));
    tfdate.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("date_c"));
 

   
          tftable.setItems(CommentaireList);
        }

    @FXML
    private void ajouterCommentaire(ActionEvent event) throws IOException {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Commentaire.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(AfficherCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                        }
               
              Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void deletecommentaire(ActionEvent event) {
        
        CommentaireCRUD u=new CommentaireCRUD();
   //       commandeplat t = tvcommande.getSelectionModel().getSelectedItem();
        Commentaire Commentaire = (Commentaire) tftable.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        u.deleteEntity(Commentaire);
         AfficheCommentaire();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Commentaire supprimé");
                alert.showAndWait();  
    }
    @FXML
    private void modofiercommentaire(ActionEvent event) {
            // selectionne l'utilisateur à modifier
  Commentaire U = tftable.getSelectionModel().getSelectedItem();

// fenetre modification
 javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("ModifC.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(AfficherCommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Appel controleur de modifier
                        ModifCController MC = loader.getController();
                       
                        MC.Modif(U);
                       
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }
 

   
}
