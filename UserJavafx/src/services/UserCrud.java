/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.EntityCrud;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import entity.user;
import tools.Myconnection;

/**
 *
 * @author belha
 */
public class UserCrud implements EntityCrud<user>{

    /**
     *
     * @param t
     */
    public void addEntity(user t) {
        try {
            String requete = "INSERT INTO user (first_name,second_name,email,phone,password) "
                    + "VALUES ("
                + "'"+t.getFirst_name()+"','"+t.getSecond_name()+"'" +t.getEmail()+"'"+t.getPhone()+"'"+t.getPassword()+ "')";
            Statement st = Myconnection.getInstance().getCnx()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("user ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<user> display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
