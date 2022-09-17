/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.RRegime;

import Exception.AuthException;
import com.mysql.jdbc.PreparedStatement;
import gui.AffichCoaching.ListCoachings;
import interfaces.IRegime;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import services.RegimeService;
import util.JWebToken;
import model.Regime;
import util.Notification;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class AjouterRegimeController implements Initializable {

    @FXML
    private TextField TxtNom;
    private TextField TxtIMC;
    @FXML
    private CheckBox vegetarien;
    @FXML
    private CheckBox vegan;
    @FXML
    private CheckBox omnivore;
    @FXML
    private Button BtnPublier;
    private TextArea TxtDescription;
  private int idRole , idUser ; 
  
  
  
  
   
      PreparedStatement pst,ps;
    @FXML
    private TextField TxtKg;
    @FXML
    private TextArea TxtPtDej;
    @FXML
    private TextArea TxtDinner;
    @FXML
    private TextArea TxtDej;
    /**
     * Initializes the controller class.
     */
  
    public void initialize(URL url, ResourceBundle rb) {
       //recieve the bearer token
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
               if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole= Integer.parseInt(audience);
                 idUser =        Integer.parseInt(subject);
                   System.out.println(idUser);
                
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
        }
    }    

    @FXML
    private void Publier(ActionEvent event) {
         IRegime Rg = new RegimeService();
         Regime r = new Regime() ; 
         r.setIdcoach(idUser);
          r.setNom(TxtNom.getText());
          //////////////////////////////////
          if (!(vegetarien.isSelected() || vegan.isSelected() || omnivore.isSelected() )) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("choix");

            alert.setHeaderText("choix");
            alert.setContentText("il faut choisir un type");

            alert.showAndWait();
        }
    
           if (vegetarien.isSelected()) {
            r.setType("vegetarien");
        }
          
              if (vegan.isSelected()) {
            r.setType("vegan");
        }
          
                if (omnivore.isSelected()) {
            r.setType("omnivore");
        }
         
          
          ///////////////////////////////////////
          r.setNbkg(Float.valueOf(TxtKg.getText()));
        r.setPetitdej(TxtPtDej.getText());
        r.setDej(TxtDej.getText());
        r.setDinner(TxtDinner.getText());
          Rg.ajouterRegime(r);
         Notification.notificationSuccess("REGIME AJOUTER AVEC SUCCES", "Merci pour voter contribution ");
         
    }
    
}
