/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import edu.tunisport.entities.Reclamation;
import edu.tunisport.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ReclamationCRUD  {

    Statement ste;
    Connection conn = MyConnection.getInstance().getCnx();

    
    public void ajouter(Reclamation v) {
        try {
            String req = "INSERT INTO `reclamation` (`id_user`, `sujet`, `description`, `status`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,v.getId_user());
            ps.setString(2, v.getSujet());
            ps.setString(3, v.getDescription());
            ps.setInt(4, v.getStatut());
            ps.executeUpdate();
            System.out.println("Reclamation ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    public void modifier(Reclamation v) {
        try {
            String req = "UPDATE `reclamation` SET `id_user` = '" + v.getId_user() + "', `sujet` = '" + v.getSujet() + "', `description` = '" + v.getDescription() + "', `statut` = '" + v.getStatut() + "' WHERE id = " + v.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reclamation` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reclamation v = new Reclamation();
                v.setId(RS.getInt(1));
                v.setId_user(RS.getInt(2));
                v.setSujet(RS.getString(3));
                v.setDescription(RS.getString(4));
                v.setStatut(RS.getInt(5));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Reclamation getByID(int id) {
        Reclamation v = new Reclamation();
        try {
            String req = "Select * from reclamation where id = " + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                v.setId(RS.getInt(1));
                v.setId_user(RS.getInt(2));
                v.setSujet(RS.getString(3));
                v.setDescription(RS.getString(4));
                v.setStatut(RS.getInt(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }

   

}
