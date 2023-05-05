/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import edu.tunisport.entities.Reclamation;
import edu.tunisport.entities.Reponse;
import edu.tunisport.services.ReclamationCRUD;
import edu.tunisport.services.ReponseCRUD;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import edu.tunisport.tools.session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author cherif
 */
public class AdminInterfaceController implements Initializable {

    public static final String ACCOUNT_SID = "AC1afc6b9d57a1cd7fa9546a3cbede76ec";
    public static final String AUTH_TOKEN = "5b5adf342bd6b8256b48c5c332870757";
    
    @FXML
    private AnchorPane pane;
    @FXML
    private ListView<MyData> lv;
    @FXML
    private TextField tfsujet;
    @FXML
    private TextArea tfdesc;
    @FXML
    private TextArea tfrep;

    public MyData v ; 
    @FXML
    private RadioButton tb;
    @FXML
    private RadioButton recrep;
    @FXML
    private RadioButton recnonrep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ToggleGroup toggleGroup = new ToggleGroup();
        recrep.setToggleGroup(toggleGroup);
        recnonrep.setToggleGroup(toggleGroup);
        tb.setToggleGroup(toggleGroup);
        tb.setSelected(true);
lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MyData>() {
            @Override
            public void changed(ObservableValue<? extends MyData> observable, MyData oldValue, MyData newValue) {
           if(lv.getSelectionModel().getSelectedItem()!=null)
         {
                v = lv.getSelectionModel().getSelectedItem();
                if (v != null) 
                {
                    tfsujet.setText(v.getSujet());
                    tfdesc.setText(v.getDescription());
                    Reponse repn = new Reponse();
                    ReponseCRUD repc = new ReponseCRUD();
                    repn = repc.getByRec(v.getId());
                    tfrep.setText(repn.getReponse());
                }
            }
            }

        });
        /**
         * *******************************************************************
         */
        
        ReclamationCRUD vc = new ReclamationCRUD() ;
        List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) 
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
          

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
     }    

    @FXML
    private void repondrebtn(ActionEvent event) {
        ReclamationCRUD rc = new ReclamationCRUD() ; 
        ReponseCRUD rep = new ReponseCRUD();
        if(tfrep.getText().length()==0)
        {
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Champs invalides");
            alert.showAndWait();
        }
        else
        {
            rep.ajouter(new Reponse(v.getId(),tfrep.getText()));
            Reclamation r = rc.getByID(v.getId());
            r.setStatut(1);
            rc.modifier(r);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez repondé a la reclamation avec success!");
            alert.showAndWait();
            tfdesc.setText("");
            tfsujet.setText("");
            
              Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Envoi du SMS
             Message message = Message.creator(new PhoneNumber("+33756493575"),
                     new PhoneNumber("+16205089452"),
                     "votre reclamation a propos ce sujet : "+v.getSujet() + " a ete repondu : " + tfrep.getText()).create();
                         tfrep.setText("");

        
               }
       
    }
    
   
   
    

    @FXML
    private void supprimerbtn(ActionEvent event) {
            ReclamationCRUD rc = new ReclamationCRUD() ; 
            rc.supprimer(v.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Vous avez supprimmé la reclamation avec success!");
            alert.showAndWait();
            tfrep.setText("");
            tfdesc.setText("");
            tfsujet.setText("");
                 List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(rc.afficher());

        List<MyData> lmd = new ArrayList<>();
        for (int i = 0; i < lv2.size(); i++) 
            lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
          

        ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
        
    }

       @FXML
    private void rechercherbtn(ActionEvent event) {
        ReclamationCRUD vc = new ReclamationCRUD() ;
        List<Reclamation> lv2 = new ArrayList<>();
        lv2.addAll(vc.afficher());

        List<MyData> lmd = new ArrayList<>();
        {
            if(tb.isSelected())
                    for (int i = 0; i < lv2.size(); i++) 
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user()));
            if (recrep.isSelected())
            {
                for (int i = 0; i < lv2.size(); i++) 
                {
                    Reclamation r = vc.getByID(lv2.get(i).getId()); 
                    if(r.getStatut()==1)
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user())) ;
                }
            }
            if (recnonrep.isSelected())
             {
                for (int i = 0; i < lv2.size(); i++) 
                {
                    Reclamation r = vc.getByID(lv2.get(i).getId()); 
                    if(r.getStatut()==0)
                        lmd.add(new MyData(lv2.get(i).getId(), lv2.get(i).getSujet(), lv2.get(i).getDescription(),lv2.get(i).getId_user())) ;
                }
            }
        }
     ObservableList<MyData> data = FXCollections.observableArrayList(
                lmd);
        lv.setItems(data);

        lv.setCellFactory(new Callback<ListView<MyData>, ListCell<MyData>>() {
            @Override
            public ListCell<MyData> call(ListView<MyData> listView) {
                return new MyListCell();
            }
        });
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
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
                        
        Stage stage1 = (Stage) tfrep.getScene().getWindow();
  stage1.close();
    // do what you have to do
    }
}
