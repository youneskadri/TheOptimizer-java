/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;
import edu.tunisport.entities.Blog;
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
public class BlogCRUD implements EntityCRUD<Blog>{

    @Override
    public void addEntity(Blog t) {
  try {
            String requete= "INSERT INTO blog (id,titre,descreption,contenu,image,likes)"
                    + "VALUES (?,?,?,?,?,0)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, Integer.toString(t.getId()));
            pst.setString(2, t.getTitre());
            pst.setString(3, t.getDescreption());
            pst.setString(4, t.getContenu());
            pst.setString(5, t.getImage());
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Blog> displayEntities() {
        List<Blog> myList = new ArrayList();
                try {   
                    String requete= "SELECT * FROM Blog";
                    Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
                    ResultSet rs = st.executeQuery(requete);
                    while(rs.next()){
                        Blog p = new Blog();
                        p.setId(rs.getInt(1));
                        p.setTitre(rs.getString("titre"));
                        p.setDescreption(rs.getString("descreption"));
                        p.setContenu(rs.getString("contenu"));
                        p.setImage(rs.getString("image"));
                        myList.add(p);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return myList;
            }   
 public ObservableList<Blog> AfficherBlog() {
        ObservableList<Blog>Utilisateurs = FXCollections.observableArrayList();

        String query = "Select * from `Blog`";
        try  {
           Statement stm = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rst = stm.executeQuery(query);

            while(rst.next()) {
                Blog U = new Blog();
                U.setId(rst.getInt(1));
                U.setTitre(rst.getString("titre"));
                U.setDescreption(rst.getString("descreption"));
                U.setContenu(rst.getString("contenu"));
                U.setImage(rst.getString("image"));
                U.setLikes(rst.getInt("likes"));
             

                Utilisateurs.add(U);
            }


        } catch(Exception e){
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE,null,e);
        }
        return Utilisateurs ;
    }

 @Override
    public void deleteEntity(Blog t) {
  try {
            String requete= "DELETE FROM blog WHERE id = ?;";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, Integer.toString(t.getId()));
           
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  public void SupprimerBlog(Blog u) {
        String query="Delete FROM blog WHERE id ="+u.getId()+"";
        try {

            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
            System.out.println("Supprimé");

        } catch(SQLException ex) {
            System.out.println("echoué");
            System.out.println(ex);
           
        }
    }
  public void modifierLike(Blog u) {
  String query = "UPDATE blog SET  likes ='" +u.getLikes()+"' WHERE id =" +u.getId()+"";
     
        try {
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
              System.out.println("Update Ye5dem");
           
        } catch (SQLException ex) {
            Logger.getLogger(BlogCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update done");
       }
       public void modifier(Blog u) {
  String query = "UPDATE blog SET titre ='" +u.getTitre()+"',descreption ='" +u.getDescreption()+"',contenu ='" +u.getContenu()+"',image ='" +u.getImage()+"',likes ='"+u.getId()+"";
     
        try {
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
              System.out.println("Update Ye5dem");
           
        } catch (SQLException ex) {
            Logger.getLogger(BlogCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update done");
       }
@Override
public void updateEntity(Blog t) {

  try {
            String requete= "UPDATE blog SET titre=?, descreption=?, contenu=?,image=? WHERE id=? ;";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getDescreption());
            pst.setString(3, t.getContenu());
            pst.setString(4, t.getImage());
            pst.setString(5, Integer.toString(t.getId()));
            
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
}
    

