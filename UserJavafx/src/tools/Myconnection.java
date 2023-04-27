/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author karra
 */
public class Myconnection {
    
    private String url="jdbc:mysql://localhost:3306/tunisport";
    private String login="root";
    private String pwd="";
    Connection cnx;
    public static Myconnection instance;

    private Myconnection() {
        
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Myconnection getInstance() {
       
        if(instance == null){
            instance = new Myconnection();
        }
        return instance;
    }
    
    
    
    
    
}
