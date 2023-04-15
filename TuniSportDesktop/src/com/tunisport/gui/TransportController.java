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
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author kadri younes
 */
public class TransportController implements Initializable {

    @FXML
    private TextField categorytransport;
    @FXML
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lb.setText("welcome");
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
    private void ajout_tr(ActionEvent event) {
        if(imagetrans.getText().isEmpty()){
            ctrlplan.setText("Required");
             System.out.println("champs est vide");
             return;
        }
  if(nomtransport.getText().isEmpty()){
            ctrlnom.setText("Required");
             System.out.println("champs est vide");
             return;
        }
          Category_transportService ts = new Category_transportService();
                         Category_transport t = new Category_transport(categorytransport.getText());
        TransportService as = new TransportService();
                                  Transport a = new Transport(imagetrans.getText(),nomtransport.getText(),ts.read(Character.getNumericValue(combotrans.getValue().charAt(0))));
 
                          as.ajouter(a);
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


    }
 
    

