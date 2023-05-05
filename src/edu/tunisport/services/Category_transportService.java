/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import edu.tunisport.entities.Category_hebergement;
import edu.tunisport.entities.Category_transport;
import edu.tunisport.tools.MyConnection;
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
public class Category_transportService implements NewInterface<Category_transport>{
Connection cnx;

    public Category_transportService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Category_transport t) {
String sql="insert into Category_transport(typetransport) values (?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getTypetransport());
            ste.executeUpdate();
            System.out.println("Categorie Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Category_transport> afficher() {
          List<Category_transport> personnes = new ArrayList<>();
        String sql="select * from Category_transport";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                Category_transport p = new Category_transport(rs.getInt(1),
                        rs.getString("typetransport"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return personnes;
    }

    @Override
    public void supprimer(Category_transport t) {
        String sql ="delete from category_transport where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public Category_transport read(int id) {
String sql = "SELECT typetransport FROM category_transport WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new Category_transport(id,
                            result.getString("typetransport"));
                           
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        return null;
    }    

    @Override
    public void update(Category_transport t) {
  String sql = "UPDATE category_transport SET typetransport = ? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getTypetransport());
            
            ste.setInt(2, t.getId());
            ste.executeUpdate();
            System.out.println("La categorie d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    public Category_transport getCategory_transportById(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
