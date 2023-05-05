/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import edu.tunisport.entities.MatchF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.tunisport.tools.MyConnection;
import edu.tunisport.interfaces.InterfaceMatch;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MatchFCRUD implements InterfaceMatch<MatchF>{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    
    public MatchFCRUD() {
        con = MyConnection.getInstance().getCnx();
        
    }

 @Override
     public void add(MatchF m) throws SQLException{
        
        Statement st = con.createStatement();
          String req =" insert into match_f (id, equipe_a_id ,  equipe_b_id, date_match,heure_deb_m,heurefin_m, type_match_id, stade_id, tournoi_id, resultat_a, resultat_b, prix, image, image2, nb_billet_total, nb_billet_reserve)"+ "values ('" +m.getId()+ "' , '"+m.getEquipeA()+" ' ,'"+m.getEquipeB()+" ' ,'"+m.getDate()+" ' ,'"+m.getHeureDeb()+" ' ,'"+m.getHeureFin()+" ' , '"+m.getType() + " ' , '"+m.getStade() + " ' , '"+m.getTournoi() + " ' , '"+m.getResultatA() + " ' , '"+m.getResultatB() +  "' , '"+m.getPrix()+  "' , '"+m.getImage()+  "' , '"+m.getImage2()+  "' , '"+m.getNbrBilletTotal()+  "' , '"+m.getNbrBilletReserve()+"')"; 
    st.executeUpdate(req);
    System.out.println("match ajouté");

    }

    @Override
    public ObservableList<MatchF> read() throws SQLException{
    ObservableList<MatchF> ls = FXCollections.observableArrayList();
    
    Statement st = con.createStatement();
    String req = "select match_f.id, eqA.nom as equipe_a_nom, eqB.nom as equipe_b_nom, match_f.date_match, match_f.heure_deb_m, match_f.heurefin_m, type_match.nom as type_match_nom, stade.nom as stade_nom, tournoi.nom as tournoi_nom, match_f.resultat_a, match_f.resultat_b, match_f.prix, match_f.image, match_f.image2, match_f.nb_billet_total, match_f.nb_billet_reserve FROM match_f JOIN equipe eqA ON match_f.equipe_a_id = eqA.id JOIN equipe eqB ON match_f.equipe_b_id = eqB.id JOIN type_match ON match_f.type_match_id = type_match.id JOIN stade ON match_f.stade_id = stade.id JOIN tournoi ON match_f.tournoi_id = tournoi.id ";
   
    ResultSet rs = st.executeQuery(req);
    
    
     
    while(rs.next() ){
        
        int id = rs.getInt("id");
        String equipeA = rs.getString("equipe_a_nom");
        String equipeB = rs.getString("equipe_b_nom");
        Date date = rs.getDate("date_match");
        Time heureDeb = rs.getTime("heure_deb_m");
        Time heureFin = rs.getTime("heurefin_m");
        String type = rs.getString("type_match_nom");
        String stade = rs.getString("stade_nom");
        String tournoi = rs.getString("tournoi_nom");
        int resultatA = rs.getInt("resultat_a");
        int resultatB = rs.getInt("resultat_b");
        int prix = rs.getInt("prix");
        String image = rs.getString("image");
        String image2 = rs.getString("image2");
        int nbbbtotal = rs.getInt("nb_billet_total");
        int nbbreserve = rs.getInt("nb_billet_reserve");
        

        MatchF m = new MatchF(id, equipeA ,  equipeB, date, heureDeb, heureFin ,type, stade, tournoi, resultatA, resultatB, prix, image, image2,nbbbtotal,nbbreserve );
        ls.add(m);
        
    }
    
    return ls;

    }

   
    public void update(MatchF m) throws SQLException {
        try {
            PreparedStatement pt = con.prepareStatement("UPDATE match_f SET equipe_a_id = '" + m.getEquipeA()+ "', equipe_b_id = '"+m.getEquipeB()+ "', date_match = '"+m.getDate()+ "', type_match_id = '" + m.getType()+ "', stade_id = '" + m.getStade()+ "', tournoi_id = '" + m.getTournoi()+ "', resultat_a = '" + m.getResultatA()+ "', resultat_b = '" + m.getResultatB()+ "', prix = '" + m.getPrix()+ "', image = '" + m.getImage()+ "', image2 = '" + m.getImage2() + "' WHERE id = " + m.getId()); 
            pt.executeUpdate();
            System.out.println("match modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement pt = con.prepareStatement("DELETE FROM match_f WHERE id = " + id);
            
            pt.executeUpdate();
            System.out.println("match supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public List getAllMatches() throws SQLException {
        List<MatchF> ls = FXCollections.observableArrayList();
        Statement st = con.createStatement();
        String req = "select match_f.id, eqA.nom as equipe_a_nom, eqB.nom as equipe_b_nom, match_f.date_match, match_f.heure_deb_m, match_f.heurefin_m, type_match.nom as type_match_nom, stade.nom as stade_nom, tournoi.nom as tournoi_nom, match_f.resultat_a, match_f.resultat_b, match_f.prix, match_f.image, match_f.image2, match_f.nb_billet_total, match_f.nb_billet_reserve FROM match_f JOIN equipe eqA ON match_f.equipe_a_id = eqA.id JOIN equipe eqB ON match_f.equipe_b_id = eqB.id JOIN type_match ON match_f.type_match_id = type_match.id JOIN stade ON match_f.stade_id = stade.id JOIN tournoi ON match_f.tournoi_id = tournoi.id ";

        ResultSet rs = st.executeQuery(req);
        while(rs.next() ){
        
        int id = rs.getInt("id");
        String equipeA = rs.getString("equipe_a_nom");
        String equipeB = rs.getString("equipe_b_nom");
        Date date = rs.getDate("date_match");
        Time heureDeb = rs.getTime("heure_deb_m");
        Time heureFin = rs.getTime("heurefin_m");
        String type = rs.getString("type_match_nom");
        String stade = rs.getString("stade_nom");
        String tournoi = rs.getString("tournoi_nom");
        int resultatA = rs.getInt("resultat_a");
        int resultatB = rs.getInt("resultat_b");
        int prix = rs.getInt("prix");
        String image = rs.getString("image");
        String image2 = rs.getString("image2");
        int nbbbtotal = rs.getInt("nb_billet_total");
        int nbbreserve = rs.getInt("nb_billet_reserve");
        

        MatchF m = new MatchF(id, equipeA ,  equipeB,date, heureDeb, heureFin ,type, stade, tournoi, resultatA, resultatB, prix, image, image2,nbbbtotal,nbbreserve );
        ls.add(m);
        
    }
       return ls;
    }
    
    
    
    
    public MatchF getMatchById(int matchId) throws SQLException {

    MatchF match = null;
    String query = "select match_f.id, eqA.nom as equipe_a_nom, eqB.nom as equipe_b_nom, match_f.date_match, match_f.heure_deb_m, match_f.heurefin_m, type_match.nom as type_match_nom, stade.nom as stade_nom, tournoi.nom as tournoi_nom, match_f.resultat_a, match_f.resultat_b, match_f.prix, match_f.image, match_f.image2, match_f.nb_billet_total, match_f.nb_billet_reserve FROM match_f JOIN equipe eqA ON match_f.equipe_a_id = eqA.id JOIN equipe eqB ON match_f.equipe_b_id = eqB.id JOIN type_match ON match_f.type_match_id = type_match.id JOIN stade ON match_f.stade_id = stade.id JOIN tournoi ON match_f.tournoi_id = tournoi.id WHERE match_f.id=?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, matchId);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        int id = rs.getInt("id");
        String equipeA = rs.getString("equipe_a_nom");
        String equipeB = rs.getString("equipe_b_nom");
        Date date = rs.getDate("date_match");
        Time heureDeb = rs.getTime("heure_deb_m");
        Time heureFin = rs.getTime("heurefin_m");
        String type = rs.getString("type_match_nom");
        String stade = rs.getString("stade_nom");
        String tournoi = rs.getString("tournoi_nom");
        int resultatA = rs.getInt("resultat_a");
        int resultatB = rs.getInt("resultat_b");
        int prix = rs.getInt("prix");
        String image = rs.getString("image");
        String image2 = rs.getString("image2");
        int nbbbtotal = rs.getInt("nb_billet_total");
        int nbbreserve = rs.getInt("nb_billet_reserve");
        

        match = new MatchF(id, equipeA ,  equipeB,date, heureDeb, heureFin ,type, stade, tournoi, resultatA, resultatB, prix, image, image2,nbbbtotal,nbbreserve );
    }
    return match;
    }
    
    
    public List<MatchF> getFinishedMatches() throws SQLException {
    List<MatchF> matches = new ArrayList<>();
     
    String query = "select match_f.id, eqA.nom as equipe_a_nom, eqB.nom as equipe_b_nom, match_f.date_match, match_f.heure_deb_m, match_f.heurefin_m, type_match.nom as type_match_nom, stade.nom as stade_nom, tournoi.nom as tournoi_nom, match_f.resultat_a, match_f.resultat_b, match_f.prix, match_f.image, match_f.image2, match_f.nb_billet_total, match_f.nb_billet_reserve FROM match_f JOIN equipe eqA ON match_f.equipe_a_id = eqA.id JOIN equipe eqB ON match_f.equipe_b_id = eqB.id JOIN type_match ON match_f.type_match_id = type_match.id JOIN stade ON match_f.stade_id = stade.id JOIN tournoi ON match_f.tournoi_id = tournoi.id ";
    PreparedStatement stmt = con.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        int id = rs.getInt("id");
        String equipeA = rs.getString("equipe_a_nom");
        String equipeB = rs.getString("equipe_b_nom");
        Date date = rs.getDate("date_match");
        Time heureDeb = rs.getTime("heure_deb_m");
        Time heureFin = rs.getTime("heurefin_m");
        String type = rs.getString("type_match_nom");
        String stade = rs.getString("stade_nom");
        String tournoi = rs.getString("tournoi_nom");
        int resultatA = rs.getInt("resultat_a");
        int resultatB = rs.getInt("resultat_b");
        int prix = rs.getInt("prix");
        String image = rs.getString("image");
        String image2 = rs.getString("image2");
        int nbbbtotal = rs.getInt("nb_billet_total");
        int nbbreserve = rs.getInt("nb_billet_reserve");
        MatchF match = new MatchF(id, equipeA ,  equipeB,date, heureDeb, heureFin ,type, stade, tournoi, resultatA, resultatB, prix, image, image2,nbbbtotal,nbbreserve );
        LocalTime currentTime = LocalTime.now();
        LocalTime givenTime = match.getHeureFin().toLocalTime();
        if (givenTime.isAfter(currentTime)) {
            matches.add(match);
        }
    }
    return matches;
}

    
    
    

    
   
    
    

    
    
    
    
     
    
    
   
 
    
}
