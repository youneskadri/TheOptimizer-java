/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import javafx.scene.control.Button;
import edu.tunisport.entities.Blog;
import edu.tunisport.services.BlogCRUD;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.io.*;
import java.net.*;
import java.time.*;
import sun.net.www.http.HttpClient;
import java.net.URI;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private TableColumn<Blog, String> tflike;
    @FXML
    private TableColumn<Blog, String>  tfaction;
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
    tflike.setCellValueFactory(new PropertyValueFactory<Blog,String>("likes"));
    
        tfimage.setCellFactory(column ->{
            return new TableCell<Blog,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String imageName, boolean empty) {
                    super.updateItem(imageName, empty);
                    if (imageName == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            FileInputStream stream = new FileInputStream(imageName);
                            Image image = new Image(stream);
                            imageView.setImage(image);
                            imageView.setFitWidth(50); 
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        });
    
    
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
                        Button detectButton = new Button("Detect");
                        Button ResumeButton = new Button("Resume");
                 
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
                        
                        ResumeButton.setStyle(
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
                            String titre = U.getTitre();
                            String quary = "{\r\"language\": \"english\",\r\"text\": \" "+titre+"\"\r}";
                            try {
                                HttpResponse<String> response = Unirest.post("https://text-analysis12.p.rapidapi.com/sentiment-analysis/api/v1.1")
                                        .header("content-type", "application/json")
                                        .header("X-RapidAPI-Key", "a34f684eccmsh9cde57f933b4198p10570bjsn30410847e2a1")
                                        .header("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
                                        .body(quary)
                                        .asString();
                                String sentiment = response.getBody().split(":")[response.getBody().split(":").length -1 ].split("}")[0];
                                String new_sentiment = sentiment.substring(1,sentiment.length() -1);
                                System.out.println(new_sentiment);
                                if (new_sentiment.equals( "neutral")){
                                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                                    loader.setLocation(getClass().getResource("neutre.fxml"));
                                     loader.load();
                                    Parent parent = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(parent));
                                    stage.show();
                                }
                                if (new_sentiment.equals( "positive")){
                                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                                    loader.setLocation(getClass().getResource("positive.fxml"));
                                     loader.load();
                                    Parent parent = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(parent));
                                    stage.show();
                                }
                                if (new_sentiment.equals( "negative")){
                                    javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                                    loader.setLocation(getClass().getResource("negative.fxml"));
                                     loader.load();
                                    Parent parent = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(parent));
                                    stage.show();
                                   
                                }
                              
                            } catch (UnirestException ex) {
                                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                           

                        });
                    
                        ResumeButton.setOnAction((ActionEvent event) -> {
                            Blog U = tftable.getSelectionModel().getSelectedItem();
                            String contenu = U.getContenu();
                            try {
                                HttpResponse<String> response = Unirest.post("https://text-analysis12.p.rapidapi.com/summarize-text/api/v1.1")
                                        .header("content-type", "application/json")
                                        .header("X-RapidAPI-Key", "a34f684eccmsh9cde57f933b4198p10570bjsn30410847e2a1")
                                        .header("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
                                        .body("{\r\"language\": \"english\",\r\"summary_percent\": 30,\r\"text\": \""+contenu+"\"\r}")
                                        .asString();
                                String new_response = response.getBody().split(":")[response.getBody().split(":").length-2];
                                new_response = new_response.substring(1, new_response.length()-13);
                                System.out.println(new_response);
                                
                                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                                loader.setLocation(getClass().getResource("Resume.fxml"));
                                loader.load();
                                ResumeController MC = loader.getController();                     
                                MC.affiche(new_response);
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.show();
                        
                            } catch (UnirestException ex) {
                                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherBlogController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           

                        });

                        HBox managebtn = new HBox(likeButton, detectButton, ResumeButton);
                       

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
/* 
                BlogCRUD crud=new BlogCRUD();
        Blog U = tftable.getSelectionModel().getSelectedItem();
            int like = U.getLikes();  
            U.setLikes(like+1);
            crud.modifierLike(U);
       */
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

 
    @FXML
   public void RefreshMatchShowListData() throws SQLException {
       BlogCRUD u = new BlogCRUD();

       ObservableList<Blog> BlogList = u.AfficherBlog();


       tftitle.setCellValueFactory(new PropertyValueFactory<Blog,String>("titre"));
     tfdescreption.setCellValueFactory(new PropertyValueFactory<Blog,String>("descreption"));
    tfcontenu.setCellValueFactory(new PropertyValueFactory<Blog,String>("contenu"));
    tfimage.setCellValueFactory(new PropertyValueFactory<Blog,String>("image"));
    tftid.setCellValueFactory(new PropertyValueFactory<Blog,String>("id"));
    tflike.setCellValueFactory(new PropertyValueFactory<Blog,String>("likes"));
           tfimage.setCellFactory(column ->{
            return new TableCell<Blog,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String imageName, boolean empty) {
                    super.updateItem(imageName, empty);
                    if (imageName == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            FileInputStream stream = new FileInputStream(imageName);
                            Image image = new Image(stream);
                            imageView.setImage(image);
                            imageView.setFitWidth(50); 
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        });
    

        tftable.setItems(BlogList);

    }

    @FXML
    private void gotoblog(ActionEvent event) {

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
                        
        Stage stage1 = (Stage) tftable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }


}
