/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.gui;

import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Hebergement;
import com.tunisport.entities.localisation;
import com.tunisport.services.Category_hebergementService;
import com.tunisport.services.HebergementService;
import com.tunisport.services.LocalisationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class TunisportController implements Initializable {

    @FXML
    private TextField categheberg;
    @FXML
    private TextField local;
    @FXML
    private TextField nomheberg;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<String> listcateg;
    @FXML
    private ComboBox<String> localis;
    @FXML
    private TextField image;

    @FXML
    private TableColumn<Category_hebergement, String> categshow;
            Category_hebergementService ps = new Category_hebergementService();
    @FXML
    private TableColumn<localisation, String> localshow;
                LocalisationService ls = new LocalisationService();

    @FXML
    private TableView<localisation> loc;
    ObservableList<localisation> locList = FXCollections.observableArrayList(ls.afficher());
    @FXML
    private TableColumn<localisation, String> delete;
    @FXML
    private TableView<Category_hebergement> categ;
        ObservableList<Category_hebergement> categList = FXCollections.observableArrayList(ps.afficher());
    @FXML
    private TableColumn<Category_hebergement, String> DELETE;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TableView<Hebergement> plan;
        HebergementService Hs = new HebergementService();

        ObservableList<Hebergement> HList = FXCollections.observableArrayList(Hs.afficher());

    @FXML
    private TableColumn<Hebergement,String> imageshow;
    @FXML
    private TableColumn<Hebergement,String> nomhebergshow;
    @FXML
    private TableColumn<Hebergement,String > categorieshow;
    @FXML
    private TableColumn<Hebergement, String> descshow;
    @FXML
    private TableColumn<Hebergement, String> lieuxshow;
    @FXML
    private TableColumn<Hebergement, String> deleter;
    @FXML
    private Label ctrllocal;
    @FXML
    private Label ctrlcateg;
    @FXML
    private Label ctrlimage;
    @FXML
    private Label ctrlnom;
    @FXML
    private Label ctrldrsc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Category_hebergement> Category_hebergements = ps.afficher();
        String[] emplacementChoix = new String[Category_hebergements.size()];
        for (int i = 0; i < Category_hebergements.size(); i++) {
            emplacementChoix[i] = Category_hebergements.get(i).toString();
        }
        listcateg.getItems().addAll(emplacementChoix);
        List<localisation> localisations = ls.afficher();
        String[] emplacement = new String[localisations.size()];
        for (int i = 0; i < localisations.size(); i++) {
            emplacement[i] = localisations.get(i).toString();
        }
        localis.getItems().addAll(emplacement);
         localshow.setCellValueFactory(new PropertyValueFactory<localisation,String>("lieux"));
          Callback<TableColumn<localisation,String>,TableCell<localisation,String>> localcellFactory=(TableColumn<localisation, String> param)->{
            final TableCell<localisation,String> cell=new TableCell<localisation,String>(){
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
                            localisation s=getTableView().getItems().get(getIndex());
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
           categshow.setCellValueFactory(new PropertyValueFactory<Category_hebergement,String>("nomcategory"));
           
          Callback<TableColumn<Category_hebergement,String>,TableCell<Category_hebergement,String>> cellFactory=(TableColumn<Category_hebergement, String> param)->{
            final TableCell<Category_hebergement,String> cell=new TableCell<Category_hebergement,String>(){
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
                            Category_hebergement c=getTableView().getItems().get(getIndex());
                            ps.supprimer(c);
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
            imageshow.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("image"));
            nomhebergshow.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("nom_heberg"));
            categorieshow.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("c"));
            descshow.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("deschebergement"));
            lieuxshow.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("l"));

            Callback<TableColumn<Hebergement,String>,TableCell<Hebergement,String>> hebercellFactory=(TableColumn<Hebergement, String> param)->{
            final TableCell<Hebergement,String> cell=new TableCell<Hebergement,String>(){
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
                            Hebergement c=getTableView().getItems().get(getIndex());
                            Hs.supprimer(c);
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
         categ.setItems(categList);
        delete.setCellFactory(localcellFactory);
         loc.setItems(locList);
          deleter.setCellFactory(hebercellFactory);
         plan.setItems(HList);
         
         
    }    

    @FXML
    private void Ajoutcategory(ActionEvent event) {
            String categoryName = categheberg.getText();

         if(categoryName.isEmpty()){
            ctrlcateg.setText("Required");
             System.out.println("champs est vide");
             return;
        }
         Category_hebergement p = new Category_hebergement(categoryName);
        Category_hebergementService ps = new Category_hebergementService();
        ps.ajouter(p);
        
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tunisport.fxml"));
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
    private void AjouterLocalisation(ActionEvent event) {
        
         localisation l = new localisation(local.getText());
        LocalisationService ls = new LocalisationService();
        if(local.getText().isEmpty()){
            ctrllocal.setText("Required");
             System.out.println("champs est vide");
             return;
        }
        ls.ajouter(l);
     
        
 try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tunisport.fxml"));
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
    private void Ajouterhebergement(ActionEvent event) {
        if(image.getText().isEmpty()){
            ctrlimage.setText("Required");
             System.out.println("champs est vide");
             return;
        }
         if(nomheberg.getText().isEmpty()){
            ctrlnom.setText("Required");
             System.out.println("champs est vide");
             return;
        }
                 localisation l = new localisation(local.getText());
        LocalisationService ls = new LocalisationService();
        
           Category_hebergement p = new Category_hebergement(categheberg.getText());
        Category_hebergementService ps = new Category_hebergementService();
        System.out.println(ls.read(localis.getValue().charAt(0)));
        System.out.println(ps.read(listcateg.getValue().charAt(0)));
      Hebergement H = new Hebergement(nomheberg.getText(),description.getText(),image.getText(),ls.read(Character.getNumericValue(localis.getValue().charAt(0))),ps.read(Character.getNumericValue(listcateg.getValue().charAt(0))));
        HebergementService Hs = new HebergementService();
        
        Hs.ajouter(H);
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tunisport.fxml"));
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
private void update_localisation(ActionEvent event) {
    // Get the selected localisation object from the table view
    localisation selectedLocalisation = loc.getSelectionModel().getSelectedItem();
    if (selectedLocalisation == null) {
        // No localisation is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select a localisation to update.");
        alert.showAndWait();
        return;
    }
   

    // Get the updated values from the text fields
    String newLieux = local.getText();
    if (newLieux.isEmpty()) {
        // Empty field, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a new value for 'Lieux'.");
        alert.showAndWait();
        return;
    }

    // Update the selected localisation object with the new values
    selectedLocalisation.setLieux(newLieux);

    // Update the localisation object in the database
    LocalisationService localisationService = new LocalisationService();
    localisationService.update(selectedLocalisation);
 // Set the value of the 'lieux' field of the selected localisation in the text field
    local.setText(selectedLocalisation.getLieux());
    // Update the table view with the modified localisation object
    loc.refresh();

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Localisation updated successfully.");
    alert.showAndWait();
}

    

    @FXML
    private void delete_localisation(ActionEvent event) {
    }

  @FXML
private void update_categ_heberg(ActionEvent event) {
    // Get the selected category object from the table view
    Category_hebergement selectedCategory = categ.getSelectionModel().getSelectedItem();
   
    if (selectedCategory == null) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select a category to update.");
        alert.showAndWait();
        return;
    }
   
    // Get the updated values from the text fields
    String newCategoryName = categheberg.getText();
    if (newCategoryName.isEmpty()) {
        // Empty field, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a new value for 'Nom Category'.");
        alert.showAndWait();
        return;
    }

    // Update the selected category object with the new values
    selectedCategory.setNomcategory(newCategoryName);

    // Update the category object in the database
    Category_hebergementService categoryService = new Category_hebergementService();
    categoryService.update(selectedCategory);

    // Set the value of the 'nom_category' field of the selected category in the text field
    categheberg.setText(selectedCategory.getNomcategory());

    // Update the table view with the modified category object
    categ.refresh();

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Category updated successfully.");
    alert.showAndWait();
}



    @FXML
    private void delete_categ_heberg(ActionEvent event) {
    }

    @FXML
    private void redirTransport(MouseEvent event) {
            try{
                root = FXMLLoader.load(getClass().getResource("transport.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void update_hebergement(ActionEvent event) {
       // Get the selected category object from the table view
    Hebergement selectedCategory = plan.getSelectionModel().getSelectedItem();
   
    if (selectedCategory == null) {
        // No category is selected, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please select a home to update.");
        alert.showAndWait();
        return;
    }
   
    // Get the updated values from the text fields
    String newCategoryName = nomheberg.getText();
    String newDescription = description.getText();
    if (newCategoryName.isEmpty() || newDescription.isEmpty() ) {
        // Empty field, show an error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please enter a new value for 'Nom Category'.");
        alert.showAndWait();
        return;
    }

    // Update the selected category object with the new values
    selectedCategory.setNom_heberg(newCategoryName);
    selectedCategory.setDeschebergement(newDescription);

    // Update the category object in the database
    HebergementService categoryService = new HebergementService();
    categoryService.update(selectedCategory);

    // Set the value of the 'nom_category' field of the selected category in the text field
    nomheberg.setText(selectedCategory.getNom_heberg());
    description.setText(selectedCategory.getDeschebergement());

    // Update the table view with the modified category object
    plan.refresh();

    // Show a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("hebergement updated successfully.");
    alert.showAndWait();
}

    
}
