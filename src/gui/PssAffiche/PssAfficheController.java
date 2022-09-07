/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.PssAffiche;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.SalleSport;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class PssAfficheController implements Initializable {

    @FXML
    private HBox itemsalle;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label prix;
    @FXML
    private Label adresse;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    
    private SalleSport Salle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void  setData (SalleSport SalleSport)   {
       
    
    this.Salle=SalleSport;
//        System.out.println(Salle);
    
        nom.setText(Salle.getNomSalle());
        prix.setText(String.valueOf(Salle.getPrix())+" DT");
        adresse.setText(Salle.getVille()+" ,"+Salle.getRue()+" "+Salle.getCodePostal());
        description.setText(Salle.getDescription());
       
    Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/imageSalleSport/"+Salle.getImageVitrine()).toURI().toString());
              image.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }


    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }
    
}
