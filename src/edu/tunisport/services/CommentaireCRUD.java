/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;
import edu.tunisport.entities.Commentaire;
import edu.tunisport.entities.Commentaire;
import edu.tunisport.interfaces.EntityCRUD;
import edu.tunisport.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author medah
 */
public class CommentaireCRUD implements EntityCRUD<Commentaire>{

    public void addEntity(Commentaire t) {
  try {
            String requete= "INSERT INTO commentaire (id,blog_id,user_id,contenu_com,date_c)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, Integer.toString(t.getId()));
            pst.setString(2, Integer.toString(t.getBlog_id()));
            pst.setString(3, Integer.toString(t.getUser_id()));
            pst.setString(4, t.getContenu_com());
            pst.setString(5, t.getDate_c());
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Commentaire> displayEntities() {
        ObservableList<Commentaire> myList = FXCollections.observableArrayList();
                try {   
                    String requete= "SELECT * FROM commentaire";
                    Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
                    ResultSet rs = st.executeQuery(requete);
                    while(rs.next()){
                        Commentaire p = new Commentaire();
                        p.setId(rs.getInt(1));
                        p.setBlog_id(rs.getInt("blog_id"));
                        p.setUser_id(rs.getInt("user_id"));
                        p.setContenu_com(rs.getString("contenu_com"));
                        p.setDate_c(rs.getString("date_c"));
                        myList.add(p);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return myList;
            }   

    public ObservableList<Commentaire> displayEntitieswithcommentaire(Integer b) {
        ObservableList<Commentaire> myList = FXCollections.observableArrayList();
                try {   
                    String requete= "SELECT * FROM commentaire WHERE blog_id = "+Integer.toString(b);
                    Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
                    ResultSet rs = st.executeQuery(requete);
                    while(rs.next()){
                        Commentaire p = new Commentaire();
                        System.out.println(rs.getInt("blog_id"));
                        p.setId(rs.getInt(1));
                        p.setBlog_id(rs.getInt("blog_id"));
                        p.setUser_id(rs.getInt("user_id"));
                        p.setContenu_com(rs.getString("contenu_com"));
                        p.setDate_c(rs.getString("date_c"));
                        myList.add(p);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return myList;
            } 

    public void deleteEntity(Commentaire t) {
  try {
            String requete= "DELETE FROM commentaire WHERE id = ?;";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, Integer.toString(t.getId()));
           
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public void updateEntity(Commentaire t) {

  try {
            String requete= "UPDATE commentaire SET blog_id=?, user_id=?, contenu_com=?,date_c=? WHERE id=? ;";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setString(1, Integer.toString(t.getBlog_id()));
            pst.setString(2, Integer.toString(t.getUser_id()));
            pst.setString(3, t.getContenu_com());
            pst.setString(4, t.getDate_c());
            pst.setString(5, Integer.toString(t.getId()));
            
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}

    public void SupprimerCommentaire(Commentaire u) {
   String query="Delete FROM commentaire WHERE id ="+u.getId()+"";
        try {

            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
            System.out.println("Supprimé");

        } catch(SQLException ex) {
            System.out.println("echoué");
            System.out.println(ex);
           
        }
    }
     public ObservableList<Commentaire> AfficherCommentaire() {
        ObservableList<Commentaire>Utilisateurs = FXCollections.observableArrayList();

        String query = "Select * from `Commentaire`";
        try  {
           Statement stm = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rst = stm.executeQuery(query);

            while(rst.next()) {
                Commentaire U = new Commentaire();
                U.setId(rst.getInt(1));
                U.setBlog_id(rst.getInt("blog_id"));
                U.setUser_id(rst.getInt("user_id"));
                U.setContenu_com(rst.getString("contenu_com"));
                U.setDate_c(rst.getString("date_c"));
             

                Utilisateurs.add(U);
            }


        } catch(Exception e){
                Logger.getLogger(Commentaire.class.getName()).log(Level.SEVERE,null,e);
        }
        return Utilisateurs ;
    }
}
    

