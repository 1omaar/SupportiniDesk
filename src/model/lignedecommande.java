/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anis-PC
 */
public class lignedecommande {
   
 
    private int id;
    private commande commande;
    private Produit produit;

    public lignedecommande() {
    }

    public lignedecommande(commande commande, Produit produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public lignedecommande(int id, commande commande, Produit produit) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
    }
    

    public commande getCommande() {
        return commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}


    
