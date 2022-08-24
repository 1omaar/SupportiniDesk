/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IEntrainee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrainee;
import util.MaConnexion;

/**
 *
 * @author Asus
 */
public class EntraineeServices implements IEntrainee{
 Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void addEntrainee(Entrainee e) {
        String req = "INSERT INTO entrainees (age,taille,poids,phone,fk_idUser) VALUES (?,?,?,?,?)";
     try {
         PreparedStatement ps = cnx.prepareStatement(req);
         ps.setInt(1, e.getAge());
         ps.setInt(2, e.getTaille());
         ps.setInt(3, e.getPoids());
         ps.setString(4,e.getPhone());
         ps.setInt(5, e.getFk_idUser());
         ps.executeUpdate();
         System.out.println("Vos Informations enregistrés avec succée");
         ps.close();
     } catch (SQLException ex) {
         Logger.getLogger(EntraineeServices.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
}
