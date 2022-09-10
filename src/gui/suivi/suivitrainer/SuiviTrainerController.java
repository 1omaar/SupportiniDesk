/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.suivitrainer;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import interfaces.IEntrainee;
import interfaces.IUtilisateur;
import interfaces.Isuivi;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Suivi;
import org.json.JSONException;
import services.EntraineeServices;
import services.Suivi_Service_Trainer;
import services.Suivie_Services;
import services.UtilisateurServices;
import util.JWebToken;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class SuiviTrainerController implements Initializable {

    @FXML
    private Label NomEntrainer;
    @FXML
    private Label PrenomEntrainer;
    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private Label ageSuivi;
    @FXML
    private Label tailleshow;
    @FXML
    private Button histo;
    @FXML
    private Button addsuivi;
    @FXML
    private AnchorPane sceneAdd;

    Suivi s = new Suivi();

    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Label imclbl;
    @FXML
    private AnchorPane scenesuivi;

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
            getInfoCurrentUser(idUser);
            refreshing(idUser);
            //scenesuivi.getScene().getWindow().setWidth(scenesuivi.getScene().getWidth()+0.001);
            try {
                getForChart();
            } catch (SQLException ex) {
                Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getInfoCurrentUser(int id) throws URISyntaxException {
        IUtilisateur iu = new UtilisateurServices();
        String nom = iu.queryUserById(id).getNom();
        String prenom = iu.queryUserById(id).getPrenom();
        Isuivi is = new Suivie_Services();

        NomEntrainer.setText(nom);
        PrenomEntrainer.setText(prenom);
        //Isuivi is = new Suivie_Services();
        //Suivi s = new Suivi();
        IEntrainee ie = new EntraineeServices();
        if (is.queryById(id).getPoidsActuelle() != 0 || is.queryById(id).getTaille() != 0 || is.queryById(id).getDateSuivi() != null || is.queryById(id).getImc() != 0) {
            PoidsActuelle.setText(String.valueOf(is.queryById(id).getPoidsActuelle()));
            DateSuivi.setText(String.valueOf(is.queryById(id).getDateSuivi()));
            tailleshow.setText(String.valueOf(is.queryById(id).getTaille()));
            if (is.queryById(id).getImc() < 18.5) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Maigreur");
                imclbl.setStyle("-fx-text-fill: DeepSkyBlue;");
            } else if (is.queryById(id).getImc() < 25) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Normal");
                imclbl.setStyle("-fx-text-fill: #00FF7F;");
            } else if (is.queryById(id).getImc() < 30) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Suproids");
                imclbl.setStyle("-fx-text-fill: #FFFF00;");

            } else if (is.queryById(id).getImc() < 35) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité modérée");
                imclbl.setStyle("-fx-text-fill: #FFD700;");
            } else if (is.queryById(id).getImc() < 40) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité sévére");
                imclbl.setStyle("-fx-text-fill: #FF4500;");
            } else {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité massive");
                imclbl.setStyle("-fx-text-fill: #FF0000;");
            }

        } else {
            PoidsActuelle.setText("Ajouter une nouvelle Suivi");
            DateSuivi.setText("Ajouter une nouvelle Suivi");
            tailleshow.setText("Ajouter une nouvelle Suivi");
            imclbl.setText("Ajouter une nouvelle Suivi");

        }

        ageSuivi.setText(String.valueOf(ie.queryById(id).getAge()));
//        XYChart.Series<Date, Integer> series = new XYChart.Series<>();
//        series.getData().add(new XYChart.Data<>(is.queryById(id).getDateSuivi(), is.queryById(id).getPoidsActuelle()));
//        chart.getData().add(series);
//
//        series.setName("Poids");

    }

    public void getForChart() throws SQLException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        String req2 = "SELECT * FROM suivi WHERE fk_idUser_Suivi = ? ORDER BY date_suivi ASC";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, idUser);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                series.getData().add(new XYChart.Data<>(String.valueOf(res.getDate(7)), res.getInt(5)));
            }
            chart.getData().add(series);
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToHisto(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) histo.getScene().getWindow();
            //stage.close();
            Stage primaryStage = new Stage();
            URL url;
            url = new File("src/gui/suivi/histosuivi/DatePicker.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            primaryStage.setTitle("HistoSuivi");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (MalformedURLException ex) {
            Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addSuivibtn(ActionEvent event) throws IOException {
        try {
            //Stage stage = (Stage) histo.getScene().getWindow();
            //stage.close();
            //Stage primaryStage = new Stage();
            URL url;
            url = new File("src/gui/suivi/ajoutSuivi/AjoutSuivi.fxml").toURI().toURL();

            Parent root = FXMLLoader.load(url);
            sceneAdd.getChildren().removeAll();
            sceneAdd.getChildren().setAll(root);

        } catch (MalformedURLException ex) {
            Logger.getLogger(SuiviTrainerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
    }
    
    

    private void refreshing (int id) throws URISyntaxException {
        IUtilisateur iu = new UtilisateurServices();
        String nom = iu.queryUserById(id).getNom();
        String prenom = iu.queryUserById(id).getPrenom();
        Isuivi is = new Suivie_Services();

        NomEntrainer.setText(nom);
        PrenomEntrainer.setText(prenom);
        //Isuivi is = new Suivie_Services();
        //Suivi s = new Suivi();
        IEntrainee ie = new EntraineeServices();
        if (is.queryById(id).getPoidsActuelle() != 0 || is.queryById(id).getTaille() != 0 || is.queryById(id).getDateSuivi() != null || is.queryById(id).getImc() != 0) {
            PoidsActuelle.setText(String.valueOf(is.queryById(id).getPoidsActuelle()));
            DateSuivi.setText(String.valueOf(is.queryById(id).getDateSuivi()));
            tailleshow.setText(String.valueOf(is.queryById(id).getTaille()));
            if (is.queryById(id).getImc() < 18.5) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Maigreur");
                imclbl.setStyle("-fx-text-fill: DeepSkyBlue;");
            } else if (is.queryById(id).getImc() < 25) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Normal");
                imclbl.setStyle("-fx-text-fill: #00FF7F;");
            } else if (is.queryById(id).getImc() < 30) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Suproids");
                imclbl.setStyle("-fx-text-fill: #FFFF00;");

            } else if (is.queryById(id).getImc() < 35) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité modérée");
                imclbl.setStyle("-fx-text-fill: #FFD700;");
            } else if (is.queryById(id).getImc() < 40) {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité sévére");
                imclbl.setStyle("-fx-text-fill: #FF4500;");
            } else {
                imclbl.setText(String.valueOf(is.queryById(id).getImc()) + " --> Obésité massive");
                imclbl.setStyle("-fx-text-fill: #FF0000;");
            }

        } else {
            PoidsActuelle.setText("Ajouter une nouvelle Suivi");
            DateSuivi.setText("Ajouter une nouvelle Suivi");
            tailleshow.setText("Ajouter une nouvelle Suivi");
            imclbl.setText("Ajouter une nouvelle Suivi");

        }

        ageSuivi.setText(String.valueOf(ie.queryById(id).getAge()));
//        XYChart.Series<Date, Integer> series = new XYChart.Series<>();
//        series.getData().add(new XYChart.Data<>(is.queryById(id).getDateSuivi(), is.queryById(id).getPoidsActuelle()));
//        chart.getData().add(series);
//
//        series.setName("Poids");

    }

}


