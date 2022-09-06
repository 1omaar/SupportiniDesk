/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import gui.itemDash.ItemDashFXMLController;
import interfaces.ICoachings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Coachings;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-114
 */
public class CoachingsService implements ICoachings{

    
         Connection cnx = MaConnexion.getInstance().getCnx();
   
    private static ICoachings Coachinges;

    public static ICoachings getInstance() {
        if (Coachinges == null) {
            Coachinges = new CoachingsService();
        }
        return Coachinges;
    }
    
    
    
    @Override
    public ObservableList<Coachings> table() {
         ObservableList<Coachings> listCoaching = FXCollections.observableArrayList();
        String req = "SELECT * FROM coachings";
        try {
             java.sql.PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            {
                while (rs.next()) {
                    Coachings st = new Coachings();
                    st.setId(rs.getInt("id"));
                    st.setIdcoach(rs.getInt("idcoach"));
                    st.setTitre(rs.getString("titre"));
                    st.setDiscipline(rs.getString("discipline"));
                    st.setDescription(rs.getString("description"));
                    st.setPlaning(rs.getString("planing"));
                      st.setPrix(rs.getString("prix"));
                  st.setPrix(rs.getString("nbinscri"));
                  st.setPrix(rs.getString("nbmax"));
                    listCoaching.add(st);

                }
                return listCoaching;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
             return null ;
        }
    }

    @Override
    public void ajouterCoaching(Coachings c) {
         String req = "INSERT INTO `coachings`(`idcoach`, `titre`, `discipline`, `description`,`planing`,`prix`,`nbmax`,`nbinscri`,`image`) VALUES (?,?,?,?,?,?,?,?,?)";
//         String req = "INSERT INTO `coaching`(`idcoach`, `titre`, `discipline`, `description`,`planing`,`prix`) VALUES (?,?,?,?,?,?)";
           try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getIdcoach());/////
            ps.setString(2,c.getTitre());
            ps.setString(3, c.getDiscipline());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getPlaning());
            ps.setString(6, c.getPrix());
            ps.setInt(7, c.getNbmax());
            ps.setInt(8, c.getNbinscri());
            ps.setString(9, c.getImage());
//            
            
            
            ps.executeUpdate();
            System.out.println("PS : Coaching  ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(CoachingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerCoaching(Coachings c) {
        com.mysql.jdbc.PreparedStatement pst,ps;

        try {
            pst = (com.mysql.jdbc.PreparedStatement) cnx.prepareStatement("delete from coachings where id = ? ");
            pst.setInt(1, c.getId());
            pst.executeUpdate();
System.out.println("Coaching supprimer avec succés!");
       
           table();

        } catch (SQLException ex) {
            Logger.getLogger(ItemDashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

   
    
}
