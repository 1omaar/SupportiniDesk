/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.suivicoach;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import gui.suivi.histosuivi.HistoSuiviController;
import gui.suivi.suivitrainer.SuiviTrainerController;
import interfaces.ICoach;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Isuivi;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Suivi;
import model.Utilisateur;
import org.json.JSONException;
import services.CoachServices;
import services.EntraineeServices;
import services.Suivie_Services;
import services.UtilisateurServices;
import util.JWebToken;
//import supportini_app.SuiviCoach;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class SuiviCoachController implements Initializable {

    @FXML
    private ListView<Suivi> ListEntrainer;
    Isuivi iS = new Suivie_Services();
    @FXML
    private Label NomEntrainer;
    @FXML
    private Label PrenomEntrainer;
    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private LineChart<Date, Double> chart;
    @FXML
    private Button histobtn;
    @FXML
    private Label ageSuivi;
    @FXML
    private Label tailleshow;

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
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);

            
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Utilisateur> lu = new ArrayList<>();
        ObservableList<Suivi> personnes = FXCollections.observableArrayList(iS.afficherEntrainer());
        //showList.setText(String.valueOf(iS.afficherSuivi().toString());
        ListEntrainer.setItems(personnes);
        //Personne p = new Personne();

    }

    @FXML
    private void show(MouseEvent event) {
        //Suivi s1 = new Suivi();
        Suivi s = ListEntrainer.getSelectionModel().getSelectedItem();
        //Suivi s = ListEntrainer.getSelectionModel().getSelectedItems();
        NomEntrainer.setText(s.getNomE());
        PrenomEntrainer.setText(s.getPrenomE());
        DateSuivi.setText(String.valueOf(s.getDateSuivi()));
        PoidsActuelle.setText(String.valueOf(s.getPoidsActuelle()));
        ageSuivi.setText(String.valueOf(s.getAge()));
        tailleshow.setText(String.valueOf(s.getTaille()));

//        XYChart.Series<Date,Double> series = new XYChart.Series<>();
//        series.getData().add(new XYChart.Data<>(s.getDateSuivi(),Double.valueOf(s.getPoidsActuelle())));
//        chart.getData().add(series);
//        
//        series.setName("Poids");
        //series.getData().add(new XYChart.Data("Jan", 100));
//        for(int i = 1; i < 5; i++) {
//        series.getData().add(new XYChart.Data(i, DateSuivi[i]));
//        }
//        chart.getData().add(series);
    }

    @FXML
    private void gotohisto(ActionEvent event) throws IOException {
        Stage stage = (Stage) histobtn.getScene().getWindow();
        //stage.close();
        Stage primaryStage = new Stage();
        URL url = new File("C:\\Users\\GIGABYTE\\Documents\\NetBeansProjects\\Elife\\LastPulled\\Supportini-main\\Supportini-main\\src\\gui\\suivi\\histosuivi\\DatePicker.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setTitle("HistoSuivi");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void getListEntrainer(int id) {
        IUtilisateur iu = new UtilisateurServices();
        ICoach ic = new CoachServices();
        IEntrainee ie = new EntraineeServices();
        Isuivi is = new Suivie_Services();
        String nom = iu.queryUserById(id).getNom();
        String prenom = iu.queryUserById(id).getPrenom();
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
            if (idUser == is.afficherEntrainerList().getId_coach()) {

                ObservableList<Suivi> personnes = FXCollections.observableArrayList(iS.afficherEntrainer());
                //showList.setText(String.valueOf(iS.afficherSuivi().toString());
                ListEntrainer.setItems(personnes);

            }

        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
