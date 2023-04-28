package com.tunisport.test;


import com.tunisport.entities.Category_hebergement;
import com.tunisport.entities.Category_transport;
import com.tunisport.entities.Hebergement;
import com.tunisport.entities.Transport;
import com.tunisport.entities.localisation;
import com.tunisport.services.Category_hebergementService;
import com.tunisport.services.Category_transportService;
import com.tunisport.services.HebergementService;
import com.tunisport.services.LocalisationService;
import com.tunisport.services.TransportService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kadri younes
 */
public class tunisport {
     public static void main(String[] args) {
         LocalisationService ls = new LocalisationService();
         Category_hebergementService ps = new Category_hebergementService();
        Category_hebergement p = new Category_hebergement(2,"st");
       // Category_hebergementService ps = new Category_hebergementService();
         Category_transport t = new Category_transport(1,"buuuus");
        Category_transportService ts = new Category_transportService();
        localisation l = new localisation(18,"mahdia");
       // LocalisationService ls = new LocalisationService();
         Hebergement H = new Hebergement(29,"png","noerg","deschebergement",ls.read(18),p);
        HebergementService Hs = new HebergementService();
        Transport a = new Transport(1,"image","deschebergement",t);
        TransportService as = new TransportService();
     Hs.ajouter(H);
     // Hs.supprimer(H);
        // System.out.println(ls.read(1));
         System.out.println(ts.read(1));
     // System.out.println( Hs.afficher());
        
    }
     
}
