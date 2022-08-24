/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard;

import Exception.AuthException;
import com.sun.javafx.scene.SceneHelper;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONException;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;

import javafx.collections.ObservableList;
import supportini.MainSupportini;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private AnchorPane sideAnchorPane;
    @FXML
    private Label nomPrenom;
    @FXML
    private Circle myCircle;
    @FXML
    private ComboBox<String> clientComboBox;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnDashboard1;
    @FXML
    private Button btnDashboard11;
    @FXML
    private Button btnDashboard111;
//    private Button btnLogout = new Button("Déconnexion");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //recieve the bearer token
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;

        nomPrenom.setAlignment(Pos.CENTER);

        try {
            incomingToken = new JWebToken(bearerToken);
            if (!incomingToken.isValid()) {
                itemComboBox();
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();

//             myCircle.setStroke(Color.WHITESMOKE);
//                Image im = new Image("../gui/uicontrolers/images");
//                myCircle.setFill(new ImagePattern(im));
//                myCircle.setEffect(new DropShadow(+25d,0d,+2d,Color.WHITESMOKE));
            } else {
                redirectToLogin();

            }

        } catch (JSONException | AuthException | InvalidKeyException | IOException ex) {
            redirectToLogin();
            System.err.println(ex);
        }

    }

    private void itemComboBox() {

//        btnLogout.setAlignment(Pos.CENTER);
//        btnLogout.setBackground(Background.EMPTY);
//        btnLogout.setStyle("-fx-font-size:15");
//        btnLogout.setCursor(Cursor.HAND);
////        btnLogout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnLogout.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                btnLogout.setStyle("-fx-background-color: #ffe6cc;");
//                btnLogout.setStyle("-fx-font-size:15");
//                btnLogout.setCursor(Cursor.HAND);
//
//            }
//        });
        ObservableList<String> items = FXCollections.observableArrayList("Déconnexion");
        clientComboBox.setItems(items);
        clientComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null) {
                ActionEvent event = null;

                logout(event);

            }
        });

    }

    private void logout(ActionEvent event) {
        Preferences userPreferences = Preferences.userRoot();
        try {
            userPreferences.clear();
            redirectToLogin();
        } catch (BackingStoreException ex) {
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectToLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../login/LoginFXML.fxml"));
            Stage primaryStage = (Stage) clientComboBox.getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Se Connecter chez Supportini");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
