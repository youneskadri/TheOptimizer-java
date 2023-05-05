/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.tools;

import io.joshworks.restclient.http.HttpResponse;
import io.joshworks.restclient.http.Unirest;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ResultatData {
    public String getScoreData() throws  IOException {
        
        HttpResponse<String> response = Unirest.get("https://v3.football.api-sports.io/fixtures?league=202&season=2022")
                .header("x-rapidapi-key", "f2a6a1565703eccce6e57be76b805fb2")
                .header("x-rapidapi-host", "v3.football.api-sports.io")
                .asString();
        
        String responseBody = response.body();
        System.out.println(responseBody);
        return responseBody;
    }
}
