/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.infoTrainee;

import gui.choice_profil.ChoiceProfilFXMLController;
import gui.login.LoginFXMLController;
import interfaces.IAuthentification;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Entrainee;
import model.Utilisateur;
import org.json.JSONException;
import services.AuthServices;
import services.EntraineeServices;
import services.UtilisateurServices;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InfoTraineeFXMLController implements Initializable {

    @FXML
    private TextField ageEnt;
    @FXML
    private TextField tailleEnt;
    @FXML
    private TextField poidsEnt;
    private TextField numPhone;
    @FXML
    private Button confEntInfo;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private Label ageValid;
    private Label phoneValid;
    @FXML
    private Label poidsValid;
    @FXML
    private Label tailleValid;
    @FXML
    private Label sexeValid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void registerInfoAdditionnel(ActionEvent event) throws JSONException, InvalidKeyException {
        clear();
        if (!Validation.validationInteger(ageEnt, ageValid)) {
            return;
        }

        if (!Validation.validationInteger(tailleEnt, tailleValid)) {
            return;
        }
        if (tailleEnt.getText().length() != 3) {
            tailleValid.setText("Taille doit se composer de 3 chiffres");
            return;
        }
        if (!Validation.validationInteger(poidsEnt, poidsValid)) {
            return;
        }

        if (!(femme.isSelected() || homme.isSelected())) {
            sexeValid.setText("Ajouter votre sexe !!");
            return;
        }
        Preferences userPreferences = Preferences.userRoot();
        String idUser = userPreferences.get("x_id_user", "root");
        IEntrainee ie = new EntraineeServices();
        IUtilisateur iu = new UtilisateurServices();
        IAuthentification ia = new AuthServices();
        LoginFXMLController login = new LoginFXMLController();
        String pwd = userPreferences.get("pwd", "root");
        if (homme.isSelected()) {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), "homme");
            ie.addEntrainee(ent);
            Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
            Utilisateur currentUser = ia.login(newUser.getEmail(), newUser.getPassword(), confEntInfo);
            login.generateCurrentUserJwt(currentUser);
            String prenom = currentUser.getPrenom().substring(0, 1).toUpperCase() + currentUser.getPrenom().substring(1);
            Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue, " + prenom);

        } else {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), "femme");
            ie.addEntrainee(ent);
            Utilisateur newUser = iu.queryUserById(Integer.parseInt(idUser));
            Utilisateur currentUser = ia.login(newUser.getEmail(), newUser.getPassword(), confEntInfo);
            login.generateCurrentUserJwt(currentUser);
            String prenom = currentUser.getPrenom().substring(0, 1).toUpperCase() + currentUser.getPrenom().substring(1);
            Notification.notificationSuccess("INSCRIPTION AVEC SUCCES", "Bienvenue, " + prenom);

        }

    }

    private void redirectToDashboard(ActionEvent event, Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../dashboard/DashboardFXML.fxml"));
        Scene scene = new Scene(root);
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void clear() {

        ageValid.setText("");

        tailleValid.setText("");

        poidsValid.setText("");

        sexeValid.setText("");
    }
}
