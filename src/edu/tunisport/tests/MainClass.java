/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.tests;

import edu.tunisport.entities.Blog;
import edu.tunisport.entities.Commentaire;
import edu.tunisport.services.BlogCRUD;
import edu.tunisport.services.CommentaireCRUD;
import edu.tunisport.tools.MyConnection;

/**
 *
 * @author karra
 */
public class MainClass {
    
    public static void main(String[] args) {
       
        
        Blog p = new Blog(50,"test", "test","test", "test");
/*
        BlogCRUD pcd = new BlogCRUD();
        pcd.addEntity(p);
        
        Blog newp = new Blog(50,"test2", "test2","test2", "test2");
        pcd.updateEntity(newp);
        
        System.out.println(pcd.displayEntities());
        
        pcd.deleteEntity(p);
        
        
        
        Commentaire p = new Commentaire(20, 50,1,"test", "2018-01-02");

        CommentaireCRUD pcd = new CommentaireCRUD();
        pcd.addEntity(p);
        
        Commentaire newp = new Commentaire(20,50, 1,"test2", "2018-06-20");
        pcd.updateEntity(newp);
        
        System.out.println(pcd.displayEntities());
        
        pcd.deleteEntity(p);
        
        */
    }

}