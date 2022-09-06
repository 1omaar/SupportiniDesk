/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IAuthentification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import model.Utilisateur;
import org.json.JSONArray;
import util.JWebToken;
import util.MaConnexion;
import util.Notification;
import util.Validation;

/**
 *
 * @author Asus
 */
public class AuthServices implements IAuthentification {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public Utilisateur login(String email, String pwd) {
        String req = "SELECT * FROM utilisateurs WHERE email = ? AND password = ?";
        try {
            String hachePwd = Validation.hachePassword(pwd);

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
           ps.setString(2, hachePwd);
            ResultSet res = ps.executeQuery();
            Utilisateur existUser = new Utilisateur();
            res.first();
            existUser.setId(res.getInt(1));
            existUser.setNom(res.getString(2));
            existUser.setPrenom(res.getString(3));
            existUser.setCin(res.getString(4));
            existUser.setEmail(res.getString(5));
            existUser.setPassword(res.getString(6));
            existUser.setIdRole(res.getInt(7));
            ps.close();
//           
//          if (existUser.getPassword().equals(hachePwd)){
              
            return existUser;
//          }else{
//            Notification.notificationError("DESOLE", "Mot de Passe incorrect !!");
//          }
        } catch (Exception ex) {
             Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
            Notification.notificationError("DESOLE", "E-mail incorrect !!");
        }
return null ;
    }

}
