/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addMaterielSalleDeSport;

import gui.PssAffiche.PssAfficheController;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.MaterielSalle;


/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class ItemMaterielController implements Initializable {

    @FXML
    private VBox itemMateriel;
    @FXML
    private Label nomMateriel;
    @FXML
    private Label Specialite;
    @FXML
    private Label Quantite;
    @FXML
    private Label descreption;
    
        private MaterielSalle Materiel;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void  setData (MaterielSalle MaterielSalle) throws URISyntaxException   {
       
    
    this.Materiel=MaterielSalle;
//        System.out.println(Materiel);
//       id.setText(String.valueOf(Materiel.getId()));
        nomMateriel.setText(Materiel.getNomMaterial());
        Specialite.setText(Materiel.getSpecialite());
        descreption.setText(Materiel.getDescription());
        Quantite.setText(String.valueOf(Materiel.getQuantite()));
     

       
   Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/imageSalleSport/" + Materiel.getImageVitrine()).toURI().toString());
            image.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
    
}
