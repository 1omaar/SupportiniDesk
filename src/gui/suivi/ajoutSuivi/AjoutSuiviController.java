/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.ajoutSuivi;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import gui.suivi.suivitrainer.SuiviTrainerController;
import interfaces.ICoach;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Isuivi;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Suivi;
import org.json.JSONException;
import services.CoachServices;
import services.EntraineeServices;
import services.Suivie_Services;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class AjoutSuiviController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private AnchorPane sceneAdd;
    @FXML
    private TextField poidsajout;
    @FXML
    private TextField tailleajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void closescene(ActionEvent event) throws URISyntaxException {

        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            Suivi suivi = new Suivi();
            IUtilisateur iu = new UtilisateurServices();
            IEntrainee ie = new EntraineeServices();
            int identrain = ie.queryById(idUser).getId();
            int age = ie.queryById(idUser).getAge();
            String nom = iu.queryUserById(idUser).getNom();
            String prenom = iu.queryUserById(idUser).getPrenom();
//        int tailletxt =Integer.parseInt(tailleajout.getText());
//        int poidtxt = Integer.parseInt(poidsajout.getText());
//            System.out.println(poidtxt);
//            System.out.println(tailletxt);
//        float taille = tailletxt/100;
//        float tailleimc = taille*taille;
//        float imc = poidtxt/tailleimc;

            Isuivi is = new Suivie_Services();
            ICoach ic = new CoachServices();
//            int idcoach = ic.queryById(idUser).getId();
            double imc = is.queryById(idUser).getImc();
            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            Suivi s = new Suivi(identrain, age, Integer.parseInt(tailleajout.getText()), Integer.parseInt(poidsajout.getText()), date, nom, prenom, imc);
//           Suivi s = new Suivi(idUser, age ,,, date, nom, prenom);
            is.ajouterSuivi(s);
            SuiviTrainerController st = new SuiviTrainerController();
            //st.refreshing(idUser);
            sceneAdd.getChildren().clear();

        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//        Stage stage = (Stage) closebtn.getScene().getWindow();
//        // do what you have to do
//        stage.close();
}
