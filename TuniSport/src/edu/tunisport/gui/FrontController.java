/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.tunisport.entities.Blog;
import edu.tunisport.services.BlogCRUD;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author medah
 */
public class FrontController implements Initializable {

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
    @FXML
    private TableColumn<Blog, String> tftid;
    @FXML
    private TableColumn<Blog, String> tflike;
    @FXML
    private TableColumn<Blog, String> tfaction;
    @FXML
    private AnchorPane reservation_form;
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<?> cbmatch;
    @FXML
    private ComboBox<?> cbuser;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane reservation_details;
    @FXML
    private ListView<?> reservationList;
    @FXML
    private Button btnpaiement;
    @FXML
    private Button btnupdate;
    @FXML
    private AnchorPane update_reservation_form;
    @FXML
    private Button btnretour1;
    @FXML
    private TextField tfnbrbillet1;
    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<?> cbuser1;
    @FXML
    private ComboBox<?> cbmatch1;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private TextField tfid;
    @FXML
    private AnchorPane classement_form;
    @FXML
    private TableView<?> classement_table;
    @FXML
    private TableColumn<?, ?> rangCol;
    @FXML
    private TableColumn<?, ?> equipeCol;
    @FXML
    private TableColumn<?, ?> mjCol;
    @FXML
    private TableColumn<?, ?> victoireCol;
    @FXML
    private TableColumn<?, ?> nulCol;
    @FXML
    private TableColumn<?, ?> defaiteCol;
    @FXML
    private TableColumn<?, ?> bmCol;
    @FXML
    private TableColumn<?, ?> beCol;
    @FXML
    private TableColumn<?, ?> dbCol;
    @FXML
    private TableColumn<?, ?> pointsCol;
    @FXML
    private AnchorPane resultats_form;
    @FXML
    private Button btnretour2;
    @FXML
    private ListView<?> MatchList1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    tflike.setCellValueFactory(new PropertyValueFactory<Blog,String>("likes"));
         //add cell of button edit 
         Callback<TableColumn<Blog, String>, TableCell<Blog, String>> cellFoctory = (TableColumn<Blog, String> param) -> {
            // make cell containing buttons
            final TableCell<Blog, String> cell = new TableCell<Blog, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button likeButton = new Button("Like");
                        Button detectButton = new Button("Details");
                 
                        likeButton.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        detectButton.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                      
                         likeButton.setOnAction((ActionEvent event) -> {
                            Blog U = tftable.getSelectionModel().getSelectedItem();
                            int likess = U.getLikes();
                            U.setLikes(likess+1);

                            BlogCRUD crud=new BlogCRUD();
                            crud.modifierLike(U);

                           

                        });
                         
                         detectButton.setOnAction((ActionEvent event) -> {
                            Blog U = tftable.getSelectionModel().getSelectedItem();
                             javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Details.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                          
                        // Appel controleur de modifier
                        DetailsController MC = loader.getController();
                       
                            try {
                                MC.Detail(U);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                                
                        });
                    
                      

                        HBox managebtn = new HBox(likeButton, detectButton);
                       

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         tfaction.setCellFactory(cellFoctory);
       
       
       tftable.setItems(BlogList);
          
          
            
                    
                    }
           
  
    @FXML
    private void ajouterReservation(ActionEvent event) {
    }

    @FXML
    private void reserverBillet(ActionEvent event) {
    }

    @FXML
    private void ReservationSelect(MouseEvent event) {
    }

    @FXML
    private void switchform4(ActionEvent event) {
    }

    @FXML
    private void switchform2(ActionEvent event) {
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
    }

    @FXML
    private void switchform3(ActionEvent event) {
    }
    
}
