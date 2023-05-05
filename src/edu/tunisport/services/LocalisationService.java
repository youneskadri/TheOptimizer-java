/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import edu.tunisport.entities.Category_hebergement;
import edu.tunisport.entities.localisation;
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
public class LocalisationService implements NewInterface<localisation>{
Connection cnx;

    public LocalisationService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(localisation t) {
String sql="insert into localisation(lieux) values (?)";
        PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getLieux());
            ste.executeUpdate();
            System.out.println("Personne Ajoutée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<localisation> afficher() {
          List<localisation> personnes = new ArrayList<>();
        String sql="select * from localisation";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
               
                localisation p = new localisation(rs.getInt(1),
                        rs.getString("Lieux"));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
        return personnes;
    }

    @Override
    public void supprimer(localisation t) {
        String sql ="delete from localisation where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public boolean modifier(localisation t) {
    String sql = "UPDATE localisation SET lieux = ? WHERE id = ?";

    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, t.getLieux());
        ste.setInt(2, t.getId());
        int rowsUpdated = ste.executeUpdate();
        System.out.println("L'emplacement d'id= " + t.getId() + " a ete mis a jour avec succes");
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
}
     @Override
    public void update(localisation t){
        String sql = "UPDATE localisation SET lieux = ? WHERE id = ?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getLieux());
               ste.setInt(2, t.getId());
            ste.executeUpdate();
            System.out.println("L'emplacement d'id= "+t.getId()+" a ete mis a jour avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public localisation read(int id){
        String sql = "SELECT lieux FROM localisation WHERE id = ?";

        try{
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            try (ResultSet result = ste.executeQuery()) {
                if (result.next()) {
                    return new localisation(id,
                            result.getString("lieux"));
                           
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
    
