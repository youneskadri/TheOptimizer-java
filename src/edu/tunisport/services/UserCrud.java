/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.tunisport.interfaces.EntityCRUD;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import edu.tunisport.entities.user;
import static edu.tunisport.gui.InscriptionController.ACCOUNT_SID;
import static edu.tunisport.gui.InscriptionController.AUTH_TOKEN;
import edu.tunisport.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.tunisport.gui.InscriptionController;

/**
 *
 * @author belha
 */
public class UserCrud implements EntityCRUD<user>{





      @Override
    public List<user> displayEntities() {
        List<user> myList = new ArrayList();
                try {   
                    String requete= "SELECT * FROM user";
                    Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
                    ResultSet rs = st.executeQuery(requete);
                    while(rs.next()){
                        user p = new user();
                        p.setFirst_name(rs.getString("first_name"));
                        p.setSecond_name(rs.getString("second_name"));
                        p.setEmail(rs.getString("email"));
                        p.setPhone(rs.getInt(1));
                        p.setPassword(rs.getString("password"));
                        myList.add(p);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return myList;
            }   


 @Override
    public void deleteEntity(user t) {
  try {
            String requete= "DELETE FROM user WHERE id = ?;";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, Integer.toString(t.getId()));
           
            pst.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
@Override
public void updateEntity(user t) {

  try {
            String requete= "UPDATE user SET first_name=?, second_name=?, email=?,phone=?, password=? WHERE id=? ;";
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
   
            st.setString(1, t.getFirst_name());
            st.setString(2, t.getSecond_name());
            st.setString(3, t.getEmail());
            st.setString(4, Integer.toString(t.getPhone()));
            st.setString(5, t.getPassword());
            st.setString(6, Integer.toString(t.getId()));
            st.executeUpdate();
            System.out.println("Success!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
   public ObservableList<user> AfficherUser() {
        ObservableList<user>Utilisateurs = FXCollections.observableArrayList();

        String query = "Select * from `user`";
        try  {
           Statement stm = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rst = stm.executeQuery(query);

            while(rst.next()) {
                user U = new user();
                U.setId(rst.getInt("id"));
                U.setFirst_name(rst.getString("first_name"));
                U.setSecond_name(rst.getString("second_name"));
                U.setPhone(rst.getInt("phone"));
                U.setEmail(rst.getString("email"));
              

                Utilisateurs.add(U);
            }


        } catch(Exception e){
                Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,e);
        }
        return Utilisateurs ;
    }
   
 
    public void SupprimerUser(user u) {
        String query="Delete FROM user WHERE id ="+u.getId()+"";
        try {

            Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
            System.out.println("Supprimé");

        } catch(SQLException ex) {
            System.out.println("echoué");
            System.out.println(ex);
            
        }
    }
     public void modifier(user u) {
       String query = "UPDATE user SET email ='" +u.getEmail()+"',first_name ='" +u.getFirst_name()+"',second_name ='" +u.getSecond_name()+"',phone ='" +u.getPhone()+"' WHERE ID =" +u.getId()+"";
      
        try {
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
            stm.executeUpdate(query);
              System.out.println("Update Ye5dem");
            
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update done");
    }

    @Override
    public void addEntity(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
