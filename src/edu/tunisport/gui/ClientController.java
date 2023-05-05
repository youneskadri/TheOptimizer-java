/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import edu.tunisport.entities.Transport;
import edu.tunisport.services.TransportService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kadri younes
 */
public class ClientController implements Initializable {

    @FXML
    private AnchorPane reservation_form;
    @FXML
    private ListView<Transport> plantransport;
    TransportService as = new TransportService();

            // ObservableList<Transport> test = FXCollections.observableArrayList(as.afficher());
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private DatePicker dpdate;
     @FXML
     private Circle PDF;
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
    @FXML
    private TextField searchbar;
   private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
 @Override
public void initialize(URL url, ResourceBundle rb) {
    // Retrieve the list of Transport objects from the database
    List<Transport> transportList = as.afficher();

    // Create an ObservableList from the list of Transport objects
    ObservableList<Transport> observableTransportList = FXCollections.observableArrayList(transportList);

    // Set the ObservableList as the items of the TableView component
    plantransport.setItems(observableTransportList);

    // Add a listener to the search bar text property to filter the TableView results
    searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
        // Use the filter() method of the ObservableList to filter the results
        plantransport.setItems(observableTransportList.filtered(transport -> {
            String lowerCaseFilter = newValue.toLowerCase();
            return transport.getNom_transport().toLowerCase().contains(lowerCaseFilter)
                    || transport.getC().toString().toLowerCase().contains(lowerCaseFilter);
        }));
    });

    // Set the cell factory for the ListView
    plantransport.setCellFactory(listView -> {
        return new ListCell<Transport>() {
            private final ImageView imageView = new ImageView();
            private final Label nomTransportLabel = new Label();
            private final Label categoryTransportLabel = new Label();

            @Override
            protected void updateItem(Transport transport, boolean empty) {
                super.updateItem(transport, empty);
                if (empty || transport == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String imagePath = transport.getImage_transport();
                    if (imagePath == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Set the image
                        Image image = new Image("file:" + imagePath);
                        imageView.setImage(image);
                        imageView.setFitWidth(200);
                        imageView.setFitHeight(200);

                        // Set the labels
                        nomTransportLabel.setText(transport.getNom_transport());
                        nomTransportLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px;");

                        categoryTransportLabel.setText(transport.getC().toString());
categoryTransportLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

                        // Create a VBox to hold the labels
                        VBox vbox = new VBox(nomTransportLabel, categoryTransportLabel);
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
public void Details4() throws SQLException{
    List<Transport> transportList = as.afficher();
        
            plantransport.setItems(FXCollections.observableList(transportList));
            plantransport.setCellFactory(param -> new ListCell<Transport>() {
                @Override
                protected void updateItem(Transport item, boolean empty) {
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
                            FileInputStream equipeAStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getImage_transport());
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
    private void ajouterReservation(ActionEvent event) {
    }

    @FXML
    private void reserverBillet(ActionEvent event) {
    }

    @FXML
    private void searchTransport(ActionEvent event) {
    }

    private void redirhebergement(MouseEvent event) {
        try{
                root = FXMLLoader.load(getClass().getResource("Details.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public void printonpdf() throws FileNotFoundException, DocumentException {
     File directory = new File("C:/Gen PDF");
if (!directory.exists()){
    directory.mkdirs();
}
    try {
            List<Transport> transportList = as.afficher();

        String fileName = "C:/Gen PDF/LIST.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        for (Transport transport : transportList) {
            // Write the transport information to the PDF document
            document.add(new Paragraph("Nom Transport: " + transport.getNom_transport()));
            document.add(new Paragraph("Categorie: " + transport.getC()));
                     document.add(new Paragraph("Image Path: " + transport.getImage_transport()));
            document.add(new Paragraph("------------------------------------------------------------------------------"));
        }
        document.close();
        JOptionPane.showMessageDialog(null, "PDF LIST CREATED");
    } catch (DocumentException | FileNotFoundException e) {
        System.out.println(e);
    }
}

    @FXML
    private void OnBtnClicked(MouseEvent event) {
        try {
            printonpdf();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void redirfront(MouseEvent event) {
         try{
                root = FXMLLoader.load(getClass().getResource("front.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }

    @FXML
    private void redirMap(MouseEvent event) {
       javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader ();
                            loader.setLocation(getClass().getResource("GoogleMaps.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
               Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                        
        Stage stage1 = (Stage) tfetat.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    }
    

