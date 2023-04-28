/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.gui;

import com.tunisport.api.JavaMail;
import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Hebergement;
import com.tunisport.entities.localisation;
import com.tunisport.services.Category_hebergementService;
import com.tunisport.services.HebergementService;
import com.tunisport.services.LocalisationService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.chart.PieChart;
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
public class TunisportController implements Initializable {
        File f;
  
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
    private AnchorPane tab_form;
    private Button tab;
    @FXML
    private Button btnimage;
       private FileChooser fileChooser; 
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button plantun_btn;
    @FXML
    private Button localisation_btn;
    @FXML
    private Button hebergement_btn;
    @FXML
    private AnchorPane planTun_form;
    @FXML
    private AnchorPane localisation_form;
    @FXML
    private AnchorPane hebergement_form;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nomhebergshow.setCellValueFactory(new PropertyValueFactory<>("nom_heberg"));
    categorieshow.setCellValueFactory(new PropertyValueFactory<>("c"));
        descshow.setCellValueFactory(new PropertyValueFactory<>("deschebergement"));
    lieuxshow.setCellValueFactory(new PropertyValueFactory<>("l"));

         fileChooser = new FileChooser();
          fileChooser.setTitle("choisir un image");
          fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images","*.jpg","*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichier","*.*"));
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
           nomhebergshow.setCellFactory(column ->{
            return new TableCell<Hebergement,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String imagePath, boolean empty) {
                    super.updateItem(imagePath, empty);
                    if (imagePath == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            File file = new File(imagePath);
                            Image image = new Image(file.toURI().toString());
                            imageView.setImage(image);
                            imageView.setFitWidth(50); // Set the desired width and height here
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        }
        );
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
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

// Map to store the hebergement count for each localisation
     Map<String, Integer> localisationCount = new HashMap<>();

// Loop over the hebergement list to count the number of hebergements for each localisation
for (Hebergement h : HList) {
    String localisationName = h.getL().getLieux();
    localisationCount.put(localisationName, localisationCount.getOrDefault(localisationName, 0) + 1);
}

// Loop over the localisation count map to add a new PieChart.Data object for each localisation
for (Map.Entry<String, Integer> entry : localisationCount.entrySet()) {
    pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
}

piechart.setData(pieChartData);
piechart.setTitle("Hebergement in all sities");
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
    private void insererimage(ActionEvent event) throws FileNotFoundException, IOException {
        
      FileChooser fc = new FileChooser();
   // fc.setTitle("Ajouter une Image");
   // fc.getExtensionFilters().addAll(
           // new FileChooser.ExtensionFilter("Images", ".png", ".jpg", "*.gif"));
    f = fc.showOpenDialog(null);
   // String DBPath = "C:\\xampp\\htdocs" + f.getName();
  //  String i = f.getName();
    if (f != null) {
        BufferedImage bufferedImage = ImageIO.read(f);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        String imagePath = "C://xampp/htdocs/img/" + f.getName();
        File destFile = new File(imagePath);
//        Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
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
private void Ajouterhebergement(ActionEvent event) throws IOException {
  
    if (nomheberg.getText().isEmpty()) {
        ctrlnom.setText("Required");
        System.out.println("champs est vide");
        return;
    }

  
    if (f == null) {
        return;
    }

   //String imagePath = "C://xampp/htdocs/img/" + f.getName();
  //  File destFile = new File(imagePath);
  //  Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
 LocalisationService localisationService = new LocalisationService();
    Category_hebergementService categoryHebergementService = new Category_hebergementService();
   localisation localisation = localisationService.read(Integer.parseInt(localis.getValue().substring(0,2).trim()));
   Category_hebergement categoryHebergement = categoryHebergementService.read(Integer.parseInt(listcateg.getValue().substring(0,2).trim()));
 
     String imagePath = f.getAbsolutePath();
       
      //  File destFile = new File(imagePath);
        //        Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                
                    HebergementService hebergementService = new HebergementService();
                Hebergement m1 = new Hebergement();

                m1.setImage(imagePath);
                m1.setNom_heberg(nomheberg.getText());
                m1.setDeschebergement(description.getText());
                m1.setC(categoryHebergement);
                m1.setL(localisation);
               
                
     //  Hebergement hebergement = new Hebergement(nomheberg.getText(), description.getText(), imageDBPath, localisation, categoryHebergement);
    String rep = "hebergement ajouter avec succées";
    try
        {
                hebergementService.ajouter(m1);

            JavaMail.sendMail("youneskadri28@gmail.com",rep);
         
        } catch (Exception ex)
        {
            System.out.println(ex);
        }

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

   

    @FXML
    private void switchForm(ActionEvent event) {

        if (event.getSource() == plantun_btn) {
            planTun_form.setVisible(true);
            localisation_form.setVisible(false);
            hebergement_form.setVisible(false);
           

            plantun_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            localisation_btn.setStyle("-fx-background-color:transparent");
           hebergement_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == localisation_btn) {
            planTun_form.setVisible(false);
            localisation_form.setVisible(true);
            hebergement_form.setVisible(false);
           

            localisation_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            plantun_btn.setStyle("-fx-background-color:transparent");
            hebergement_btn.setStyle("-fx-background-color:transparent");
           

        }else if (event.getSource() == hebergement_btn) {
            planTun_form.setVisible(false);
            localisation_form.setVisible(false);
            hebergement_form.setVisible(true);
           

            hebergement_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            plantun_btn.setStyle("-fx-background-color:transparent");
            localisation_btn.setStyle("-fx-background-color:transparent");
            
           

        }
        
    }

    
    
    
    
    

    
}

