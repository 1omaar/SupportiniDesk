/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import interfaces.IProduits;
import interfaces.ICategories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class Categorieservices implements ICategories{
   


  Connection cnx = MaConnexion.getInstance().getCnx();
  @Override
    public void insertcat(Categories st) {
        String requete = "insert into categories (name) values (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, st.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorieservices .class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
   

   
   private static ICategories daoCategories;

    public static ICategories getInstance() {
        if (daoCategories == null) {
           daoCategories = new Categorieservices();
        }
        return daoCategories;
    }
@Override
    public Categories findcatBynom(String nom) {
      Categories cat = new Categories();
        String requete = "select * from categories where name=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                cat.setId(resultat.getInt(1));
                cat.setName(resultat.getString(2));
            }
            return cat;

        } catch (SQLException ex) {
           Logger.getLogger(Categorieservices .class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du Categories " + ex.getMessage());
            return null;
        }
    }
@Override
    public Categories findcatById(int id) {
          Categories cat = new Categories();
        String requete = "select * from categories where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                cat.setId(resultat.getInt(1));
                cat.setName(resultat.getString(2));
            }
            return cat;

        } catch (SQLException ex) {
           Logger.getLogger(Categorieservices .class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du Categories " + ex.getMessage());
            return null;
        }
    }
}


    

