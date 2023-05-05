/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Hebergement;
import edu.tunisport.entities.Transport;
import edu.tunisport.services.HebergementService;
import edu.tunisport.services.TransportService;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kadri younes
 */
public class FrontHController implements Initializable {

    @FXML
    private Circle PDF;
    @FXML
    private AnchorPane reservation_form;
    
    @FXML
    private ListView<Hebergement> plantransport;
        HebergementService as = new HebergementService();

    @FXML
    private TextField searchbar;
   private Stage stage;
    private Scene scene;
    private Parent root;
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
    private TextField tfetat;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
   @Override
public void initialize(URL url, ResourceBundle rb) {
    // Retrieve the list of Hebergement objects from the database
    List<Hebergement> transportList = as.afficher();

    // Create an ObservableList from the list of Hebergement objects
    ObservableList<Hebergement> observableTransportList = FXCollections.observableArrayList(transportList);

    // Set the ObservableList as the items of the TableView component
    plantransport.setItems(observableTransportList);

    // Add a listener to the search bar text property to filter the TableView results
    searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
        // Use the filter() method of the ObservableList to filter the results
        plantransport.setItems(observableTransportList.filtered(hebergement -> {
            String lowerCaseFilter = newValue.toLowerCase();
            return hebergement.getNom_heberg().toLowerCase().contains(lowerCaseFilter)
                    ||hebergement.getDeschebergement().toLowerCase().contains(lowerCaseFilter)
                    || hebergement.getC().toString().toLowerCase().contains(lowerCaseFilter)|| hebergement.getL().toString().toLowerCase().contains(lowerCaseFilter);
        }));
    });

    // Set the cell factory for the ListView
    plantransport.setCellFactory(listView -> {
        return new ListCell<Hebergement>() {
            private final ImageView imageView = new ImageView();
            private final Label nomHebergementLabel = new Label();
                        private final Label DescHebergementLabel = new Label();

            private final Label categoryHebergementLabel = new Label();
            private final Label LocalHebergementLabel = new Label();

            @Override
            protected void updateItem(Hebergement hebergement, boolean empty) {
                super.updateItem(hebergement, empty);
                if (empty || hebergement == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imagePath = hebergement.getNom_heberg();
                    if (imagePath == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Set the image
                        Image image = new Image("file:" + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(300);
                        imageView.setFitHeight(300);
DescHebergementLabel.setText(hebergement.getDeschebergement());
DescHebergementLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

categoryHebergementLabel.setText(hebergement.getC().toString());
categoryHebergementLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

LocalHebergementLabel.setText(hebergement.getL().toString());
LocalHebergementLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

                        // Set the labels
                        //nomHebergementLabel.setText(hebergement.getNom_heberg());
                    //    DescHebergementLabel.setText(hebergement.getDeschebergement());

                       // categoryHebergementLabel.setText(hebergement.getC().toString());
                      //  LocalHebergementLabel.setText(hebergement.getL().toString());

                        // Create a VBox to hold the labels
                        VBox vbox = new VBox(nomHebergementLabel, DescHebergementLabel,categoryHebergementLabel,LocalHebergementLabel);
                        vbox.setSpacing(5);

                        // Create an HBox to hold the image and VBox
                        HBox hbox = new HBox(imageView, vbox);
                        hbox.setSpacing(10);

                        // Set the HBox as the graphic
                        setGraphic(hbox);
                    }
                }
            }
        };
    });
}

  // plantransport.setItems(observableTransportList);       


    // Set the ObservableList as the items of the ListView component
public void Details5() throws SQLException{
    List<Hebergement> transportList = as.afficher();
        
            plantransport.setItems(FXCollections.observableList(transportList));
            plantransport.setCellFactory(param -> new ListCell<Hebergement>() {
                @Override
                protected void updateItem(Hebergement item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        
                        GridPane gridPane = new GridPane();
                        gridPane.setHgap(10);
                        gridPane.setVgap(5);
                        gridPane.setPadding(new Insets(5));
                        gridPane.setStyle("-fx-background-color: #f2f2f2;-fx-padding: 10px;-fx-border-color: #ccc;-fx-border-width: 1px;-fx-border-radius: 5px;");

                        
                        
                        ImageView equipeAImage = new ImageView();
                        ImageView equipeBImage = new ImageView();

                        try {
                            // Load the images from disk or URL using their names from the database
                            FileInputStream equipeAStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getNom_heberg());
                            Image equipeAImageFile = new Image(equipeAStream);
                            equipeAImage.setImage(equipeAImageFile);

                         
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }  
                        equipeAImage.setFitWidth(200); 
                        equipeAImage.setFitHeight(200);
                        equipeBImage.setFitWidth(200); 
                        equipeBImage.setFitHeight(200);
                        gridPane.add(equipeAImage, 1, 0);
                        gridPane.add(equipeBImage, 3, 0);

                        
               
                        
                        
                       
                        setGraphic(gridPane);
                    }

                }
                
            });
    }    



    @FXML
    private void searchTransport(ActionEvent event) {
    }

    @FXML
    private void ajouterReservation(ActionEvent event) {
    }

    @FXML
    private void reserverBillet(ActionEvent event) {
    }

 

    @FXML
    private void redirClient(MouseEvent event) {
          try{
                root = FXMLLoader.load(getClass().getResource("Client.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void OnBtnClicked(MouseEvent event) {
    }
    
      @FXML
    private void blogclick(MouseEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("front.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
  
    @FXML
    private void blogbouton(ActionEvent event) {
          javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("front.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void showEventF(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("eventf.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
        
        
    }

    @FXML
    private void showHebergF(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("frontH.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void showMatch(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("MatchUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void reservationbouton(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
        @FXML
    private void reclamationShow(ActionEvent event) {
              javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("ajouterRec.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) reservation_form.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    
}
