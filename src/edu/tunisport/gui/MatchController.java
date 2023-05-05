/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.MatchF;
import edu.tunisport.tools.MyConnection;
import Views.getData;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import edu.tunisport.services.MatchFCRUD;
import edu.tunisport.tools.session;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MatchController implements Initializable {

    @FXML
    private AnchorPane match_form;
    @FXML
    private AnchorPane add_match_form;
    @FXML
    private AnchorPane update_match_form;
    @FXML
    private TableView<MatchF> match_table;
    @FXML
    private TableColumn<MatchF, String> eqA_col;
    @FXML
    private TableColumn<MatchF, String> eqB_col;
    @FXML
    private TableColumn<MatchF, String> date_col;
    @FXML
    private TableColumn<MatchF, String> type_col;
    @FXML
    private TableColumn<MatchF, String> stade_col;
    @FXML
    private TableColumn<MatchF, String> tournoi_col;
    @FXML
    private TableColumn<MatchF, String> rA_col;
    @FXML
    private TableColumn<MatchF, String> rB_col;
    @FXML
    private TableColumn<MatchF, String> prix_col;
    @FXML
    private TableColumn<MatchF, String> image_col;
    @FXML
    private TableColumn<MatchF, String> image2_col;
    @FXML
    private TableColumn<MatchF, String> id_col;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    private Button btnmatchs;
    @FXML
    private ComboBox<String> cbeqA;
    @FXML
    private ComboBox<String> cbtype;
    @FXML
    private ComboBox<String> cbeqB;
    @FXML
    private ComboBox<String> cbtournoi;
    @FXML
    private ComboBox<String> cbstade;
    @FXML
    private DatePicker dpdate;
    
    @FXML
    private TextField tfrA;
    @FXML
    private TextField tfrB;
    @FXML
    private TextField tfprix;
    
    
    
    ObservableList<String> optionstype = FXCollections.observableArrayList();
    ObservableList<String> optionstype1 = FXCollections.observableArrayList();
    ObservableList<String> optionstype2 = FXCollections.observableArrayList();
    ObservableList<String> optionstype3 = FXCollections.observableArrayList();
    ObservableList<String> optionstype4 = FXCollections.observableArrayList();
    Map<String, Integer> maMap = new HashMap<String, Integer>();
    Map<String, Integer> maMap1 = new HashMap<String, Integer>();
    Map<String, Integer> maMap2 = new HashMap<String, Integer>();
    Map<String, Integer> maMap3 = new HashMap<String, Integer>();
    Map<String, Integer> maMap4 = new HashMap<String, Integer>();
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    
    @FXML
    private Label ldate;
    @FXML
    private Label lrA;
    @FXML
    private Label lrB;
    @FXML
    private Label lprix;
    @FXML
    private Label leqA;
    @FXML
    private Label leqB;
    @FXML
    private Label ltype;
    @FXML
    private Label lstade;
    @FXML
    private Label ltournoi;
    @FXML
    private Button btnupdate;
    @FXML
    private Label ldate1;
    @FXML
    private Label lrA1;
    @FXML
    private Label lrB1;
    @FXML
    private Label lprix1;
    @FXML
    private Label leqA1;
    @FXML
    private Label leqB1;
    @FXML
    private Label ltype1;
    @FXML
    private Label lstade1;
    @FXML
    private Label ltournoi1;
    @FXML
    private ComboBox<String> cbeqB1;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private ComboBox<String> cbtournoi1;
    @FXML
    private ComboBox<String> cbstade1;
    @FXML
    private TextField tfrA1;
    @FXML
    private TextField tfrB1;
    @FXML
    private TextField tfprix1;
    @FXML
    private ComboBox<String> cbtype1;
    @FXML
    private ComboBox<String> cbeqA1;
    @FXML
    private TextField tfid1;
    @FXML
    private Button btnrevenir;
    @FXML
    private Button btnreservation;
    @FXML
    private Button btnimporter;
    @FXML
    private Button btnimporter2;
    @FXML
    private Button btnimporter3;
    @FXML
    private Button btnimporter4;
    @FXML
    private Button btnrevenir1;
    @FXML
    private TableColumn<?, ?> nbbtotal_col;
    @FXML
    private TableColumn<?, ?> nbbreserve_col;
    @FXML
    private TextField match_search;
    File f;
    File f1;
    @FXML
    private TextField tfnbbreserve;
    @FXML
    private TextField tfnbbtotal;
    @FXML
    private TextField tfnbbtotal1;
    @FXML
    private TextField tfnbbreserve1;
    @FXML
    private TextField tfheureDeb;
    @FXML
    private TextField tfheureFin;
    @FXML
    private TextField tfheureDeb1;
    @FXML
    private TextField tfheureFin1;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnreservation.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/edu/tunisport/gui/reservation.fxml"));
               Scene scene=btnreservation.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               
           }
        });
        btnmatchs.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/edu/tunisport/gui/Match.fxml"));
               Scene scene=btnmatchs.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               
           }
        });
        try {
            MatchShowListData();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        fillcomboEquipeA();
        cbeqA.setItems(optionstype);
        cbeqA1.setItems(optionstype);
        fillcomboEquipeB();
        cbeqB.setItems(optionstype1);
        cbeqB1.setItems(optionstype1);
        fillcomboTypeMatch();
        cbtype.setItems(optionstype2);
        cbtype1.setItems(optionstype2);
        fillcomboStade();
        cbstade.setItems(optionstype3);
        cbstade1.setItems(optionstype3);
        fillcomboTournoi();
        cbtournoi.setItems(optionstype4);
        cbtournoi1.setItems(optionstype4);
    }    

    @FXML
    private void switchform(ActionEvent event) {
        if (event.getSource() == btnrevenir) {
            match_form.setVisible(true);
            add_match_form.setVisible(false);
            
        } else if (event.getSource() == btnadd) {
            match_form.setVisible(false);
            add_match_form.setVisible(true);
            
        } 
    }
    
    @FXML
    private void switchform2(ActionEvent event) {
        if (event.getSource() == btnrevenir1) {
            match_form.setVisible(true);
            update_match_form.setVisible(false);
            
        } else if (event.getSource() == btnupdate) {
            match_form.setVisible(false);
            update_match_form.setVisible(true);
            
        }
    }

    @FXML
    private void supprimerMatch(ActionEvent event) throws SQLException {
        MatchF m = match_table.getSelectionModel().getSelectedItem();
        Alert alert2;
        if (m != null) {
            alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Message de confirmation");
            alert2.setHeaderText(null);
            alert2.setContentText("Voulez vous supprimer ce match?");
            Optional<ButtonType> option = alert2.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                MatchFCRUD us = new MatchFCRUD();
                us.delete(m.getId());
                alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Information Message");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Match supprimé avec succès!");
                        alert2.showAndWait();
                MatchShowListData();
            }

        } else {
            alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Message d'erreur");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Aucun match selectionné!");
                        alert2.showAndWait();
        }
    }

    @FXML
    private void ajouterMatch(ActionEvent event) throws IOException {
        
            LocalDate selectedDate = dpdate.getValue();
            LocalDate today = LocalDate.now();
            
            try{
                Alert alert2;
                
            if (cbeqA.getSelectionModel().getSelectedItem() == null
                    || selectedDate == null
                    || cbeqB.getSelectionModel().getSelectedItem() == null
                    || cbtournoi.getSelectionModel().getSelectedItem() == null
                    || cbstade.getSelectionModel().getSelectedItem() == null
                    || cbtype.getSelectionModel().getSelectedItem() == null
                    || tfrA.getText().isEmpty()
                    || tfrA.getText().isEmpty()
                    || tfprix.getText().isEmpty()
                    || tfnbbtotal.getText().isEmpty()
                    || tfnbbreserve.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                    leqA.setVisible(true);
                    leqB.setVisible(true);
                    ltype.setVisible(true);
                    lstade.setVisible(true);
                    lrA.setVisible(true);
                    lrB.setVisible(true);
                    lprix.setVisible(true);
                    alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Message d'erreur");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Veuillez remplir tous les champs!");
                    alert2.showAndWait();
                 
            }   else if (Integer.parseInt(tfrA.getText()) < 0){
                     lrA.setVisible(true);
                }
                else if (Integer.parseInt(tfrA.getText()) < 0){
                     lrA.setVisible(true);
                }
                
                else if (Integer.parseInt(tfrB.getText()) < 0){
                     lrB.setVisible(true);
                }
                
                else if (selectedDate.isBefore(today)){
                    ldate.setVisible(true);
                }
                
            else {
                leqA.setVisible(false);
                leqB.setVisible(false);
                ltype.setVisible(false);
                lstade.setVisible(false);
                ltournoi.setVisible(false);
                String imagePath = f.getName();
                File destFile = new File(imagePath);
                Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                
                MatchF m1 = new MatchF();
               
                m1.setImage(imagePath);
                
                MatchF m = new MatchF(
                    (maMap.get(cbeqA.getValue()).toString()),
                    (maMap1.get(cbeqB.getValue()).toString()),
                    Date.valueOf(dpdate.getValue()),
                    Time.valueOf(tfheureDeb.getText()),
                    Time.valueOf(tfheureFin.getText()),
                    (maMap2.get(cbtype.getValue()).toString()),
                    (maMap3.get(cbstade.getValue()).toString()),
                    (maMap4.get(cbtournoi.getValue()).toString()),
                    Integer.parseInt(tfrA.getText()),
                    Integer.parseInt(tfrB.getText()),
                    Integer.parseInt(tfprix.getText()),
                    imagePath,
                    imagePath,
                    Integer.parseInt(tfnbbtotal.getText()),
                    Integer.parseInt(tfnbbreserve.getText()));
                alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Message de confirmation");
                alert2.setHeaderText(null);
                alert2.setContentText("Voulez vous ajouter ce match?");
                Optional<ButtonType> option = alert2.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    MatchFCRUD pc = new MatchFCRUD();
                    pc.add(m);

                    alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information Message");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Match ajouté avec succès!");
                    alert2.showAndWait();

                    MatchShowListData();
                    
                }
            }
            }
            catch (SQLException ex) {
                    System.out.println(ex.getMessage());;
                }
            
    }
    
    @FXML
    private void modifierMatch(ActionEvent event) {
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String uri2 = getData.path;
        uri2 = uri2.replace("\\", "\\\\");

        

        String req = "UPDATE match_f SET equipe_a_id = '"
                + maMap.get(cbeqA1.getValue()) + "', equipe_b_id = '"
                + maMap1.get(cbeqB1.getValue()) + "', date_match = '"
                + Date.valueOf(dpdate1.getValue())+ "', heure_deb_m = '"
                + Time.valueOf(tfheureDeb1.getText())+ "', heurefin_m = '"
                + Time.valueOf(tfheureFin1.getText()) + "', type_match_id = '"
                + maMap2.get(cbtype1.getValue()) + "', stade_id = '"
                + maMap3.get(cbstade1.getValue())+ "', tournoi_id = '"
                + maMap4.get(cbtournoi1.getValue())+ "', resultat_a = '"
                + tfrA1.getText()+ "', resultat_b = '"
                + tfrB1.getText()+ "', prix = '"
                + tfprix1.getText() + "', image = '"
                + uri + "', image2 = '" + uri2 + "' WHERE id ='"
                + tfid1.getText() + "'";

       
            con  = MyConnection.getInstance().getCnx();
         try {
            Alert alert;
            if (cbeqA1.getValue().isEmpty()
                    || cbeqB1.getValue().isEmpty()
                    || cbtournoi1.getValue().isEmpty()
                    || cbstade1.getValue().isEmpty()
                    || cbtype1.getValue().isEmpty()
                    || tfrA1.getText().isEmpty()
                    || tfrA1.getText().isEmpty()
                    || tfprix1.getText().isEmpty()
                    || tfnbbtotal1.getText().isEmpty()
                    || tfnbbreserve1.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous modifier le match d'ID: " + tfid1.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement st = con.createStatement();
                    st.executeUpdate(req);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    MatchShowListData();
                    
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void fillcomboEquipeA() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from equipe";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap.put(rs.getString("nom"), rs.getInt("id"));
                       
                optionstype.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    public void fillcomboEquipeB() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from equipe";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap1.put(rs.getString("nom"), rs.getInt("id"));
                       
                optionstype1.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    public void fillcomboTypeMatch() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from type_match";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap2.put(rs.getString("nom"), rs.getInt("id"));
                       
                optionstype2.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    public void fillcomboStade() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from stade";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap3.put(rs.getString("nom"), rs.getInt("id"));
                       
                optionstype3.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    
    public void fillcomboTournoi() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from tournoi";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap4.put(rs.getString("nom"), rs.getInt("id"));
                       
                optionstype4.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    public ObservableList<MatchF> MatchListData() throws SQLException {

        return new MatchFCRUD().read();
    }
    
    private ObservableList<MatchF> MatchList;
    
    public void MatchShowListData() throws SQLException {
        MatchList = MatchListData();

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        eqA_col.setCellValueFactory(new PropertyValueFactory<>("equipeA"));
        eqB_col.setCellValueFactory(new PropertyValueFactory<>("equipeB"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        stade_col.setCellValueFactory(new PropertyValueFactory<>("stade"));
        tournoi_col.setCellValueFactory(new PropertyValueFactory<>("tournoi"));
        rA_col.setCellValueFactory(new PropertyValueFactory<>("resultatA"));
        rB_col.setCellValueFactory(new PropertyValueFactory<>("resultatB"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
        image_col.setCellFactory(column ->{
            return new TableCell<MatchF,String>(){
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
                            FileInputStream stream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + imageName);
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
        image_col.setCellValueFactory(new PropertyValueFactory<>("image"));
        image2_col.setCellFactory(column ->{
            return new TableCell<MatchF,String>(){
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
                            FileInputStream stream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + imageName);
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
        );;
        image2_col.setCellValueFactory(new PropertyValueFactory<>("image2"));
        

        match_table.setItems(MatchList);

    }
    
    
    
    @FXML
    public void MatchSelect() {

        
        MatchF m = match_table.getSelectionModel().getSelectedItem();
        int num = match_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tfid1.setText(String.valueOf(m.getId()));
        cbeqA1.setValue(m.getEquipeA());
        cbeqB1.setValue(m.getEquipeB());
        cbtype1.setValue(m.getType());
        cbstade1.setValue(m.getStade());
        cbtournoi1.setValue(m.getTournoi());
        tfheureDeb1.setText(String.valueOf(m.getHeureDeb()));
        tfheureFin1.setText(String.valueOf(m.getHeureDeb()));
        cbtournoi1.setValue(m.getTournoi());
        tfrA1.setText(String.valueOf(m.getResultatA()));
        tfrB1.setText(String.valueOf(m.getResultatB()));
        tfprix1.setText(String.valueOf(m.getPrix()));
        tfrB1.setText(String.valueOf(m.getResultatB()));
        tfprix1.setText(String.valueOf(m.getPrix()));
        tfnbbreserve1.setText(String.valueOf(m.getNbrBilletReserve()));
        tfnbbtotal1.setText(String.valueOf(m.getNbrBilletTotal()));
        
        java.sql.Date sqlDate = (java.sql.Date) m.getDate();

        LocalDate localDate = sqlDate.toLocalDate();
        
        dpdate1.setValue(localDate);
    
        getData.path = m.getImage();

        getData.path = m.getImage2();

    }
    

    @FXML
    private void insererImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Ajouter une Image");

        f = fc.showOpenDialog(null);
        String i = f.getName();
        if (f != null) {
            BufferedImage bufferedImage = ImageIO.read(f);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            String imagePath = "C://xampp/htdocs/img/" + i;
            File destFile = new File(imagePath);
            Files.copy(f.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileInputStream fin = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] post_image = bos.toByteArray();
            fin.close(); // close the FileInputStream
            bos.close(); // close the ByteArrayOutputStream

        }
    }
    
    

    @FXML
    private void insererImage2(ActionEvent event) throws IOException {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(match_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            
            
        }
    }

    @FXML
    private void insererImage3(ActionEvent event) {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(match_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            
            
        }
    }

    private void insererImage4(ActionEvent event) {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(match_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            
            
        }
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrB1.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    
    
    
}
