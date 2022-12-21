/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICategories;
import interfaces.IProduits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Categories;
import model.Produit;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class Produitservices implements IProduits{
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void insertproduit(Produit st) {
         String requete = "INSERT INTO `produits` (`nom_Produit`, `prix`, `Description`, `categories`, `quantite`, `image`) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, st.getNom_produit());
            ps.setFloat(2 , st.getPrix());
            ps.setString(3, st.getDescription());
            ps.setInt(4, st.getCat().getId());
            ps.setInt(5, st.getQuantite());
            ps.setString(6, st.getImage());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(Produitservices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
 
    @Override
    public ObservableList<Produit> DisplayAllproduit() {
        
        ObservableList<Produit> listedepots = FXCollections.observableArrayList();
        String requete = "select * from produits";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ICategories deptdao =Categorieservices.getInstance();

            while (resultat.next()) {
                Produit pr = new Produit();
                pr.setId(resultat.getInt(1));
                pr.setNom_produit(resultat.getString(2));
              
                pr.setPrix(resultat.getInt(3));
                pr.setDescription(resultat.getString("Description"));
                pr.setCat(deptdao.findcatById(resultat.getInt(5)));
                pr.setQuantite(resultat.getInt(6));
                pr.setImage(resultat.getString(7));

                listedepots.add(pr);
            }
            return listedepots;
        } catch (SQLException ex) {
           Logger.getLogger(Produitservices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }
   
        }
    
    






