/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.infoTrainee;

import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Entrainee;
import services.EntraineeServices;
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
    @FXML
    private TextField numPhone;
    @FXML
    private Button confEntInfo;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private Label ageValid;
    @FXML
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
    public void registerInfoAdditionnel(ActionEvent event) {
        clear();
        if (!Validation.validationInteger(ageEnt, ageValid)) {
            return;
        }

        if (!Validation.validationInteger(tailleEnt, tailleValid)) {
            return;
        }
        if(tailleEnt.getText().length()!=3){
            tailleValid.setText("Taille doit se composer de 3 chiffres");
            return;
        }
        if (!Validation.validationInteger(poidsEnt, poidsValid)) {
            return;
        }
        if (!Validation.validationInteger(numPhone, phoneValid)) {
            return;
        }
        if(numPhone.getText().length()!=8) {
            phoneValid.setText("Num de portable doit 8 chiffres!!");
            return;
        }
        if(!(femme.isSelected()|| homme.isSelected())){
            sexeValid.setText("Ajouter votre sexe !!");
            return;
        }
        Preferences userPreferences = Preferences.userRoot();
        String idUser = userPreferences.get("x_id_user", "root");
        IEntrainee ie = new EntraineeServices();
        if (homme.isSelected()) {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), numPhone.getText(), "homme");
            ie.addEntrainee(ent);
        } else {
            Entrainee ent = new Entrainee(Integer.parseInt(idUser), Integer.parseInt(ageEnt.getText()), Integer.parseInt(tailleEnt.getText()), Integer.parseInt(poidsEnt.getText()), numPhone.getText(), "femme");
            ie.addEntrainee(ent);
        }

    }
    public void clear() {
        
        ageValid.setText("");
     
         tailleValid.setText("");
    
         poidsValid.setText("");
      
        phoneValid.setText("");
        sexeValid.setText("");
    }
}
