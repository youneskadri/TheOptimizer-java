/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.services;

import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Hebergement;
import com.tunisport.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kadri younes
 */
public class HebergementService implements NewInterface<Hebergement>{
Connection cnx;
    LocalisationService ps = new LocalisationService();
    Category_hebergementService us = new Category_hebergementService();

    public HebergementService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Hebergement t) {
String sql="insert into Hebergement(localisation_id,category_hebergement_id,image,nom_heberg,deschebergement) values (?,?,?,?,?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getL().getId());
            ste.setInt(2, t.getC().getId());
            ste.setString(3, t.getImage());
            ste.setString(4, t.getNom_heberg());
            ste.setString(5, t.getDeschebergement());
            

            ste.executeUpdate();
            System.out.println("Hebergement Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Hebergement> afficher() {
          List<Hebergement> Hebergements = new ArrayList<>();
        String sql="select * from Hebergement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                Hebergement p;
                p = new Hebergement(rs.getInt(1),
                        rs.getString("image"),
                        rs.getString("nom_heberg"),
                       rs.getString("deschebergement"),

                        ps.read(rs.getInt("localisation_id")),
                    us.read(rs.getInt("category_hebergement_id")));
                Hebergements.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return Hebergements;
    }

    
      public void supprimer(Category_hebergement t) {
               
    }
     @Override
    public void update(Hebergement t) {
        String sql = "UPDATE Hebergement SET localisation_id=?,category_hebergement_id=?,image=?,nom_heberg=?,deschebergement=? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getL().getId());
            ste.setInt(2, t.getC().getId());
            ste.setString(3, t.getImage());
            ste.setString(4, t.getNom_heberg());
            ste.setString(5, t.getDeschebergement());
   
            ste.setInt(6, t.getId());
            ste.executeUpdate();
            System.out.println("L'Hebergement d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public Hebergement read(int id) {
        String sql = "SELECT localisation_id,category_hebergement_id, image, nom_heberg,deschebergement  FROM Hebergement WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Hebergement(result.getInt(id),
                  
                    result.getString("image"),
                    result.getString(" nom_heberg"),
                    result.getString("deschebergement"),
                      ps.read(result.getInt("localisation_id")),
                    us.read(result.getInt("category_hebergement_id")));
                  
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;    }

    @Override
    public void supprimer(Hebergement t) {
 String sql ="delete from Hebergement where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
