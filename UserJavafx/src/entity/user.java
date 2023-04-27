/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
    private String roles;
       private String phone;
    
  
 
      public user(int id, String first_name, String second_name, String email, String password,String phone) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    
    
 
    
    public user(int id, String first_name, String second_name, String email, String password,String phone, String roles) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phone = phone;
    }
 public user() {
       
    }

    public user(String first_name, String second_name, String email, String password, String phone) {
       this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

}
