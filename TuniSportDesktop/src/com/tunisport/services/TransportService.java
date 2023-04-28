/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.services;

import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Category_transport;
import com.tunisport.entities.Hebergement;
import com.tunisport.entities.Transport;
import com.tunisport.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kadri younes
 */
public class TransportService implements NewInterface<Transport>{
    Connection cnx;
            Category_transportService ts = new Category_transportService();

    public TransportService() {
        cnx = MaConnection.getInstance().getCnx();
}
 

    @Override
    public void ajouter(Transport t) {
String sql="insert into Transport(category_transport_id,image_transport,nom_transport) values (?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getC().getId());
            ste.setString(2, t.getImage_transport());
            ste.setString(3, t.getNom_transport());
            

            ste.executeUpdate();
            System.out.println("Transport Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Transport> afficher() {
          List<Transport> Transports = new ArrayList<>();
        String sql="select * from Transport";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                Transport p;
                p = new Transport(rs.getInt(1),
                        rs.getString("image_transport"),
                        rs.getString("nom_transport"),

                        ts.read(rs.getInt("category_transport_id")));

                Transports.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return Transports;
    }

    
      public void supprimer(Category_hebergement t) {
       
        
    }
     @Override
    public void update(Transport t) {
        String sql = "UPDATE Transport SET category_transport_id=?,image_transport=?,nom_transport=? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getC().getId());
            ste.setString(2, t.getImage_transport());
            ste.setString(3, t.getNom_transport());
   
            ste.setInt(4, t.getId());
            ste.executeUpdate();
            System.out.println("L'Transport d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public Transport read(int id) {
       String sql = "SELECT * FROM Transport WHERE id = ?";

    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);

        try (ResultSet result = ste.executeQuery()) {
            if (result.next()) {
                return new Transport(
                    result.getInt("id"),
                    result.getString("image_transport"),
                    result.getString("nom_transport"),
                    ts.read(result.getInt("category_transport_id"))
                );
            } else {
                return null;
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return null;   }

    @Override
    public void supprimer(Transport t) {
 String sql ="delete from Transport where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
   
    public ObservableList<Transport> chercherServ(String chaine) {
        String sql = "SELECT * FROM Transport WHERE (nom_transport LIKE ? or category_transport_id = ?  )  ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<Transport> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                Transport e = new Transport();

                e.setId(rs.getInt("id"));
                e.setImage_transport(rs.getString("image_transport"));
                e.setNom_transport(rs.getString("nom_transport"));
            e.setC(ts.read(rs.getInt("category_transport_id")));
                myList.add(e);
                System.out.println("hebergement trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}

    
    

