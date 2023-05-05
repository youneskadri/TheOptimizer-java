/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author klair
 */
  class MyListCell extends ListCell<MyData> {
        private AnchorPane myAnchorPane;
        private Label sujetLabel;
        private Label descriptionLabel;

        public MyListCell() {
            super();
            myAnchorPane = new AnchorPane();
             sujetLabel = new Label();
            descriptionLabel = new Label();
            myAnchorPane.getChildren().addAll(sujetLabel, descriptionLabel);

            AnchorPane.setLeftAnchor(sujetLabel, 10.0);
            AnchorPane.setTopAnchor(sujetLabel, 0.0);
            
             AnchorPane.setLeftAnchor(descriptionLabel, 130.0);
            AnchorPane.setTopAnchor(descriptionLabel, 0.0);
        }
        
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                sujetLabel.setText(item.getSujet());
                descriptionLabel.setText(item.getDescription());  
                setGraphic(myAnchorPane);
            }
        }
  }