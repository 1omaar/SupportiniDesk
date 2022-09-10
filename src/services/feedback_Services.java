/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Ifeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Coach;
import model.Feedback;
import util.MaConnexion;

/**
 *
 * @author GIGABYTE
 */
public class feedback_Services implements Ifeedback{
Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouterfeedback(Feedback feedback) {
        String req = "INSERT INTO `feedback`(`feedback`, `id_suivi`, `id_user`, `id_coach`, `id_entrainee`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, feedback.getFeedback());
            ps.setInt(2, feedback.getId_suivi());
            ps.setInt(3, feedback.getId_user_feedback());
            ps.setInt(4, feedback.getId_coach_feedback());
            ps.setInt(5, feedback.getId_entrainee());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
       

    @Override
    public void supprimerFeedback() {
        
    }
       

    @Override
    public void modifierFeedback() {
       
    }

    @Override
    public void afficherfeedback() {

    }
    
}
