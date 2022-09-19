
package model;

import model.Panier;
import model.Utilisateur;

/**
 *
 * @author ASUS
 */
public class commande {
    private int ref;    
    private float prix;
    private Utilisateur user;
    private Panier panier;
    private paiement paiment;

    public paiement getPaiment() {
        return paiment;
    }

    public void setPaiment(paiement paiment) {
        this.paiment = paiment;
    }

    public commande(float prix, Utilisateur user, Panier panier, paiement paiment) {
        this.prix = prix;
        this.user = user;
        this.panier = panier;
        this.paiment = paiment;
    }
    
    
    

    public commande() {
    }

    
    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public commande(float prix, Utilisateur user, Panier panier) {
        this.prix = prix;
        this.user = user;
        this.panier = panier;
    }

    
    private int NbrProduit;

    public int getNbrProduit() {
        return NbrProduit;
    }

    public void setNbrProduit(int NbrFormation) {
        this.NbrProduit = NbrProduit;
    }
    
    
    
}
