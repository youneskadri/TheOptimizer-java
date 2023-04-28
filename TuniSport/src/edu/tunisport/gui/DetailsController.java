/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Blog;
import edu.tunisport.entities.Commentaire;
import edu.tunisport.services.CommentaireCRUD;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class DetailsController implements Initializable {

    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfdescreption;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TableView<Commentaire> tftable;
    @FXML
    private TableColumn<Commentaire, String> tfid;
    @FXML
    private TableColumn<Commentaire, String> tfblog;
    @FXML
    private TableColumn<Commentaire, String> tfuser;
    @FXML
    private TableColumn<Commentaire, String> tfcontenu1;
    @FXML
    private TableColumn<Commentaire, String> tfdate;
    @FXML
    private TextField comment;
    
    int k;
    @FXML
    private AnchorPane reservation_form;
    @FXML
    private ImageView Imageview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
     public void Detail(Blog u ) throws FileNotFoundException {
        
        //Affichage des données
        tftitle.setText(u.getTitre());
        tfdescreption.setText(u.getDescreption());
        tfcontenu.setText(u.getContenu());
        FileInputStream stream = new FileInputStream(u.getImage());

        Imageview.setImage(new Image(stream));
     AfficheCommentaire(u.getId());
 this.k = u.getId();
     
     
}
     
        public void AfficheCommentaire(int b){
        CommentaireCRUD u = new CommentaireCRUD();
       
        
        ObservableList<Commentaire> CommentaireList = u.displayEntitieswithcommentaire(b);
        
    tfid.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("id"));  
    tfblog.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("blog_id"));
    tfuser.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("user_id"));
    tfcontenu1.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("contenu_com"));
    tfdate.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("date_c"));
 

   
          tftable.setItems(CommentaireList);
        }

        @FXML
        public void RefreshMatchShowListData()throws SQLException{
        CommentaireCRUD u = new CommentaireCRUD();
       
        
        ObservableList<Commentaire> CommentaireList = u.displayEntitieswithcommentaire(this.k);
        
    tfid.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("id"));  
    tfblog.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("blog_id"));
    tfuser.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("user_id"));
    tfcontenu1.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("contenu_com"));
    tfdate.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("date_c"));
 

   
          tftable.setItems(CommentaireList);
        }
        
  

    @FXML
    private void ajoutercomment(ActionEvent event) {
        Commentaire p = new Commentaire(this.k,1,comment.getText(),"2018-01-01");

        CommentaireCRUD pc = new CommentaireCRUD();
        pc.addEntity(p);
        
    }


}