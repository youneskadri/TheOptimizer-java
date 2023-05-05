/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author kadri younes
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
   
    Media media = new Media("file:///C:/Users/kadri%20younes/Desktop/TheOptimizer-java/music/Hayya.mp3");

// create a media player
MediaPlayer mediaPlayer = new MediaPlayer(media);

// play the audio file
mediaPlayer.play();

        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Tunisport");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
