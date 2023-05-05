/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import edu.tunisport.entities.Classement;
import edu.tunisport.entities.MatchF;
import edu.tunisport.entities.Reservation;
import edu.tunisport.services.ReservationCRUD;
import edu.tunisport.tools.ClassementData;
import edu.tunisport.tools.MyConnection;
import edu.tunisport.tools.ResultatData;


import io.joshworks.restclient.http.mapper.ObjectMapper;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.tunisport.services.MatchFCRUD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MatchUserController implements Initializable {

    @FXML
    private AnchorPane reservation_form;
    private Button btnreserver;
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<String> cbmatch;
    @FXML
    private ComboBox<String> cbuser;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private Button btnretour;
    ObservableList<String> optionstype = FXCollections.observableArrayList();
    ObservableList<String> optionstype1 = FXCollections.observableArrayList();
    
    Map<String, Integer> maMap = new HashMap<String, Integer>();
    Map<MatchF, Integer> maMap1 = new HashMap<MatchF, Integer>();
   
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    @FXML
    private ListView<MatchF> MatchList;
    @FXML
    private PieChart pie;
    
    private MatchF selectedMatch;
    @FXML
    private AnchorPane reservation_details;
    @FXML
    private Button btnretour1;
    @FXML
    private TextField tfnbrbillet1;
    private TextField tfetat1;
    @FXML
    private ComboBox<String> cbuser1;
    @FXML
    private ComboBox<String> cbmatch1;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField tfid;
    @FXML
    private AnchorPane update_reservation_form;
    @FXML
    private ListView<Reservation> reservationList;
    @FXML
    private Button btnclassement;
    @FXML
    private AnchorPane classement_form;
    @FXML
    private TableView<Classement> classement_table;
    @FXML
    private TableColumn<Classement, String> rangCol;
    @FXML
    private TableColumn<Classement, String> equipeCol;
    @FXML
    private TableColumn<Classement, String> mjCol;
    @FXML
    private TableColumn<Classement,String> victoireCol;
    @FXML
    private TableColumn<Classement, String> nulCol;
    @FXML
    private TableColumn<Classement, String> defaiteCol;
    @FXML
    private TableColumn<Classement, String> bmCol;
    @FXML
    private TableColumn<Classement, String> beCol;
    @FXML
    private TableColumn<Classement, String> dbCol;
    @FXML
    private TableColumn<Classement, String> pointsCol;
    
    private String userId = null;
    @FXML
    private Button btnpaiement;
    private AnchorPane paiement_form;
    @FXML
    private Button btnretour2;
    
    private WebView webView;
    @FXML
    private AnchorPane resultats_form;
    @FXML
    private ListView<MatchF> MatchList1;
    @FXML
    private Button btnresultats;
   
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ResultatData r = new ResultatData();
        fillcomboUser();
        cbuser.setItems(optionstype);
        try{
            MatchDetails();
        }catch (SQLException ex){
            ex.getMessage();
        }
        
        try {
            MatchDetails2(r.getScoreData());
        } catch (IOException ex) {
            Logger.getLogger(MatchUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MatchUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         try {
            ClassementData c = new ClassementData();
            List<Classement> teamStandings;
            
            teamStandings = parseStandingsJson(c.getStandingsData());
            
            
            rangCol.setCellValueFactory(new PropertyValueFactory<>("rang"));
            equipeCol.setCellValueFactory(new PropertyValueFactory<>("equipe"));
            mjCol.setCellValueFactory(new PropertyValueFactory<>("joues"));
            victoireCol.setCellValueFactory(new PropertyValueFactory<>("victoire"));
            nulCol.setCellValueFactory(new PropertyValueFactory<>("nul"));
            defaiteCol.setCellValueFactory(new PropertyValueFactory<>("defaite"));
            bmCol.setCellValueFactory(new PropertyValueFactory<>("butPour"));
            beCol.setCellValueFactory(new PropertyValueFactory<>("butContre"));
            dbCol.setCellValueFactory(new PropertyValueFactory<>("differenceBut"));
            pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
            
            classement_table.setItems(FXCollections.observableList(teamStandings));
        } catch (IOException ex) {
            
        }
        
    }    

    @FXML
    private void reserverBillet(ActionEvent event) {
        if (event.getSource() == btnreserver) {
            reservation_form.setVisible(false);
            add_reservation_form.setVisible(true);
            
        } else if (event.getSource() == btnretour) {
            reservation_form.setVisible(true);
            add_reservation_form.setVisible(false);
            
        } 
    }

    
    
     public void fillcomboUser() {
       
        con = MyConnection.getInstance().getCnx();
        try {
            Statement st = con.createStatement();
            String req = "select * from user";
            
            
            ResultSet rs = st.executeQuery(req);
           
            while (rs.next()) {
                maMap.put(rs.getString("username"), rs.getInt("id"));
                       
                optionstype.add(rs.getString("username"));
            }

        } catch (SQLException ex) {

        }   
    }
    
    

    @FXML
    private void ajouterReservation(ActionEvent event) throws SQLException {
        LocalDate selectedDate = dpdate.getValue();
            LocalDate today = LocalDate.now();

                Alert alert2;
                
            if (cbuser.getSelectionModel().getSelectedItem() == null
                    || selectedDate == null
                    || cbmatch.getSelectionModel().getSelectedItem() == null
                    || tfnbrbillet.getText().isEmpty()
                    
                    ){

                    alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Message d'erreur");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Veuillez remplir tous les champs!");
                    alert2.showAndWait();
                 
            }   else if (Integer.parseInt(tfnbrbillet.getText()) < 0){
                     tfnbrbillet.setVisible(true);
                }
                
                
            else {

                Reservation r = new Reservation( 
                        maMap.get(cbuser.getValue()).toString(),
                        cbmatch.getValue(),
                        Date.valueOf(dpdate.getValue()),
                        Integer.parseInt(tfnbrbillet.getText()),
                        "en attente"
                    );
                alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Message de confirmation");
                alert2.setHeaderText(null);
                alert2.setContentText("Voulez vous faire une reservation associée à ce match?");
                Optional<ButtonType> option = alert2.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    ReservationCRUD pc = new ReservationCRUD();
                    
                    MatchFCRUD matchCRUD = new MatchFCRUD();

                    List<Reservation> userReservations = pc.getReservationsByUser(maMap.get(cbuser.getValue()).toString());

                    Set<MatchF> uniqueMatches = new HashSet<>();
                        for (Reservation reservation : userReservations) {
                            MatchF match = matchCRUD.getMatchById(Integer.parseInt(reservation.getMatch_id()));
                            uniqueMatches.add(match);
                        }
                    r.setEtat("confirmée");
                    pc.add(r);
                    
                    if (uniqueMatches.size() >= 1 && uniqueMatches.size() <= 2 && userReservations.size() == 2) {
    
                        double totalPrix = 0.0;

                        Reservation freeReservation = new Reservation(maMap.get(cbuser.getValue()).toString(), cbmatch.getValue(), Date.valueOf(dpdate.getValue()), Integer.parseInt(tfnbrbillet.getText()), "en attente");

                        MatchF match = matchCRUD.getMatchById(Integer.parseInt(freeReservation.getMatch_id()));
                        double reservationPrix = freeReservation.getNbr_billet() * match.getPrix();
                        totalPrix = reservationPrix;
                            
                        freeReservation.setEtat("confirmée");

                        pc.add(freeReservation);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Félicitations! Vous avez gagné une réservation gratuite d'une valeur de " + totalPrix + " DT.");
                        alert.showAndWait();
                    } else {
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Désolé, vous n'êtes pas éligible pour une réservation gratuite pour le moment.");
                        alert.showAndWait();
                    }

                    alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information Message");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Reservation ajoutée avec succès!");
                    alert2.showAndWait();
                    

                    
 
                
                    if (event.getSource() == btnajouter) {
                        reservation_details.setVisible(true);
                        add_reservation_form.setVisible(false);
                        Reservation newReservation = pc.getLatestReservation();
                        
                        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList(newReservation);
                        reservationList.setItems(reservationsList);
                        reservationList.setCellFactory(param -> new ListCell<Reservation>() {
                            @Override
                            protected void updateItem(Reservation item, boolean empty) {
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

                                    // Add team names
                                    Label userLabel = new Label(String.valueOf(item.getDate()));
                                    userLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                                    Label NombreBilletLabel = new Label(String.valueOf(item.getNbr_billet()));
                                    NombreBilletLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                                    gridPane.add(userLabel, 0, 0);
                                    gridPane.add(NombreBilletLabel, 10, 0);

 

                                    setGraphic(gridPane);
                                }

                            }

                            });
                        
                }
 
                }
                }
            
    }

    public List<MatchF> MatchListData() throws SQLException {

            return new MatchFCRUD().getAllMatches();
        }

    public void MatchDetails() throws SQLException{
        List<MatchF> matches = MatchListData();
        Collections.sort(matches, new Comparator<MatchF>() {
            @Override
            public int compare(MatchF m1, MatchF m2) {
                return m1.getDate().compareTo(m2.getDate());
            }
        });
            MatchList.setItems(FXCollections.observableList(matches));
            MatchList.setCellFactory(param -> new ListCell<MatchF>() {
                @Override
                protected void updateItem(MatchF item, boolean empty) {
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

                        // Add team names
                        Label equipeALabel = new Label(item.getEquipeA());
                        equipeALabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        Label equipeBLabel = new Label(item.getEquipeB());
                        equipeBLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        gridPane.add(equipeALabel, 0, 0);
                        gridPane.add(equipeBLabel, 2, 0);
                        
                        ImageView equipeAImage = new ImageView();
                        ImageView equipeBImage = new ImageView();

                        try {
                            // Load the images from disk or URL using their names from the database
                            FileInputStream equipeAStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getImage());
                            Image equipeAImageFile = new Image(equipeAStream);
                            equipeAImage.setImage(equipeAImageFile);

                            FileInputStream equipeBStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getImage2());
                            Image equipeBImageFile = new Image(equipeBStream);
                            equipeBImage.setImage(equipeBImageFile);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }  
                        equipeAImage.setFitWidth(50); 
                        equipeAImage.setFitHeight(50);
                        equipeBImage.setFitWidth(50); 
                        equipeBImage.setFitHeight(50);
                        gridPane.add(equipeAImage, 1, 0);
                        gridPane.add(equipeBImage, 3, 0);

                        // Add stadium and date
                        Label stadiumLabel = new Label(item.getStade());
                        stadiumLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        Label dateLabel = new Label(String.valueOf(item.getDate()));
                        dateLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        gridPane.add(stadiumLabel, 0, 1);
                        gridPane.add(dateLabel, 2, 1);

                        Button reserveButton = new Button("Réserver Billet");
                        reserveButton.getProperties().put("match", item);
                        reserveButton.setStyle("-fx-background-color: #00cc66;-fx-background-radius:50px;-fx-text-fill:#fff;-fx-font-size:14px;-fx-font-family:\"Berlin Sans FB\";-fx-cursor:hand;");
               
                        reserveButton.setOnAction(event -> {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/tunisport/gui/reservation.fxml"));
                            // MatchF selectedMatch = MatchList.getSelectionModel().getSelectedItem();
                            MatchF selectedMatch = (MatchF) reserveButton.getProperties().get("match");
                            String selectedMatchId = String.valueOf(selectedMatch.getId());  
                            cbmatch.getItems().add(selectedMatchId);
                            cbmatch.getSelectionModel().select(selectedMatchId);
                            
                            try {
                                Parent root = loader.load();
                            } catch (IOException ex) {
                            }
                                ReservationController controller = loader.getController();
                                if (selectedMatch.getNbrBilletReserve() >= selectedMatch.getNbrBilletTotal()){
                                reserveButton.setText("Billets Non Dispo");
                                reserveButton.setStyle("-fx-background-color: #ff3333;-fx-background-radius:50px;-fx-text-fill:#fff;-fx-font-size:14px;-fx-font-family:\"Berlin Sans FB\";-fx-cursor:hand;");
                             }
                                else {
                                    if (event.getSource() == reserveButton) {
                                        reservation_form.setVisible(false);
                                        add_reservation_form.setVisible(true);

                                    } else if (event.getSource() == btnretour) {
                                        reservation_form.setVisible(true);
                                        add_reservation_form.setVisible(false);
                                    }
                            }
                        });
 
                        Button detailsButton = new Button("Details");
                        detailsButton.setStyle("-fx-background-color:#9966ff;-fx-background-radius:50px;-fx-text-fill:#fff;-fx-font-size:14px;-fx-font-family:\"Berlin Sans FB\";-fx-cursor:hand;");
                         
                        detailsButton.setOnAction(event -> {
                            MatchF selectedMatch2 = getItem();
                            StackPane contentPane = new StackPane();
                             Label chartTitle = new Label("Reserved Tickets");
                            chartTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                            
                            PieChart chart = new PieChart();
                            chart.setTitle("Information de billets");
                            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                                new PieChart.Data("Reservés",selectedMatch2.getNbrBilletReserve()),
                                new PieChart.Data("Disponibles",selectedMatch2.getNbrBilletTotal() - selectedMatch2.getNbrBilletReserve()) );
                            chart.setData(pieChartData);
                            
                            contentPane.setStyle("-fx-background-color: #fff");

    
                            contentPane.getChildren().addAll(chartTitle, chart);

                            
                            Popup popup = new Popup();
                            popup.setAutoHide(true);
                            popup.getContent().add(contentPane);

                            
                            Bounds buttonBounds = detailsButton.localToScreen(detailsButton.getBoundsInLocal());
                            double popupX = buttonBounds.getMinX() + 10;
                            double popupY = buttonBounds.getMaxY() + 10;
                            popup.show(detailsButton.getScene().getWindow(), popupX, popupY);
                        });
                        gridPane.add(reserveButton,5, 1);
                         gridPane.add(detailsButton, 1, 2);
                       
                        setGraphic(gridPane);
                    }

                }
                
            });
    }

    @FXML
    private void switchform2(ActionEvent event) {
         if (event.getSource() == btnupdate) {
            reservation_details.setVisible(false);
            update_reservation_form.setVisible(true);
            
        } else if (event.getSource() == btnretour1) {
            reservation_details.setVisible(true);
            update_reservation_form.setVisible(false);
            
        }
    }

    @FXML
    private void modifierReservation(ActionEvent event) {
                
                    
        String req = "UPDATE reservation SET user_id = '"
                + maMap.get(cbuser1.getValue()) + "', match_f_id = '"
                + maMap1.get(cbmatch1.getValue()) + "', date_resevation = '"
                + Date.valueOf(dpdate1.getValue()) + "', nombre_billet = '"
                + tfnbrbillet1.getText()+ "', etat = '"
                + tfetat1.getText()
                + "' WHERE id ='"
                + tfid .getText() + "'";

       
            con = MyConnection.getInstance().getCnx();
         try {
            Alert alert;
            if (cbuser1.getSelectionModel().getSelectedItem() == null
                    || cbmatch1.getSelectionModel().getSelectedItem() == null
                    || tfnbrbillet1.getText().isEmpty()
                    || tfetat1.getText().isEmpty()
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
                alert.setContentText("Voulez vous modifier la reservation d'ID: " + tfid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement st = con.createStatement();
                    st.executeUpdate(req);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Reservation mise à jour avec succès!");
                    alert.showAndWait();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void ReservationSelect() {

        Reservation r = reservationList.getSelectionModel().getSelectedItem();
        int num = reservationList.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tfid.setText(String.valueOf(r.getId()));
        cbuser1.setValue(r.getUser_id());
        cbmatch1.setValue(String.valueOf(r.getMatch_id()));
        tfnbrbillet1.setText(String.valueOf(r.getNbr_billet()));
        
        java.sql.Date sqlDate = (java.sql.Date) r.getDate();

        LocalDate localDate = sqlDate.toLocalDate();
        
        dpdate1.setValue(localDate);

        
    }

    @FXML
    private void switchform3(ActionEvent event) {
        if (event.getSource() == btnclassement) {
            reservation_form.setVisible(false);
            classement_form.setVisible(true);
            
        } else if (event.getSource() == btnretour1) {
            reservation_form.setVisible(true);
            classement_form.setVisible(false);
            
        } 
    }
    
    
    private List<Classement> parseStandingsJson(String json) throws JSONException {
    List<Classement> teamStandings = new ArrayList<>();

    JSONObject jsonObject = new JSONObject(json);
    JSONArray jsonArray = jsonObject.getJSONArray("response");
    
    JSONObject leagueStanding = jsonArray.getJSONObject(0);

    JSONArray standingsArray = leagueStanding.getJSONObject("league").getJSONArray("standings");
    for (int i = 0; i < jsonArray.length(); i++) {
        JSONArray standings = standingsArray.getJSONArray(i);

        for (int j = 0; j < standings.length(); j++) {
            JSONObject standing = standings.getJSONObject(j);

            int rank = standing.getInt("rank");

            JSONObject teamObj = standing.getJSONObject("team");
            
            String teamName = teamObj.getString("name");
           

            int points = standing.getInt("points");
            int goalsDiff = standing.getInt("goalsDiff");
            JSONObject allObj = standing.getJSONObject("all");
            int played = allObj.getInt("played");
            int win = allObj.getInt("win");
            int draw = allObj.getInt("draw");
            int lose = allObj.getInt("lose");
            int goalsFor = allObj.getJSONObject("goals").getInt("for");
            int goalsAgainst = allObj.getJSONObject("goals").getInt("against");
            String group = standing.getString("group");

            Classement teamStanding = new Classement(rank, teamName, points, goalsDiff, played, win, draw, lose, goalsFor, goalsAgainst);
            
            teamStandings.add(teamStanding);
            
        }
    }

    return teamStandings;
}

    @FXML
    private void switchform4(ActionEvent event) {
        if (event.getSource() == btnresultats) {
            
            reservation_form.setVisible(false);
            resultats_form.setVisible(true);
            
        } else if (event.getSource() == btnretour2) {
            reservation_form.setVisible(true);
            resultats_form.setVisible(false);
            
        }
    }
    
    
    public List<MatchF> FinishedMatches() throws SQLException {

            return new MatchFCRUD().getFinishedMatches();
        }

    public void MatchDetails2(String jsonResponse) throws SQLException{
        
        JSONObject rootObject = new JSONObject(jsonResponse);
        JSONArray responseArray  = rootObject .getJSONArray("response");
        List<MatchF> matches = FinishedMatches();
        Collections.sort(matches, new Comparator<MatchF>() {
            @Override
            public int compare(MatchF m1, MatchF m2) {
                return m1.getDate().compareTo(m2.getDate());
            }
        });
        
        for (int i = 0; i < responseArray.length(); i++) {
        JSONObject matchObject = responseArray.getJSONObject(i);
        JSONObject teamsObject = matchObject.getJSONObject("teams");
        String homeTeamName = teamsObject.getJSONObject("home").getString("name");
        String awayTeamName = teamsObject.getJSONObject("away").getString("name");
        
        String status = matchObject.getJSONObject("fixture").getJSONObject("status").getString("long");

        for (MatchF match : matches) {
            if (match.getEquipeA().equals(homeTeamName) && match.getEquipeB().equals(awayTeamName)) {
                if (status.equals("Match Finished")) {
                    int homeTeamScore = matchObject.getJSONObject("goals").isNull("home") ? null : matchObject.getJSONObject("goals").getInt("home");
                    int awayTeamScore = matchObject.getJSONObject("goals").isNull("away") ? null : matchObject.getJSONObject("goals").getInt("away");
                    match.setResultatA(homeTeamScore);
                    match.setResultatB(awayTeamScore);
                    MatchFCRUD pc = new MatchFCRUD();
                    pc.update(match);
            }
        }
    }
        
            MatchList1.setItems(FXCollections.observableList(matches));
            MatchList1.setCellFactory(param -> new ListCell<MatchF>() {
                @Override
                protected void updateItem(MatchF item, boolean empty) {
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

                        // Add team names
                        Label equipeALabel = new Label(item.getEquipeA());
                        equipeALabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        Label equipeBLabel = new Label(item.getEquipeB());
                        equipeBLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        gridPane.add(equipeALabel, 0, 0);
                        gridPane.add(equipeBLabel, 2, 0);
                        
                        ImageView equipeAImage = new ImageView();
                        ImageView equipeBImage = new ImageView();

                        try {
                            // Load the images from disk or URL using their names from the database
                            FileInputStream equipeAStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getImage());
                            Image equipeAImageFile = new Image(equipeAStream);
                            equipeAImage.setImage(equipeAImageFile);

                            FileInputStream equipeBStream = new FileInputStream("C:\\xampp\\htdocs\\img\\" + item.getImage2());
                            Image equipeBImageFile = new Image(equipeBStream);
                            equipeBImage.setImage(equipeBImageFile);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }  
                        equipeAImage.setFitWidth(50); 
                        equipeAImage.setFitHeight(50);
                        equipeBImage.setFitWidth(50); 
                        equipeBImage.setFitHeight(50);
                        gridPane.add(equipeAImage, 1, 0);
                        gridPane.add(equipeBImage, 3, 0);

                        
                        Label stadiumLabel = new Label(item.getStade());
                        stadiumLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        Label dateLabel = new Label(String.valueOf(item.getDate()));
                        dateLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        gridPane.add(stadiumLabel, 0, 1);
                        gridPane.add(dateLabel, 2, 1);
                        
                        Label resultatALabel = new Label(String.valueOf(item.getResultatA()));
                        resultatALabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        Label resultatBLabel = new Label(String.valueOf(item.getResultatB()));
                        resultatBLabel.setStyle("-fx-font-family:\"Berlin Sans FB\"");
                        
                        gridPane.add(resultatALabel, 0, 2);
                        gridPane.add(resultatBLabel, 2, 2);

                        setGraphic(gridPane);
                    }

                }
                
            });
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
                        
        Stage stage1 = (Stage) classement_table.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) classement_table.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfid.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) classement_table.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) classement_table.getScene().getWindow();
  stage1.close();
    } // do what you have to do
      @FXML
     private void reclamationbouton(ActionEvent event) {
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
                        
        Stage stage1 = (Stage) classement_table.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
    
    
    
    
    

    
}
