/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;


    class MyData {
        private  String sujet;
        private  String description;
        private int id ; 
        private int user_id ; 

    public int getUser_id() {
        return user_id;
    }

        

        public MyData(int id,String sujet, String description,int user_id) {
            this.id=id ; 
            this.sujet = sujet;
            this.description = description;
            this.user_id = user_id ;
        }

        public String getSujet() {
            return sujet;
        }

        public String getDescription() {
            return description;
        }

    public int getId() {
        return id;
    }


    }
