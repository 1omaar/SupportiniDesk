/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICoach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coach;
import model.Entrainee;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class CoachServices implements ICoach {

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

    @Override
    public Coach queryById(int id) {
        String req = "SELECT * FROM coachs WHERE fk_idUser =?";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            res.first();
            Coach coach = new Coach();
            coach.setId(res.getInt(1));
            coach.setSpecialite(res.getString(2));

            coach.setIdPlanning(res.getInt(4));

            ps.close();
            return coach;

        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

}
