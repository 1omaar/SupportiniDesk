/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produits;
import Exception.AuthException;
import interfaces.ILignePanier;
import interfaces.IPanier;
import interfaces.MyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.LignePanier;
import model.Panier;
import model.Produit;
import org.json.JSONException;
import services.LignePanierservices;
import services.Panierservices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ItemController implements Initializable{

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    private int idPro,idUser ;
    @FXML
    private Button Ajout;
    @Override
    public void initialize(URL location, ResourceBundle resources)  {
    Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
             idUser = Integer.parseInt(subject);
         
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Prod);
    }

    private Produit Prod;
    private MyListener myListener;

    public void setData(Produit Prod, MyListener myListener) throws URISyntaxException {
        this.Prod = Prod;
        this.myListener = myListener;
        nameLabel.setText(Prod.getNom_produit());
        priceLable.setText(Prod.getPrix()+"DT" );
        String path = Prod.getImage();
        Image aa = new Image(getClass().getResource("../uicontrolers/imagesproduits/"+Prod.getImage()).toURI().toString());
        
        img.setImage(aa);
    }

    @FXML
    private void Ajoutpanier(ActionEvent event) {
        IPanier ip =new Panierservices();
           ILignePanier il = new LignePanierservices();
           if (ip.querypanier(idUser)instanceof Panier){
              //fama panier
               System.out.println("panier");
           }else{
               Panier p = new Panier(12, idUser);
               ip.addPanier(p);
               int idPanier= ip.querypanier(idUser).getId();
               if (il.queryByIdProd(idPro)instanceof LignePanier) {
                 
                  
                  String yy= "UPDATE `ligne_panier` SET `quantité`" ;
                 
               }else{
                  // create ligne  panier 
               }
           }
    }
    
    
}
