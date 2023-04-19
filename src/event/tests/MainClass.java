/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tests;

import event.entities.type;
import event.services.typecrud;
import event.utils.MyConnection;

/**
 *
 * @author mohamed
 */
public class MainClass {
    public static void main(String[] args){
      MyConnection mc = new MyConnection();
      typecrud pcd = new typecrud();
      type t2 = new type("foot");
      pcd.ajoutertype2(t2);
    
       
    }
    
}
