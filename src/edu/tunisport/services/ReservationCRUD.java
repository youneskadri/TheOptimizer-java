/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;



import edu.tunisport.entities.Billet;
import edu.tunisport.entities.Reservation;
import edu.tunisport.interfaces.InterfaceReservation;
import edu.tunisport.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ReservationCRUD implements InterfaceReservation<Reservation>{
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ReservationCRUD() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void add(Reservation r) throws SQLException {
        Statement st = con.createStatement();
        String req =" insert into reservation (id, user_id , match_f_id,date_resevation, nombre_billet, etat)"+ "values ('" +r.getId()+ "' , '"+r.getUser_id()+" ' ,'"+r.getMatch_id()+" ' ,'"+r.getDate()+" ' , '"+r.getNbr_billet() + " ' , '"+r.getEtat() +"')"; 
        st.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            int reservationId = rs.getInt(1);
            String matchReq = "SELECT prix, nb_billet_reserve FROM match_f WHERE id = " + r.getMatch_id();
            ResultSet matchRs = st.executeQuery(matchReq);
            if (matchRs.next()) {
                int prix = matchRs.getInt("prix");
                int reservedTickets = matchRs.getInt("nb_billet_reserve");
                int nbrBillets = r.getNbr_billet();
                int newReservedTickets = reservedTickets + nbrBillets;
                String updateMatchReq = "UPDATE match_f SET nb_billet_reserve = " + newReservedTickets + " WHERE id = " + r.getMatch_id();
                st.executeUpdate(updateMatchReq);
                for (int i = 0; i < r.getNbr_billet(); i++) {
                    Billet b = new Billet();
                    b.setReservation_id(reservationId); 
                    b.setPrix(prix); 

                    String billetReq = "INSERT INTO billet (reservation_id, prix) "
                            + "VALUES ('" + b.getReservation_id() + "', '" + b.getPrix() + "')";
                    st.executeUpdate(billetReq);
                }
                System.out.println("Reservation et billets ajoutés avec succès");
            } else {
                System.out.println("Match introuvable");
            }
        
        }
    }    

    @Override
    public ObservableList<Reservation> read() throws SQLException {
        ObservableList<Reservation> ls = FXCollections.observableArrayList();
    
    Statement st = con.createStatement();
    String req = "select reservation.id, user.username as user_nom,reservation.match_f_id, reservation.date_resevation, reservation.nombre_billet, reservation.etat FROM reservation JOIN user ON reservation.user_id = user.id ";
   
    ResultSet rs = st.executeQuery(req);
   
    
     
    while(rs.next() ){
        
        int id = rs.getInt("id");
        String user = rs.getString("user_nom");
        String match = rs.getString("match_f_id");
        Date date = rs.getDate("date_resevation");
        int nbrbillet = rs.getInt("nombre_billet");
        String etat = rs.getString("etat");
        
        

        Reservation r = new Reservation(id,user,match,date,nbrbillet,etat);
        ls.add(r);
        
    }
    
    return ls;
    }
    
    public void update(Reservation r) throws SQLException {
        try {
            PreparedStatement pt = con.prepareStatement("UPDATE reservation SET user_id = '" + r.getUser_id()+ "', match_f_id = '"+r.getMatch_id()+ "', date_resevation = '"+r.getDate()+ "', nombre_billet = '" + r.getNbr_billet()+ "',etat = '" + r.getEtat()+ "' WHERE id = " + r.getId()); 
            pt.executeUpdate();
            System.out.println("Reservation modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }


    }

    
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement pt = con.prepareStatement("DELETE FROM reservation WHERE id = " + id);
            
            pt.executeUpdate();
            System.out.println("reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    
    
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> ls = FXCollections.observableArrayList();
    
    Statement st = con.createStatement();
    String req = "select reservation.id, user.username as user_nom,reservation.match_f_id, reservation.date_resevation, reservation.nombre_billet, reservation.etat FROM reservation JOIN user ON reservation.user_id = user.id ";
   
    ResultSet rs = st.executeQuery(req);
   
    
     
    while(rs.next() ){
        
        int id = rs.getInt("id");
        String user = rs.getString("user_nom");
        String match = rs.getString("match_f_id");
        Date date = rs.getDate("date_resevation");
        int nbrbillet = rs.getInt("nombre_billet");
        String etat = rs.getString("etat");
        
        

        Reservation r = new Reservation(id,user,match,date,nbrbillet,etat);
        ls.add(r);
        
    }
    
    return ls;
    }
    
    public List getReservationById(int id) throws SQLException {
    List<Reservation> ls = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        ps = con.prepareStatement("SELECT * FROM reservation WHERE id = ?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            int reservationId = rs.getInt("id");
            String userId = rs.getString("user_id");
            String matchId = rs.getString("match_f_id");
            Date reservationDate = rs.getDate("date_resevation");
            int numberOfTickets = rs.getInt("nombre_billet");
            String reservationState = rs.getString("etat");

            Reservation r = new Reservation(reservationId, userId, matchId, reservationDate, numberOfTickets, reservationState);
            ls.add(r);
        }

    } finally {
        if (rs != null) {
            rs.close();
        }

        if (ps != null) {
            ps.close();
        }
    }

    return ls;
    }
    
    public Reservation getLatestReservation() throws SQLException {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT reservation.id, user.username as user_nom,reservation.match_f_id, reservation.date_resevation, reservation.nombre_billet, reservation.etat FROM reservation JOIN user ON reservation.user_id = user.id ORDER BY reservation.id DESC LIMIT 1");
    Reservation r = null;
    if (rs.next()) {
        r = new Reservation(rs.getInt("id"), rs.getString("user_nom"), rs.getString("match_f_id"), rs.getDate("date_resevation"), rs.getInt("nombre_billet"), rs.getString("etat"));
    }
    rs.close();
    stmt.close();
    return r;
    }
    
    
    public List<Reservation> getReservationsByUser(String userId) throws SQLException {
    
    List<Reservation> reservations = new ArrayList<>();
    String query = "SELECT * FROM reservation WHERE user_id = ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, userId);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        int reservationId = rs.getInt("id");
        String matchId = rs.getString("match_f_id");
        Date date = rs.getDate("date_resevation");
        int nbr_billet = rs.getInt("nombre_billet");
        String etat = rs.getString("etat");
        Reservation reservation = new Reservation(reservationId, userId, matchId, date, nbr_billet, etat);
        reservations.add(reservation);
    }
    return reservations;
    }
    
    
    public double calculateReservationPrice(List<Reservation> reservations) throws SQLException {
        MatchFCRUD matchCRUD = new MatchFCRUD();
        double totalPrice = 3;
        return totalPrice;
}








    
    
}
