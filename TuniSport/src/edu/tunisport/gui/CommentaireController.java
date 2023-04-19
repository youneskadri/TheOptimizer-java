/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Blog;
import edu.tunisport.entities.Commentaire;
import edu.tunisport.services.BlogCRUD;
import edu.tunisport.services.CommentaireCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author medah
 */
public class CommentaireController implements Initializable {

    @FXML
    private TextField tfblogcom;
    @FXML
    private TextField tfusercom;
    @FXML
    private TextField tfcontenucom;
    @FXML
    private Button tfvalidatecom;
    @FXML
    private DatePicker tfdatecom;
    @FXML
    private TableView<Blog> tftable;
    @FXML
    private TableColumn<Blog, String> tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficheComm();
    }    

    
    public void AfficheComm(){
    BlogCRUD u = new BlogCRUD();

        ObservableList<Blog> BlogList = u.AfficherBlog();

    tfid.setCellValueFactory(new PropertyValueFactory<Blog,String>("id"));        
    tftable.setItems(BlogList);

    
    }
    @FXML
    private void addCommentaire(ActionEvent event) {
       
        try {
            //System.out.println("test entie:",Integer.toString(tfblogcom.getText()));
        
        
            Blog blogid = (Blog) tftable.getSelectionModel().getSelectedItem();
            System.out.println(blogid.getId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            String formattedString = tfdatecom.getValue().format(formatter);
            //System.out.println(Integer.parseInt(tfblogcom.getText()));
            Commentaire p = new Commentaire(blogid.getId(),Integer.parseInt(tfusercom.getText()),tfcontenucom.getText(),tfdatecom.getValue().format(formatter));
            //System.out.println(p.toString());
            CommentaireCRUD pc = new CommentaireCRUD();
            pc.addEntity(p);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCommentaire.fxml"));
            Parent root = loader.load();
            DetailsCommentaireController dc = loader.getController();
            dc.setTxtBlogId(Integer.toString(p.getBlog_id()));
            dc.setTxtUser(Integer.toString(p.getUser_id()));
            dc.setTxtCommentaire(p.getContenu_com());
            dc.setTxtDate(p.getDate_c());
            
            tfblogcom.getScene().setRoot(root);
            
                } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
