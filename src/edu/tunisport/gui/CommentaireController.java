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
import javafx.scene.input.MouseEvent;
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
    @FXML
    private void reservationclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Reservation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void blogclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("afficherBlog.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void commentaireclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AfficherCommentaire.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void typeclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("InscriptionType.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void evenementclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("event.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reclamationclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AdminInterface.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reponseclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("AdminInterface.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void hebergementclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("tunisport.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
        
    }

    @FXML
    private void transportclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("transport.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void matchclick(MouseEvent event) {
           javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Match.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfblogcom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    
}
