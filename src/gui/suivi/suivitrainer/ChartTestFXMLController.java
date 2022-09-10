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
import java.io.IOException;
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
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import model.Suivi;
import org.json.JSONException;
import services.EntraineeServices;
import services.UtilisateurServices;
import util.JWebToken;
import util.MaConnexion;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class ChartTestFXMLController implements Initializable {

    @FXML
    private Button btnChart;
    @FXML
    private LineChart<String, Integer> chart;

    Connection cnx = MaConnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       

    }

    @FXML
    private void btnChart(ActionEvent event) throws SQLException {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        String req2 = "SELECT * FROM suivi WHERE fk_idUser_Suivi = ? ORDER BY date_suivi ASC";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
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
                series.getData().add(new XYChart.Data<>(String.valueOf(res.getDate(7)),res.getInt(5)));
                
                IEntrainee ie = new EntraineeServices();
                IUtilisateur iu = new UtilisateurServices();
                String nom = iu.queryUserById(idUser).getNom();
                String prenom = iu.queryUserById(idUser).getPrenom();
                series.setName(res.getString(nom));
            }
            chart.getData().add(series);
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
