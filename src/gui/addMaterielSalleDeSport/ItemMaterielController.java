/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addMaterielSalleDeSport;

import gui.PssAffiche.PssAfficheController;
import interfaces.Imateriel;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MaterielSalle;
import services.MaterielServices;
import util.Notification;


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
    @FXML
    private Button supprimer;

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
        nomMateriel.setText("Nom: "+Materiel.getNomMaterial());
        Specialite.setText("Type Sport:\n"+Materiel.getSpecialite());
        descreption.setText("Desciption:\n"+Materiel.getDescription());
        Quantite.setText("Quantité:\n"+String.valueOf(Materiel.getQuantite()));
  
         
   Image img;
        try {
            img = new Image(getClass().getResource("../uicontrolers/materiel/" + Materiel.getImageVitrine()).toURI().toString());
      
         
            image.setImage(img);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
    

    @FXML
    private void supprimerMateriel(ActionEvent event) throws IOException {
         Imateriel iMateriel = new MaterielServices();
        iMateriel.supprimerMateriel(Materiel);
        
          Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
           Notification.notificationSuccess("MATERIEL SUPPRIMER AVEC SUCCESS", "Merci");
    }
    
}
