
package services;
import model.LignePanier;
import model.Panier;
import util.MaConnexion;
import interfaces.IPanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panierservices  implements  IPanier{
Connection cnx=MaConnexion.getInstance().getCnx();
    
    
    
    public int update(Panier p,int commandee) {
        try {   
        
        String requeteInsert = "update panier set prix= "+p.getPrix()+", commendé=  "+commandee+" where id= "+p.getId();
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
        return pre.executeUpdate();

        

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    public int ajouter(Panier p) {
        try {   
        
        String requeteInsert = "INSERT INTO `panier` ( `prix`, `id_user`, `Id_Lpanier`) VALUES (?,?,?)" ;
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
       int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("La création de l'utilisateur a échoué, aucune ligne n'est affectée.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Échec de la création de l'utilisateur, aucun ID obtenu.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    public int ajouterLignePanier(LignePanier p) {
        try {   
        
        String requeteInsert = "INSERT INTO ligne_panier(id_Panier,id_Produit) VALUES (?,?)";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
         pre.setInt(1, p.getPanier().getId());
         pre.setInt(2, p.getProduit().getId());

         
        
        int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("La création de l'utilisateur a échoué, aucune ligne n'est affectée.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Échec de la création de l'utilisateur, aucun ID obtenu.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    public int deleteLignePanier(int idpLigne) {
        try {   
        
        String requeteInsert = "DELETE FROM ligne_panier where id_Lpanier="+idpLigne;
        PreparedStatement pre=cnx.prepareStatement(requeteInsert);
     
         return pre.executeUpdate();


     } catch (SQLException ex) {
         
         System.out.println("erruer suppression");
     }
        return 0;
    }
    
    
    public int deletePanier(int id) {
        try {   
        
        String requeteInsert = "DELETE FROM panier where id="+id;
        PreparedStatement pre=cnx.prepareStatement(requeteInsert);
     
         return pre.executeUpdate();


     } catch (SQLException ex) {
         
         System.out.println("erruer suppression");
     }
        return 0;
    }
    
    

    

    
    
    ///////////
    
    List<Panier>list = new ArrayList<Panier>();
    @Override
    public List<Panier> getAllPanier() {
    
        Panier panier = new Panier();
                 String query="select* from `panier`";

        Statement stm;
    try {
        stm = cnx.createStatement();
        ResultSet rst=stm.executeQuery(query);
        while(rst.next()){
            panier.setId(rst.getInt("id"));
//            panier.setId_Produit(rst.getInt("id_Produit"));
            list.add(panier);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return list;
    
    }

    @Override
    public Panier querypanier(int idUser) {
      String req= "Select * from Panier where id_User=?" ;
      PreparedStatement ps;
    try {
        ps=cnx.prepareStatement(req);
        ps.setInt(1, idUser);
        ResultSet res = ps.executeQuery();
        res.first();
        Panier p = new Panier();
        p.setId(res.getInt("id"));
        
        p.setPrix(res.getInt("prix"));
        p.setId_user(res.getInt("id_user"));
        return p;
    } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }

    @Override
    public void addPanier(Panier p) {
       String req = "INSERT INTO `panier`(`prix`, `id_user`) VALUES (?,?)";
       PreparedStatement ps ; 
    try {
        ps=cnx.prepareStatement(req);
        ps.setInt(1, 12);
        ps.setInt(2, p.getId_user());
    } catch (SQLException ex) {
        Logger.getLogger(Panierservices.class.getName()).log(Level.SEVERE, null, ex);
    }
    }   
    
    
    
}

    

