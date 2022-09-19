package services;
import model.lignedecommande;
import model.LignePanier;
import model.Panier;
import model.commande;
import model.paiement;
import interfaces.ICommande;
import util.MaConnexion;
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
import model.Produit;
public class Commandeservices implements ICommande{
    Connection cnx;

    public Commandeservices() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    
     public int ajouter(commande c) {
        try {   
        
        String requeteInsert = "INSERT INTO commande (prix,idUser,idpanier,idpaiement) VALUES ("+c.getPrix()+", "+ c.getUser().getId()+", "+ c.getPanier().getId()+", "+ c.getPaiment().getId()+")";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
       int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    public int ajouterLigneCommande(lignedecommande lp) {
        try {   
        
        String requeteInsert = "INSERT INTO ligne_commande(idCommande,idProduit) VALUES (?,?)";
        PreparedStatement pre=cnx.prepareStatement(requeteInsert,Statement.RETURN_GENERATED_KEYS);
     
         pre.setInt(1, lp.getCommande().getRef());
         pre.setInt(2, lp.getProduit().getId());

         
        
        int affectedRows = pre.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

     } catch (SQLException ex) {
         
         System.out.println("erruer ajout");
     }
        return 0;
    }
    
    
    
//////////////////
    @Override
    public void AddCommande(commande c) {
        PreparedStatement pst ;
        
        try {
            String sql="INSERT INTO commande(nom_Produit,prix)VALUES(?,?)";
            pst=cnx.prepareStatement(sql);
           // pst.setString(1,c.getNom_produit());
            pst.setFloat(2,c.getPrix());
            
            if(pst.executeUpdate()>0) 
                System.out.println("request send successfully!!!");
            else 
                System.out.println("failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    ObservableList<commande>oblist2=FXCollections.observableArrayList();
    @Override
    public ObservableList<commande> AfficherCommande()  {
        
        try {
            Statement stm=cnx.createStatement();
            
            String query="select* from `commande`";
            ResultSet rst=stm.executeQuery(query);
            commande c=new commande();
            while(rst.next()){
                
                c.setRef(rst.getInt("ref"));
                c.setNbrProduit(rst.getInt("NbrProduit"));
                c.setPrix(rst.getInt("Prix"));
            }
            
            oblist2.add(c);   
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oblist2;
        
        
    }
    
    
    public List<commande> AfficherTousCommandes()  {
        List<commande> lista=new ArrayList<>();
        Statement stm;
        try {
            stm = cnx.createStatement();
        
        String query="SELECT DISTINCT c.ref,c.prix,p.mode_paiement, COUNT(DISTINCT lc.id_ligne) " +
"FROM commande c " +
"inner join paiement p on p.id = c.id_paiement " +
"INNER JOIN ligne_commande lc on lc.id_commande = c.ref " +
"GROUP by c.ref,c.prix,p.mode_paiement ";
        ResultSet rst=stm.executeQuery(query);
        
        while(rst.next()){
            commande c=new commande();
            c.setRef(rst.getInt("ref"));
            c.setNbrProduit(rst.getInt("NbrProduit"));
             c.setPrix(rst.getInt("Prix"));
             c.setPaiment(new paiement(rst.getString("mode_paiement")));
                 
                    
           
            lista.add(c);
            
           
        }
        } catch (SQLException ex) {
            Logger.getLogger(Commandeservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
        
        
    }
    

    @Override
    public void DeleteCommande(commande c) {
        int n2=0;
        PreparedStatement st;
        try {
            st=cnx.prepareStatement("DELETE FROM `commande` WHERE `ref`=?");
            st.setInt(1,c.getRef());
            n2=st.executeUpdate();
            if(n2>0)
                System.out.println("Supprimé");
            else 
                
                System.out.println("non_supprimé");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void ModifyCommande(commande c) {
        String sql2="UPDATE commande SET nom_Produit=?,prix=?  WHERE ref=? ";
        
        try {
            PreparedStatement pstmt=cnx.prepareStatement(sql2);
            pstmt.setInt(1, c.getNbrProduit());
            pstmt.setFloat(2,c.getPrix());
            pstmt.setInt(3, c.getRef());
           
            if(pstmt.executeUpdate()>0){
                System.out.println("Parfait commande a ete modifie avec succees ");
                
                   
            }
            else 
                System.out.println("Echec de modification");
            pstmt.close();
            
                
            
            
            
        } catch (SQLException ex) {
            System.out.println("Modify commande=="+ex.getMessage());
        }
        
        
    }
    
    @Override
    public commande getCommandeById(int ref) {
         commande c=new commande();
        
        try {
            String sql="SELECT * FROM commande WHERE ref="+ref;
            Statement stm=cnx.createStatement();
            ResultSet rst=stm.executeQuery(sql);
            while(rst.next()){
               c.setNbrProduit(rst.getInt("NbrProduit"));
                c.setPrix(rst.getInt("prix"));
               
                
            
            
            }
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
        return c;
    }
    
    public List<Produit> afficherProduitParCommande(int idcmd) {
        List<Produit> arr = new ArrayList<>();
        try {
            Statement ste = cnx.createStatement();
            String requete = "";
       
                requete = "SELECT distinct p.nom_produit,p.description,p.prix FROM Produit p INNER join ligne_commande lc on lc.idproduit=p.id where lc.idCommande="+idcmd;
            
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                String nom_produit = rs.getString("nom_produit");
                String description = rs.getString(2);
                              
                float prix = rs.getFloat(3);
               
                
Produit p=new Produit();
p.setPrix(prix);
p.setDescription(description);
p.setNom_produit(nom_produit);

                arr.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("probleme d'affichage");
        }
        return arr;
    }
}
        
