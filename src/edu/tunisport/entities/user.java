/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.entities;

import edu.tunisport.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author belha
 */
public class user {

    private int id;
    private String first_name;
    private String second_name;
    private String email;
    private String password;
    private String roles = "['ROLE_USER']";
    private int phone;

    public user(String first_name, String second_name, String email, String password, int phone, String question, String answer) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
    }
    private String question;
    private String answer;
    
    
    public user(int id, String first_name, String second_name, String email, String password, int phone, String answer) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
  
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
  

          Random rand = new Random();
                int token = rand.nextInt(1000);
   
    


    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }


  

 
    
    public user(int id, String first_name, String second_name, String email, String password,int phone, String roles,String answer,String question) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phone = phone;
        this.answer = answer;
        this.question=question;
    }
 public user() {
       
    }


    public user(String string, String string0, String string1, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user{" + "first_name=" + first_name + ", second_name=" + second_name + ", email=" + email + ", password=" + password + ", phone=" + phone + '}';
    }

public boolean ValidateEmail(String mail) {
        
        String EmailVerif = "SELECT count(1) FROM user WHERE email = '"+mail+"' ";
        try {
           Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
            ResultSet queryResult = st.executeQuery(EmailVerif);

            // verifier et afficher si l'email existe
            while (queryResult.next()) {
                if (queryResult.getInt(1) != 1) {
                    return true ;
                }
            }

        } catch(Exception e) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

public boolean ValidateAnswer(String answer){
    String anwser = "SELECT count(1) FROM user WHERE answer = '"+answer+"' ";
     try {
           Statement st = MyConnection.getInstance().getCnx()
                            .createStatement();
            ResultSet queryResult = st.executeQuery(anwser);

            // verifier et afficher si l'email existe
            while (queryResult.next()) {
                if (queryResult.getInt(1) != 1) {
                    return true ;
                }
            }

        } catch(Exception e) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
}

  

   

    
    
}
