/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunisport.services;

import com.tunisport.entities.Category_hebergement;
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
public class Category_hebergementService implements NewInterface<Category_hebergement>{
Connection cnx;

    public Category_hebergementService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Category_hebergement t) {
String sql="insert into Category_hebergement(nomcategory) values (?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomcategory());
            ste.executeUpdate();
            System.out.println("categorie hebergement Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Category_hebergement> afficher() {
          List<Category_hebergement> personnes = new ArrayList<>();
        String sql="select * from Category_hebergement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                Category_hebergement p = new Category_hebergement(rs.getInt(1),
                        rs.getString("nomcategory"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return personnes;
    }

    @Override
    public void supprimer(Category_hebergement t) {
        String sql ="delete from category_hebergement where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     @Override
    public void update(Category_hebergement t){
        String sql = "UPDATE category_hebergement SET nomcategory = ? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNomcategory());
            
            ste.setInt(2, t.getId());
            ste.executeUpdate();
            System.out.println("La categorie d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }



    @Override
    public Category_hebergement read(int id){
        String sql = "SELECT Nomcategory FROM category_hebergement WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Category_hebergement(id,
                            result.getString("Nomcategory"));
                           
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;
    }
    }
    
