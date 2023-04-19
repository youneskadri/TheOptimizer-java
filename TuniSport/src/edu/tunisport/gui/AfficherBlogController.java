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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class AfficherBlogController implements Initializable {

    @FXML
    private TableView<Blog> tftable;
    @FXML
    private TableColumn<Blog, String> tftitle;
    @FXML
    private TableColumn<Blog, String> tfdescreption;
    @FXML
    private TableColumn<Blog, String> tfcontenu;
    @FXML
    private TableColumn<Blog, String> tfimage;
    ObservableList<Blog> BlogList;
    @FXML
    private TableColumn<Blog, String> tftid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficheBlog();
    }    
       public void AfficheBlog(){
        BlogCRUD u = new BlogCRUD();

        ObservableList<Blog> BlogList = u.AfficherBlog();
       
    tftitle.setCellValueFactory(new PropertyValueFactory<Blog,String>("titre"));
     tfdescreption.setCellValueFactory(new PropertyValueFactory<Blog,String>("descreption"));
    tfcontenu.setCellValueFactory(new PropertyValueFactory<Blog,String>("contenu"));
    tfimage.setCellValueFactory(new PropertyValueFactory<Blog,String>("image"));
    tftid.setCellValueFactory(new PropertyValueFactory<Blog,String>("id"));

   
          tftable.setItems(BlogList);
        }

    @FXML
    private void addonaction(ActionEvent event) throws IOException {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Blog.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                        }
               
              Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }

    @FXML
    private void deleteOnaction(ActionEvent event) {
        
        BlogCRUD u=new BlogCRUD();
   //       commandeplat t = tvcommande.getSelectionModel().getSelectedItem();
        Blog Blog = (Blog) tftable.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        u.SupprimerBlog(Blog);
         AfficheBlog();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Blog supprimé");
                alert.showAndWait();  
    }

    @FXML
    private void updateonaction(ActionEvent event) {
            // selectionne l'utilisateur à modifier
  Blog U = tftable.getSelectionModel().getSelectedItem();

// fenetre modification
 javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Modif.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Appel controleur de modifier
                        ModifController MC = loader.getController();
                       
                        MC.Modif(U);
                       
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
    }
    
}
