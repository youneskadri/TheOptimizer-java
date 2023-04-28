/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kadri younes
 */
public class MatchUserController implements Initializable {

    @FXML
    private AnchorPane reservation_form;
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
    @FXML
    private ListView<?> plantransport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserverBillet(ActionEvent event) {
    }

    @FXML
    private void ajouterReservation(ActionEvent event) {
    }
    
}
