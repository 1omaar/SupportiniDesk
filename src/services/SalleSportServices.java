/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ISalleSport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.SalleSport;
import util.MaConnexion;

/**
 *
 * @author HSOUNA
 */
public class SalleSportServices implements ISalleSport {
    
  Connection cnx = MaConnexion.getInstance().getCnx();
   
    @Override
    public List<SalleSport> affichage() {
//    String req =  "SELECT * FROM `salledessport` where `fk_idUser` = idUser";
       String req = "SELECT * FROM `salledessport`" ;
        PreparedStatement ps;
        
       try {
           ps = cnx.prepareStatement(req);
           ResultSet res = ps.executeQuery();
           List <SalleSport> listSalleSport = new ArrayList<>() ;
           
           while (res.next()){
           
           SalleSport st = new SalleSport();
           st.setId(res.getInt(1));
           st.setNomSalle(res.getString(2));
           st.setNumTel(res.getInt(3));
           st.setVille(res.getString(4));
           st.setRue(res.getString(5));
           st.setCodePostal(res.getString(6));
           st.setDescription(res.getString(7));
           st.setPrix(res.getFloat(8));
           st.setDuration(res.getString(9));
           st.setFk_idUser(res.getInt(10));
           st.setImageVitrine(res.getString(11));
           
          listSalleSport.add(st);
           
           }
           return listSalleSport;
       } 
       
       
       catch (SQLException ex) {
           Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }

    }

    @Override
    public void ajouterSalleSport(SalleSport s) {
        String req = " INSERT INTO salledessport( nomSalle, numTel, ville, rue, codePostal, description, duration, prix,imageVitrine,fk_idUser) VALUES  (?, ?,?, ?, ?,?,?,?,?,?)";
        try {
            java.sql.PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, s.getNomSalle());
            ps.setInt(2, s.getNumTel());
            ps.setString(3, s.getVille());
            ps.setString(4, s.getRue());
            ps.setString(5, s.getCodePostal());
            ps.setString(6,s.getDescription());
            ps.setString(7,s.getDuration());
            ps.setFloat(8, s.getPrix());
            ps.setString(9, s.getImageVitrine());
            ps.setInt(10, s.getFk_idUser());
          
            ps.executeUpdate();
            System.out.println("PS :Salle ajoutée avec succés!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Salle De Sport AJOUTER");

            alert.setHeaderText("AJOUTER AVEC SUCCES");
            alert.setContentText("Salle De Sport AJOUTER AVEC SUCCES!");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SalleSport> affichageByIdPss(int idUser) {
      String req =  "SELECT * FROM `salledessport` where `fk_idUser` = ?";
       PreparedStatement ps;
      try {
          ps = cnx.prepareStatement(req);
          ps.setInt(1, idUser);
            ResultSet res = ps.executeQuery();
           List <SalleSport> listSalleSport = new ArrayList<>() ;
           while (res.next()){
         
           SalleSport st = new SalleSport();
           st.setId(res.getInt(1));
           st.setNomSalle(res.getString(2));
           st.setNumTel(res.getInt(3));
           st.setVille(res.getString(4));
           st.setRue(res.getString(5));
           st.setCodePostal(res.getString(6));
           st.setDescription(res.getString(7));
           st.setPrix(res.getFloat(8));
           st.setDuration(res.getString(9));
           st.setFk_idUser(res.getInt(10));
           st.setImageVitrine(res.getString(11));
           
          listSalleSport.add(st);
           
           }
           return listSalleSport;
       
      } catch (SQLException ex) {
          Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
         
    
    }
}
