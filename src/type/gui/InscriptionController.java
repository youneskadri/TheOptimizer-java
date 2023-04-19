/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type.gui;

import event.entities.type;
import event.services.typecrud;
import event.utils.MyConnection;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class InscriptionController implements Initializable {

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
    @FXML
    private TextField tfVideo;
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

    @FXML
    private void saveType(ActionEvent event) throws SQLException {
        
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
FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
try {
    Parent root = loader.load();
    DetailsWindowController dwc = loader.getController();

    dwc.setTextNom(t.getNom_type());

    tfNom.getScene().setRoot(root);

} catch (IOException ex) {
    System.out.println("Error: "+ex.getMessage());
}
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
    String searchTerm = xxxx.getText().trim();
// Vérifier si la chaîne de recherche n'est pas vide
if (!searchTerm.isEmpty()) {
// Créer une nouvelle liste qui contiendra les éléments correspondant à la recherche
ObservableList<Type> filteredList = FXCollections.observableArrayList();
 // Parcourir chaque élément de la liste existante
    for (type type : TypeList) {
        // Vérifier si le nom du type contient la chaîne de recherche
        if (type.getNom_type().toLowerCase().contains(searchTerm.toLowerCase())) {
            // Ajouter l'élément à la nouvelle liste s'il correspond à la recherche
            TypeList.add(type);
        }
    }

    // Afficher la nouvelle liste dans la table
    table_type.setItems(TypeList);
} else {
    // Si la chaîne de recherche est vide, afficher la liste complète dans la table
    table_type.setItems(TypeList);
}
    }
    
    }

