package com.tunisport.gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import com.tunisport.tools.MaConnection;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;

public class GoogleMapsController implements Initializable {

    @FXML
    private WebView webView;

    private WebEngine webEngine;
    private Stage stage;
    private Scene scene;
    private Parent root;

  @Override
public void initialize(URL url, ResourceBundle rb) {
    webEngine = webView.getEngine();

    // Load the HTML file that contains the Google Maps JavaScript code
    webEngine.load(getClass().getResource("/com/tunisport/gui/map.html").toExternalForm());

    // Retrieve locations from database and add markers to map
    try {
        java.sql.Connection conn = MaConnection.getInstance().getCnx();
        java.sql.PreparedStatement stmt = conn.prepareStatement("SELECT * FROM localisation");
        java.sql.ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String lieux = rs.getString("lieux");

            // Use Geocoding API to get coordinates for the location
            String urlStr = "https://maps.googleapis.com/maps/api/geocode/json?address=" + lieux
                    + "&key=";
            URL urlObj = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonString = response.toString();

            // Extract the latitude and longitude from the JSON response
            int latIndex = jsonString.indexOf("\"lat\" :");
            if (latIndex >= 0) {
                latIndex += 8; // skip past the "\"lat\" :" string
                int commaIndex = jsonString.indexOf(",", latIndex);
                if (commaIndex >= 0) {
                    double lat = Double.parseDouble(jsonString.substring(latIndex, commaIndex));

                    int lngIndex = jsonString.indexOf("\"lng\" :");
                    if (lngIndex >= 0) {
                        lngIndex += 8; // skip past the "\"lng\" :" string
                        int endIndex = jsonString.indexOf("\n   }\n  ],", lngIndex);
                        if (endIndex >= 0) {
                            double lng = Double.parseDouble(jsonString.substring(lngIndex, endIndex));

                            // Add marker to map
                            addMarker(lat, lng, lieux);
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void addMarker(double lat, double lng, String lieux) {
    System.out.println("Adding marker for " + lieux +"("+lat + "," + lng + ")" );
    webView.getEngine().executeScript("Var marker = L.marker([" + lat + ", "+ lng + "]).addTo(map).bindPopup('" + lieux + "');");
}

    @FXML
    private void redirTransport(MouseEvent event) {
   try{
            root = FXMLLoader.load(getClass().getResource("transport.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirhebergement(MouseEvent event) {
   try{
                root = FXMLLoader.load(getClass().getResource("tunisport.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
