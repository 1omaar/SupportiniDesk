/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Produit;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ICommande {
    public void AddCommande(commande c);
    public List<commande>AfficherCommande() ;
    public void DeleteCommande(commande c);
    public void ModifyCommande(commande c);
    public commande getCommandeById(int id);
    
    
    
}
