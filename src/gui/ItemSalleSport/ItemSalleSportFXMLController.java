/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ItemSalleSport;

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
import javafx.scene.layout.AnchorPane;
import model.SalleSport;

/**
 * FXML Controller class
 *
 * @author HSOUNA
 */
public class ItemSalleSportFXMLController implements Initializable {

    @FXML
    private AnchorPane Itemsalle;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label adress;
    @FXML
    private Label classs;

    private SalleSport Salle;
    @FXML
    private ImageView image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    public void  setData (SalleSport SalleSport)   {
       
    
    this.Salle=SalleSport;
//        System.out.println(Salle);
    
        nom.setText(Salle.getNomSalle());
        prix.setText(String.valueOf(Salle.getPrix()));
        adress.setText(Salle.getVille()+" ,"+Salle.getRue()+" "+Salle.getCodePostal());
        classs.setText(Salle.getDescription());
       
    Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/imageSalleSport/"+Salle.getImageVitrine()).toURI().toString());
              image.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ItemSalleSportFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
}
