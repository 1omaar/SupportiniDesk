/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ILignePanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LignePanier;
import util.MaConnexion;

/**
 *
 * @author Anis-PC
 */
public class LignePanierservices implements ILignePanier {
Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void AjoutLignePanier() {
String req ="INSERT INTO `ligne_panier`( `Id_Panier`, `id_Produit`, `quantité`, `prix_total`) VALUES(?,?,?,?)";
            PreparedStatement ps ; 
    try {
        ps=cnx.prepareStatement(req);
       
    } catch (SQLException ex) {
        Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        
        
    }

    @Override
    public LignePanier queryByIdProd(int id) {
         String requete= "Select * From ligne_panier where id_Produit=?" ;
         PreparedStatement ps;
    try {
        ps=cnx.prepareStatement(requete);
        ps.setInt(0, id);
        ResultSet rest=ps.executeQuery();
        LignePanier lp=new LignePanier(rest.getInt(1),rest.getInt(2), rest.getInt(3), rest.getFloat(4));
        return lp;
    } catch (SQLException ex) {
        Logger.getLogger(LignePanierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null ;
    }
    
}
