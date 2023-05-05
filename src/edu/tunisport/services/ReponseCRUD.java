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
import edu.tunisport.entities.Reponse;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


public class ReponseCRUD  {

    Statement ste;
    Connection conn = MyConnection.getInstance().getCnx();

    
    public void ajouter(Reponse v) {
        try {
            String req = "INSERT INTO `reponse` (`id_rec`, `reponse`) VALUES (?,?)";
            System.out.println(req);
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1,v.getId_rec());
            ps.setString(2,v.getReponse());
            ps.executeUpdate();
            System.out.println("reponse ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
    public void modifier(Reponse v) {
        try {
            String req = "UPDATE `reponse` SET `id_rec` = '" + v.getId_rec() + "', `reponse` = '" + v.getReponse() + "' WHERE id = " + v.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reponse` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("reponse deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reponse> afficher() {
        List<Reponse> list = new ArrayList<>();
        try {
            String req = "Select * from reponse";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reponse v = new Reponse();
                v.setId(RS.getInt(1));
                v.setId_rec(RS.getInt(2));
                v.setReponse(RS.getString(3));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Reponse getByID(int id) {
        Reponse v = new Reponse();
        try {
            String req = "Select * from reponse where id = " + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                v.setId(RS.getInt(1));
                v.setId_rec(RS.getInt(2));
                v.setReponse(RS.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }

   
    public Reponse getByRec(int id) {
        Reponse v = new Reponse();
        try {
            String req = "Select * from reponse where id_rec = " + id;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                v.setId(RS.getInt(1));
                v.setId_rec(RS.getInt(2));
                v.setReponse(RS.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }
    
   

}
