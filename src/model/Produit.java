package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Produit {
 
    private Integer id, quantite;
    private String  Description;
        private String nom_produit;
    private float prix;
    
  private Categories cat;
  private String image ;
    public Produit() {
    }

    public Produit(Integer quantite, String Description, String nom_produit, float prix, Categories cat,String image) {
        this.quantite = quantite;
        this.Description = Description;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.cat = cat;
        this.image=image;
    }

  

    public Produit(Integer id, Integer quantite, String Description, String nom, float prix, Categories cat, String image) {
        this.id = id;
        this.quantite = quantite;
        this.Description = Description;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.cat = cat;
        this.image=image;
    }

  

  
 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

   

    

    public Categories getCat() {
        return cat;
    }

    public void setCat(Categories cat) {
        this.cat = cat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantite=" + quantite + ", Description=" + Description + ", nom_produit=" + nom_produit + ", prix=" + prix + ", cat=" + cat + ", image=" + image + '}';
    }

   

   
   }
