package edu.tunisport.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.tunisport.entities.user;
import edu.tunisport.services.UserCrud;
import edu.tunisport.tools.MyConnection;
import edu.tunisport.tools.session;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashboardController extends Application {
 @FXML
    private TableView<user> userTable;
    @FXML
    private TableColumn<user, String> FirstNameCol;
    @FXML
    private TableColumn<user, String> SecondNameCol;
    @FXML
    private TableColumn<user, String> EmailCol;
    @FXML
    private TableColumn<user, String> PhoneCol;
    ObservableList<user>  userList;
    
    String query = null;
Connection connection =null;
PreparedStatement st =null;
ResultSet res = null;
user user =null;
    @FXML
    private Label time;


  



    @Override
    public void start(Stage primaryStage) {

        // Create a border pane as the root layout
        BorderPane root = new BorderPane();

        // Create an HBox for the top of the border pane
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(10, 10, 10, 10));
        topBox.setSpacing(10);

        // Create two buttons for the top HBox
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");

        // Add the buttons to the top HBox
        topBox.getChildren().addAll(button1, button2);

        // Set the top of the border pane to the HBox
        root.setTop(topBox);

        // Create a scene with the root layout
        Scene scene = new Scene(root, 400, 300);

        // Set the stage to the scene and show it
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add a listener to the scene's width property to adjust the top HBox spacing
        scene.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            topBox.setSpacing(newWidth.intValue() / 20);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        time();
        AffichUtilisateur();
     
         
    }  
    
     public void AffichUtilisateur(){
        UserCrud u = new UserCrud();

        ObservableList<user> userList = u.AfficherUser();
        
  
     FirstNameCol.setCellValueFactory(new PropertyValueFactory<user,String>("first_name"));
    SecondNameCol.setCellValueFactory(new PropertyValueFactory<user,String>("second_name"));
    EmailCol.setCellValueFactory(new PropertyValueFactory<user,String>("email"));
    PhoneCol.setCellValueFactory(new PropertyValueFactory<user,String>("phone"));
          userTable.setItems(userList);
        }

    



    

    
    @FXML
    private void supprimebtn(ActionEvent event) {

        UserCrud u=new UserCrud();
   //       commandeplat t = tvcommande.getSelectionModel().getSelectedItem();
        user user = (user) userTable.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        u.SupprimerUser(user);
         AffichUtilisateur();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur supprimé");
                alert.showAndWait();  

    }

    @FXML
    private void Updatebtn(ActionEvent event) {
        // selectionne l'utilisateur à modifier 
  user U = userTable.getSelectionModel().getSelectedItem();

// fenetre modification 
 javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Modif.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        // Appel controleur de modifier 
                        ModifController MC = loader.getController();
                        
                        MC.Modif(U);
                        
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
//

    }

    @FXML
    private void AddUser(ActionEvent event) {
    }
private void time (){
        SimpleDateFormat  sdf = new SimpleDateFormat("DD/MM/YYYY");
        String date =sdf.format(new Date());
        time.setText(date);
          
}

    @FXML
    private void refresh(MouseEvent event) {
        AffichUtilisateur();
    }
    private void logout(ActionEvent event) {
        
              

    }

    private void showBlogkrich(ActionEvent event) {
        
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    }

    private void showEvent(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    }

    private void showTypeEvent(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
        
    }

    private void showMatch(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    }

    private void showDetails(ActionEvent event) {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("detailsH.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    }

    private void showRec(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    }
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
@FXML
    private void reservationclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void blogclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void commentaireclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void typeclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void evenementclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reclamationclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reponseclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void hebergementclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
        
    }

    @FXML
    private void transportclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void matchclick(javafx.scene.input.MouseEvent event) {
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
                        
        Stage stage1 = (Stage) userTable.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void logout(MouseEvent event) {
        
           session US = session.getInstance(session.getId(),session.getFirst_name(),session.getSecond_name(),session.getEmail(),session.getPassword(),session.getPhone());
        US.cleanUserSession();
        
        // redirection vers accueil
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
        loader.setLocation(getClass().getResource("loginView.fxml"));
        try {
        loader.load();

        } catch (IOException ex) {
        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("failed to load");
        System.out.println(ex);
        }
       // LoginController LC = loader.getController();
       
        
        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));
        stage.show();
    }

   
}

