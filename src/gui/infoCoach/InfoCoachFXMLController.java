/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.infoCoach;

import interfaces.ICoach;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import model.Coach;
import services.CoachServices;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InfoCoachFXMLController implements Initializable {

    @FXML
    private CheckBox fitness;
    @FXML
    private CheckBox musculation;
    @FXML
    private Button confInfCoach;
    @FXML
    private CheckBox dance;
    @FXML
    private CheckBox boxing;
    @FXML
    private CheckBox karate;
    @FXML
    private CheckBox taik;
    @FXML
    private CheckBox autre;
    @FXML
    private Label validationSpec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void secondStepRegister(ActionEvent event) {
         validationSpec.setText("");
       if(!(fitness.isSelected()||musculation.isSelected()||dance.isSelected()||boxing.isSelected()||karate.isSelected()||taik.isSelected()||autre.isSelected())){
          validationSpec.setText("Précise vos spécialités sportives !!");
          return ;
       }
       String DescrSpecialite = null ;
       if(fitness.isSelected()){
           DescrSpecialite="fitness";
       }
        if (musculation.isSelected()) {
            DescrSpecialite+=", musculation";
        }
        if(dance.isSelected()){
            DescrSpecialite+=", dance";
        }
        if(boxing.isSelected()){
            DescrSpecialite+=", boxing";
        }
        if(karate.isSelected()){
            DescrSpecialite+=", karate";
        }
        if(taik.isSelected()){
            DescrSpecialite+=", taikwando";
        }
        if(autre.isSelected()){
            DescrSpecialite+=", autres...";
        }
        ICoach ic =new CoachServices();
        Preferences userPreferences = Preferences.userRoot();
        String idUser = userPreferences.get("x_id_user", "root");
        //fk id planning static will change
        Coach newCoach = new Coach(Integer.parseInt(idUser), 0, DescrSpecialite);
        ic.addCoach(newCoach);
    }
    
    
}
