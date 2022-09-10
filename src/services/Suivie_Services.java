/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Isuivi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrainee;
import model.Suivi;
import model.Utilisateur;
import util.MaConnexion;

/**
 *
 * @author GIGABYTE
 */
public class Suivie_Services implements Isuivi {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterSuivi(Suivi s) {
        String req = "INSERT INTO `suivi`(`nom`, `prenom`, `age`, `poids`, `taille`, `date_suivi`, `imc`, `fk_idUser_Suivi`, `id_coach`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getNomE());
            ps.setString(2, s.getPrenomE());
            ps.setInt(3, s.getAge());
            ps.setInt(4, s.getPoidsActuelle());
            ps.setInt(5, s.getTaille());
            ps.setDate(6, s.getDateSuivi());
            ps.setDouble(7, s.getImc());
            ps.setInt(8, s.getfk_idUser_Suivi());
            ps.setInt(9, s.getId_coach());
            ps.executeUpdate();
            System.out.println("PS : Suivi ajoutée avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerSuivi(Suivi s) {
        String req = "DELETE FROM `suivi` WHERE `nom`= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getNomE());
            ps.executeUpdate();
            System.out.println("PS : Personne supprimé avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierSuivi(Suivi s) {

    }

    @Override
    public Suivi afficherEntrainerList() {

        List<Suivi> suivis = new ArrayList<>();
         Suivi s = new Suivi();
        String req = "SELECT `nom`,`prenom` FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
                s.setId_coach(rs.getInt(12));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
                if (suivis != null && !suivis.isEmpty()) {
                    s = suivis.get(suivis.size()-1);
                    
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public List<Suivi> afficherEntrainer() {

        List<Suivi> suivis = new ArrayList<>();
        String req = "SELECT * FROM `suivi`";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
                s.setAge(rs.getInt("age"));
                s.setTaille(rs.getInt("taille"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivis;
    }

    @Override
    public void afficherChart(String nom) {

        List<Suivi> suivis = new ArrayList<>();
        String req = "SELECT poids,date FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
        //return suivis;
    }

    @Override
    public List<Suivi> afficherselondate() {

        List<Suivi> suivis = new ArrayList<>();
        String req = "SELECT * FROM `suivi`";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Suivi s = new Suivi();
//                p.setId(rs.getInt("id"));
                s.setNomE(rs.getString("nom"));
                s.setPoidsActuelle(rs.getInt("poids"));
                s.setDateSuivi(rs.getDate("date_suivi"));
//                p.setNom(rs.getString(2));
//                p.setPrenom(rs.getString("prenom"));
                s.setPrenomE(rs.getString("prenom"));
//                p.setCin(rs.getInt(4));
//                p.setAge(rs.getInt(5));
                suivis.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Suivie_Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suivis;
    }


    @Override
    public Suivi queryById(int id) {
        
        List<Suivi> suivis = new ArrayList<>();
        
        Suivi date_suivi = new Suivi();
        Suivi taille = new Suivi();
        Suivi pods = new Suivi();
        String req = "SELECT * FROM suivi";
        String req2 = "SELECT * FROM suivi WHERE fk_idUser_Suivi = ? ORDER BY fk_idUser_Suivi DESC";
        try {
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                //res.first();
                
//                date_suivi.setId(res.getInt(1));
//                date_suivi.setNomE(res.getString(2));
//                date_suivi.setPrenomE(res.getString(3));
                date_suivi.setPoidsActuelle(res.getInt(5));
                date_suivi.setDateSuivi(res.getDate(7));
//                date_suivi.setAge(res.getInt(4));
                date_suivi.setTaille(res.getInt(6));
                date_suivi.setImc(res.getDouble(8));
                
//                date_suivi.setfk_idUser_Suivi(res.getInt(8));
//                int retval=suivis.size();
                //System.out.println(retval);
                suivis.add(date_suivi);
                if (suivis != null && !suivis.isEmpty()) {
                    date_suivi = suivis.get(suivis.size()-1);
                    
                }
                
                
                //System.out.println(date_suivi);
                
                //ystem.out.println(date_suivi);
               

                
                
                //ps.close();

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
        return date_suivi;
    }
   

    @Override
    public List<Suivi> queryByidCoach(int id) {

     return null;
    }
    
    
    
    
    
    
}
