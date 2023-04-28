/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.gui;

import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Category_transport;
import com.tunisport.entities.Transport;
import com.tunisport.entities.localisation;
import com.tunisport.services.Category_hebergementService;
import com.tunisport.services.Category_transportService;
import com.tunisport.services.LocalisationService;
import com.tunisport.services.TransportService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author kadri younes
 */
public class TransportController implements Initializable {
      File f;

    @FXML
    private TextField categorytransport;
    private TextField imagetrans;
    @FXML
    private TextField nomtransport;
    @FXML
    private ComboBox<String> combotrans;
    @FXML
    
    private TableView<Category_transport> trans;
            Category_transportService ls = new Category_transportService();

            ObservableList<Category_transport> trList = FXCollections.observableArrayList(ls.afficher());

    @FXML
    private TableColumn<Category_transport,String> categtr_show;
    @FXML
    private TableColumn<Category_transport,String> delete;
    private Stage stage;
    private Scene scene;
    private Parent root;
            

    @FXML
    private TableColumn<Transport, String> planshow;
    @FXML
    private TableColumn<Transport, String> circuitshow;
    @FXML
    private TableColumn<Transport, String> categorueshow;
    @FXML
    private TableColumn<Transport, String> DELETE;
    @FXML
    private TableView<Transport> plantransport;
    TransportService as = new TransportService();

             ObservableList<Transport> test = FXCollections.observableArrayList(as.afficher());
    @FXML
    private Label ctrltr;
    @FXML
    private Label ctrlplan;
    @FXML
    private Label ctrlnom;
    @FXML
    private Label lb;
    @FXML
    private Button btnimage;
       private FileChooser fileChooser; 
    @FXML
    private TextField searchbar;
    @FXML
    private Button plan_btn;
    @FXML
    private Button category_btn;
    @FXML
    private AnchorPane plan_form;
    @FXML
    private AnchorPane category_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        circuitshow.setCellValueFactory(new PropertyValueFactory<>("nom_transport"));
                categorueshow.setCellValueFactory(new PropertyValueFactory<>("categ_transport"));

                // Associer les données à la table
       circuitshow.setCellValueFactory(new PropertyValueFactory<>("nom_transport"));
                categorueshow.setCellValueFactory(new PropertyValueFactory<>("categ_transport"));

                // Associer les données à la table
                plantransport.setItems(test);
                 searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
        // utiliser la méthode filter() de l'objet categories pour filtrer les résultats
        plantransport.setItems(test.filtered(Transport -> {
            String lowerCaseFilter = newValue.toLowerCase();
            return Transport.getNom_transport().toLowerCase().contains(lowerCaseFilter)
                 ||    Transport.getC().toString().toLowerCase().contains(lowerCaseFilter);
        }));
    });
         // Set up table columns
    circuitshow.setCellValueFactory(new PropertyValueFactory<>("nom_transport"));
    categorueshow.setCellValueFactory(new PropertyValueFactory<>("categ_transport"));

          fileChooser = new FileChooser();
          fileChooser.setTitle("choisir un image");
          fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images","*.jpg","*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichier","*.*"));
                
     
        List<Category_transport> localisations = ls.afficher();
        String[] emplacement = new String[localisations.size()];
        for (int i = 0; i < localisations.size(); i++) {
            emplacement[i] = localisations.get(i).toString();
        }
        combotrans.getItems().addAll(emplacement);
        categtr_show.setCellValueFactory(new PropertyValueFactory<Category_transport,String>("typetransport"));
          Callback<TableColumn<Category_transport,String>,TableCell<Category_transport,String>> localcellFactory=(TableColumn<Category_transport, String> param)->{
            final TableCell<Category_transport,String> cell=new TableCell<Category_transport,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                else{
                        final Button editButton=new Button("Delete");
                        editButton.setStyle("-fx-background-color: red;");
                        editButton.setOnAction(event ->{
                            Category_transport s=getTableView().getItems().get(getIndex());
                            ls.supprimer(s);
                            /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You have clicked \n"+s.getMessage());
                            alert.show();*/
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
          planshow.setCellFactory(column ->{
            return new TableCell<Transport,String>(){
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
        }
        );
           planshow.setCellValueFactory(new PropertyValueFactory<Transport,String>("image_transport"));
           circuitshow.setCellValueFactory(new PropertyValueFactory<Transport,String>("nom_transport"));
           categorueshow.setCellValueFactory(new PropertyValueFactory<Transport,String>("c"));

                      
          Callback<TableColumn<Transport,String>,TableCell<Transport,String>> cellFactory=(TableColumn<Transport, String> param)->{
            final TableCell<Transport,String> cell=new TableCell<Transport,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                else{
                        final Button editButton=new Button("Delete");
                        editButton.setStyle("-fx-background-color: red;");
                        editButton.setOnAction(event ->{
                            Transport s=getTableView().getItems().get(getIndex());
                            as.supprimer(s);
                            /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("You have clicked \n"+s.getMessage());
                            alert.show();*/
                        });
                        setGraphic(editButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
          DELETE.setCellFactory(cellFactory);
        plantransport.setItems(test);
           delete.setCellFactory(localcellFactory);
         trans.setItems(trList);
    }    

    @FXML
    private void ajout_transport(ActionEvent event) {
                           Category_transportService ts = new Category_transportService();
                         Category_transport t = new Category_transport(categorytransport.getText());
                     if(categorytransport.getText().isEmpty()){
            ctrltr.setText("Required");
             System.out.println("champs est vide");
             return;
        }
                         ts.ajouter(t);
 try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transport.fxml"));
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
    private void insererimage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
  //  fc.setTitle("Ajouter une Image");
   // fc.getExtensionFilters().addAll(
      //      new FileChooser.ExtensionFilter("Images", ".png", ".jpg", "*.gif"));
    f = fc.showOpenDialog(null);
  //  String DBPath = "C:\\xampp\\htdocs" + f.getName();
   // String i = f.getName();
    if (f != null) {
        
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        String imagePath = "C://xampp/htdocs/img/" + f.getName();
        File destFile = new File(imagePath);
     Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        FileInputStream fin = new FileInputStream(f);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = fin.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }
        byte[] post_image = bos.toByteArray();
        
    }

    }
    @FXML
   private void ajout_tr(ActionEvent event) throws IOException {
    if (nomtransport.getText().isEmpty()) {
        ctrlnom.setText("Required");
        System.out.println("champs est vide");
        return;
    }

    // Get the selected category transport
    Category_transportService cts = new Category_transportService();
    Category_transport ct = cts.read(Character.getNumericValue(combotrans.getValue().charAt(0)));
// Get the image file path from the file chooser dialog
  //  FileChooser fc = new FileChooser();
    //fc.setTitle("Ajouter une Image");
   // fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".png", ".jpg", "*.gif"));
   // File f = fc.showOpenDialog(null);
    if (f == null) {
        System.out.println("Aucun fichier sélectionné.");
        return;
    }
    String imagePath = f.getAbsolutePath();

    // Create a new Transport object with the input values
    TransportService ts = new TransportService();
    Transport t = new Transport();
    t.setNom_transport(nomtransport.getText());
    t.setC(ct);
    t.setImage_transport(imagePath);

    // Add the Transport object to the database
    ts.ajouter(t);

    // Navigate back to the transport view
  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transport.fxml"));
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
    private void update_tr(ActionEvent event) {
           // Get the selected category object from the table view
    Category_transport selectedCategory = trans.getSelectionModel().getSelectedItem();
   
    if (selectedCategory == null) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select a category to update.");
        alert.showAndWait();
        return;
    }
   
    // Get the updated values from the text fields
    String newCategoryName = categorytransport.getText();
    if (newCategoryName.isEmpty()) {
        // Empty field, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a new value for 'Nom Category'.");
        alert.showAndWait();
        return;
    }

    // Update the selected category object with the new values
    selectedCategory.setTypetransport(newCategoryName);

    // Update the category object in the database
    Category_transportService categoryService = new Category_transportService();
    categoryService.update(selectedCategory);

    // Set the value of the 'nom_category' field of the selected category in the text field
    categorytransport.setText(selectedCategory.getTypetransport());

    // Update the table view with the modified category object
    trans.refresh();

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Category updated successfully.");
    alert.showAndWait();
    }

    @FXML
    private void redirhebergement(MouseEvent event) {
         try{
                root = FXMLLoader.load(getClass().getResource("tunisport.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void update(ActionEvent event) {
            // Get the selected category object from the table view
    Transport selectedCategory = plantransport.getSelectionModel().getSelectedItem();
   
    if (selectedCategory == null) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select a home to update.");
        alert.showAndWait();
        return;
    }
   
    // Get the updated values from the text fields
    String newCategoryName = nomtransport.getText();
    String newDescription = imagetrans.getText();
    if (newCategoryName.isEmpty() || newDescription.isEmpty() ) {
        // Empty field, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a new value for 'Nom Category'.");
        alert.showAndWait();
        return;
    }

    // Update the selected category object with the new values
    selectedCategory.setNom_transport(newCategoryName);
    selectedCategory.setImage_transport(newDescription);

    // Update the category object in the database
    TransportService categoryService = new TransportService();
    categoryService.update(selectedCategory);

    // Set the value of the 'nom_category' field of the selected category in the text field
    nomtransport.setText(selectedCategory.getNom_transport());
    imagetrans.setText(selectedCategory.getImage_transport());

    // Update the table view with the modified category object
    plantransport.refresh();

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("hebergement updated successfully.");
    alert.showAndWait();
}

    @FXML
    private void searchTransport(ActionEvent event) {
  
    String searchString = searchbar.getText();
    ObservableList<Transport> transportList = FXCollections.observableArrayList(as.chercherServ(searchString));
    plantransport.setItems(transportList);
}

    @FXML
    private void switchForm(ActionEvent event) {

        if (event.getSource() == plan_btn) {
            plan_form.setVisible(true);
            category_form.setVisible(false);
           

            plan_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            category_btn.setStyle("-fx-background-color:transparent");
           

        } else if (event.getSource() == category_btn) {
            plan_form.setVisible(false);
            category_form.setVisible(true);
           

            category_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            plan_btn.setStyle("-fx-background-color:transparent");
           

        }
    }

    }

    
    
    
 
    

