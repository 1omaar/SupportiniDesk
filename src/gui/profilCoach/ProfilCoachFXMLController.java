/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.profilCoach;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import interfaces.ICoach;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.json.JSONException;
import services.CoachServices;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfilCoachFXMLController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label typeUser;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            getImageProfil();
            getInfoCurrentUser(idUser);
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getImageProfil() throws URISyntaxException {
        Image im = new Image(getClass().getResource("../uicontrolers/user.png").toURI().toString());
        myCircle.setFill(new ImagePattern(im));
//        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }
      private void getInfoCurrentUser(int id){
           IUtilisateur iu = new UtilisateurServices();
          String nom = iu.queryUserById(id).getNom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getNom().substring(1);
        String prenom = iu.queryUserById(id).getPrenom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getPrenom().substring(1);
        nomPrenom.setText(nom + " " + prenom);
        email.setText(iu.queryUserById(id).getEmail());
        phone.setText(iu.queryUserById(id).getPhone());
        typeUser.setText("Entraineur");
        ICoach ic = new CoachServices();
        description.setText(ic.queryById(id).getSpecialite());
      }
}
