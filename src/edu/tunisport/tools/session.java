/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.tools;

/**
 *
 * @author 21699
 */
public class session {
    private  static session instance=null;

    private static int id;
    private static String first_name;
    private static String second_name;
    private  static String email;
    private static String password;
    private static String roles = "['ROLE_USER']";
    private static int phone;

    public session(int id, String first_name, String second_name, String email, String password, int phone) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    


 

    public static int getId() {
        return id;
    }

    public  static String getFirst_name() {
        return first_name;
    }

    public static String getSecond_name() {
        return second_name;
    }

    public static String getEmail() {
        return email;
    }

    public  static String getPassword() {
        return password;
    }

    public static String getRoles() {
        return roles;
    }

    public static int getPhone() {
        return phone;
    }
  


    public static session getInstance(int id, String first_name, String second_name, String email, String password, int phone) {
        if(instance == null) {
            instance = new session(id,first_name,second_name, email, password, phone);
        }
        return instance;
    }
    
     public session getInstance() {
        return instance;
    }
     
     
     
 
    public void cleanUserSession() {
       instance = null;
        
    }
 
  

  /*   @Override
    public String toString() {
        return "UserSession{" + "ID=" + ID  + ", Username=" + Username + ", Password=" +
                Password + ", Email=" + Email + ", Nom=" + Nom + ", Age=" + Age + ", Sexe=" + Sexe + '}';
    }
    */
}
