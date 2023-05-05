/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
*/
package edu.tunisport.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.tunisport.entities.event;
import edu.tunisport.entities.type;
import edu.tunisport.services.typecrud;
import edu.tunisport.tools.MyConnection;
import java.io.IOException;

import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.google.zxing.*;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import edu.tunisport.tools.session;
import java.io.File;
import java.util.Hashtable;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class InscriptionTypeController implements Initializable {

    @FXML
    private Button btnValider;
    private TextField tfID;
    @FXML
    private TextField tfNom;
    @FXML
    private TableView<type> table_type;
    @FXML
    private TableColumn<type, String> col_id;
    @FXML
    private TableColumn<?, String> col_type;
    @FXML
    private Button supprimertype;
    @FXML
    private TextField texteid;
   
private MyConnection con;
    @FXML
    private TextField xxxx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TypeShowListData();
        } catch (SQLException ex) {
           
        }
    }  
 
    public void ajoutertype2(type t){
        try {
            String requete2 = "INSERT INTO type_event (nom_type)"
                    + "VALUES (?)";
            PreparedStatement pst = new MyConnection() .getCnx() .prepareStatement(requete2);
            pst.setString(1, t.getNom_type());
            pst.executeUpdate();
            System.out.println("Votre type est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
@FXML
private void saveType(ActionEvent event) throws SQLException, WriterException, IOException {

   String nom = tfNom.getText();
   if (nom.length() < 5) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText(null);
       alert.setContentText("Le champ du nom doit avoir au moins 5 caractères");
       alert.showAndWait();
       return;
       // afficher un message d'erreur ou une alerte pour indiquer que le champ doit avoir au moins 5 caractères

       // sortir de la méthode pour éviter d'exécuter le reste du code
   }
   if (!nom.matches("[a-zA-Z]+")) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText(null);
       alert.setContentText("Le champ du nom doit contenir uniquement des lettres");
       alert.showAndWait();
       return;
       // afficher un message d'erreur ou une alerte pour indiquer que le champ doit contenir uniquement des lettres

       // sortir de la méthode pour éviter d'exécuter le reste du code
   }
   type t = new type(nom);
   typecrud pc = new typecrud();
  pc.ajoutertype(t);
   RefreshTypeShowListData();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
   alert.setTitle("Succès");
   alert.setHeaderText(null);
   alert.setContentText("Le type a été ajouté avec succès.");
   alert.showAndWait();

   // generate the QR code for the newly created type
   String content = "Type: " + nom;
   int width = 300;
   int height = 300;
   String format = "png";

   // set QR code encoding parameters
   Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
   hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

   // create the QR code matrix
   BitMatrix matrix = new MultiFormatWriter().encode(new String(content.getBytes("UTF-8"), "ISO-8859-1"),
       BarcodeFormat.QR_CODE, width, height, hintMap);

   // write the QR code to a file or display it in a GUI component


   // navigate to the details window

  
}
    
    public ObservableList<type> TypeListData() throws SQLException {

        return new typecrud().affichertypes();
    }
    
    private ObservableList<type> TypeList;
    
    public void TypeShowListData() throws SQLException {
        
        TypeList = TypeListData();

      
        col_type.setCellValueFactory(new PropertyValueFactory<>("nom_type"));
        
        

        table_type.setItems(TypeList);
 

    }
    
    public void RefreshTypeShowListData() throws SQLException {
        TypeList.clear();
        
        TypeList = TypeListData();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("nom_type"));
        
        

        table_type.setItems(TypeList);

    }

    @FXML
    private void supprimertype(ActionEvent event) throws SQLException {
  
        // Récupérer l'utilisateur sélectionné
    type u = table_type.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        typecrud us = new typecrud();
        us.supprimer(u.getId());
        table_type.refresh();
        RefreshTypeShowListData();
    } 
    else {
        System.out.println("Aucun utilisateur sélectionné");
    }
   
    }
    
 @FXML
    public void selectItem() {
    type u = table_type.getSelectionModel().getSelectedItem();
    int num = table_type.getSelectionModel().getSelectedIndex();
        System.out.println(u);
    if ((num - 1)<-6) {
        return;
    }
    texteid.setText(String.valueOf(u.getId()));
    tfNom.setText(u.getNom_type());
        
        
}

    @FXML
    private void retour1(ActionEvent event) {
        try {
    FXMLLoader loader = new FXMLLoader( 
        getClass().getResource("DetailsWindow.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
} catch (IOException ex) {
    System.out.println(ex.getMessage());
}
    }

    @FXML
    private void modifiers(ActionEvent event) throws SQLException {

        


        

      String req = "UPDATE type_event SET nom_type = '"
                +tfNom.getText()+"" + "' WHERE id ='"
                + texteid.getText() + "'";
                
       
            
         try {
            Alert alert;
            if (tfNom.getText().isEmpty()
              
                    
                    ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE type: " + texteid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                     Statement st = new MyConnection() .getCnx() .prepareStatement(req);
                    st.executeUpdate(req);
 
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    RefreshTypeShowListData();
                    
                }

            }
         }catch (Exception e) {
            e.printStackTrace();
        }
            
}

    @FXML
    private void btntrier(ActionEvent event) {
          // Trier la liste des types par ordre alphabétique croissant
    TypeList.sort((type1, type2) -> type1.getNom_type().compareTo(type2.getNom_type()));
    
    // Rafraîchir la table pour afficher la liste triée
    table_type.refresh();
    }

    @FXML
    private void rechercher(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
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
      @FXML
    private void Dashboardclick(MouseEvent event) {
        
                   javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("Dashboard.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfNom.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    }
    
    

