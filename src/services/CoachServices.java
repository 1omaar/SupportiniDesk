/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICoach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coach;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class CoachServices implements ICoach{
   Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addCoach(Coach c) {
        String req = "INSERT INTO coachs (specialite , fk_idUser, fk_idPlaning) VALUES (?,?,?)";
       try {
           PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, c.getSpecialite());
           ps.setInt(2, c.getIdUser());
           ps.setInt(3, 0);
           ps.executeUpdate();
           System.out.println("Inscription avec succée");
           ps.close();
       } catch (SQLException ex) {
           System.err.println(ex);
       }
    }
   
}
