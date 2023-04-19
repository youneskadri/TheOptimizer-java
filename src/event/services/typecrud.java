 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.services;

import event.entities.type;
import event.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamed
 */
public class typecrud {
    
    public void ajoutertype(type t){
        try {
            String requete="INSERT INTO type_event (nom_type) "
                    + "VALUES ('"+t.getNom_type()+"')";
            Statement st = new MyConnection() .getCnx() .createStatement();
            st.executeUpdate(requete);
            System.out.println("type ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public void ajoutertype2(type t){
        try {
            String requete2 = "INSERT INTO type_event (nom_type)"
                    + "VALUES (?)";
            PreparedStatement pst = new MyConnection() .getCnx() .prepareStatement(requete2);
            pst.setString(1, t.getNom_type());
            pst.executeUpdate();
            System.out.println("Votre type est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    /*public List<type> affichertypes(){
                    List<type> myList = new ArrayList<>();
                    try {
            String requete3 = " SELECT * FROM type_event";
            Statement st = new MyConnection() .getCnx() .createStatement();
           ResultSet rs = st.executeQuery(requete3);
           while(rs.next()){
               type t = new type(); 
               t.setId(rs.getInt(1));
               t.setNom_type(rs.getString("nom_type"));
               myList.add(t);
           }
           
        } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
         return myList;
    }*/
      public void updateEntity(type t) {
   try {
String requete = "UPDATE type_event SET nom_type=? WHERE id=?";
PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
pst.setString(1, t.getNom_type());
pst.setInt(2, t.getId());
pst.executeUpdate();
System.out.println("Catégorie mise à jour");
} catch (SQLException ex) {
System.out.println(ex.getMessage());
}
}
    public void modifier(type t) {
        try {
        String requete = "UPDATE type_event SET nom_type = ? WHERE id = ?";
        System.out.println(requete);
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, t.getNom_type());
        pst.setInt(2, t.getId());
        pst.executeUpdate();
        System.out.println("type modifié avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }
    
    public ObservableList<type> affichertypes() throws SQLException{
    ObservableList<type> ls = FXCollections.observableArrayList();
    
    String req = "select * from type_event";
    PreparedStatement pst = new MyConnection() .getCnx() .prepareStatement(req);
    ResultSet rs = pst.executeQuery(req);
     
    while(rs.next()){
        int id = rs.getInt("id");
        String nom = rs.getString("nom_type");
        
        
        

        type m = new type(id, nom);
        ls.add(m);

    }
    
    return ls;

    }
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM type_event WHERE id = " + id;
          Statement st = new MyConnection() .getCnx() .prepareStatement(req);
            st.executeUpdate(req);
            System.out.println("type supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
