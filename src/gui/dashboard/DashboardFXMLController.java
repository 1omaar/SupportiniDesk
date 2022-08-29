/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashboard;

import Exception.AuthException;
import interfaces.IUtilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONException;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Utilisateur;
import services.UtilisateurServices;
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
    private Button sideBarBtn3;
    @FXML
    private Button btnCommerce;
    @FXML
    private Button sideBarBtn2;
//    private Button btnLogout = new Button("Déconnexion");
    @FXML
    private AnchorPane navbar;
    @FXML
    private AnchorPane iconBar;
    @FXML
    private ImageView iconDash;
    @FXML
    private ImageView iconShop;
    @FXML
    private ImageView iconSport;
    @FXML
    private ImageView iconCoach;
    @FXML
    private ImageView iconMenu;
    @FXML
    private AnchorPane scenePane;

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

        try {
            incomingToken = new JWebToken(bearerToken);
            if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                int idRole = Integer.parseInt(audience);
                int idUser = Integer.parseInt(subject);
//                control user  side bar
                itemComboBox(idRole);
                sideBarBtn2.setVisible(idRole != 3);
                sideBarBtn2.setManaged(idRole != 3);
                iconCoach.setVisible(idRole != 3);
                iconCoach.setManaged(idRole != 3);
                sideAnchorPane.setVisible(false);
                sideAnchorPane.setManaged(false);
                nomPrenom.setAlignment(Pos.CENTER);
                displayMenu();
                getImageProfil();
                getNameOfCurrentUser(idUser);

            } else {
                redirectToLogin();

            }

        } catch (JSONException | InvalidKeyException | IOException ex) {
            redirectToLogin();
            System.err.println(ex);
        } catch (URISyntaxException | AuthException ex) {
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//      private  void makeStyleNavSideBar(Button btn){
//            btn.onMouseMovedProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
//                btn.setStyle("-fx-background-color: #000000");
//                btn.setStyle("-fx-text-fill: white");
//                btn.setCursor(Cursor.HAND);
//            });
//        btn.onMouseExitedProperty().set((event) -> {
//            btn.setStyle("-fx-background-color: #ffe6cc");
//            btn.setStyle("-fx-text-fill: white");
//            btn.setCursor(Cursor.DEFAULT);
//        });
//      }
    private void displayMenu() {

        iconMenu.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), sideAnchorPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });
        sideAnchorPane.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {

            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });
        iconBar.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), sideAnchorPane);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
            sideAnchorPane.setVisible(true);
            sideAnchorPane.setManaged(true);
        });

        navbar.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), sideAnchorPane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            sideAnchorPane.setVisible(false);
            sideAnchorPane.setManaged(false);
        });
        scenePane.onMouseEnteredProperty().set((EventHandler<MouseEvent>) (MouseEvent event) -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), sideAnchorPane);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            sideAnchorPane.setVisible(false);
            sideAnchorPane.setManaged(false);
        });

    }

    private void itemComboBox(int idRole) {

        ObservableList<String> items = FXCollections.observableArrayList("Profile", "Déconnexion");
        clientComboBox.setItems(items);
        clientComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue != null) {
                if (newValue.equals(clientComboBox.getItems().get(0))) {
                    ActionEvent event = null;
                    try {
                        profil(event, idRole);

//                   oldValue=observable.getValue();
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (newValue.equals(clientComboBox.getItems().get(1))) {
                    ActionEvent event = null;

                    logout(event);

                }

            }

        });

    }

    public void profil(ActionEvent event, int idRole) throws IOException {
        switch (idRole) {

            case 3: {
                Parent root = FXMLLoader.load(getClass().getResource("../profilCoach/ProfilCoachFXML.fxml"));
                scenePane.getChildren().removeAll();
                scenePane.getChildren().setAll(root);
                break;
            }
            default: {
                Parent root = FXMLLoader.load(getClass().getResource("../profil/ProfilFXML.fxml"));
                scenePane.getChildren().removeAll();
                scenePane.getChildren().setAll(root);
                break;
            }

        }

    }

    @FXML
    public void listProduit(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../produits/ProduitsFXML.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

    }

    @FXML
    public void salleDeSport(ActionEvent event) throws IOException {
        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../salleDeSport/ItemSalleSportFXML.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

    }

    @FXML
    public void itemDash(ActionEvent event) throws IOException {

        clientComboBox.getSelectionModel().clearSelection();
        Parent root = FXMLLoader.load(getClass().getResource("../itemDash/ItemDashFXML.fxml"));
        scenePane.getChildren().removeAll();
        scenePane.getChildren().setAll(root);

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

    private void getImageProfil() throws URISyntaxException {
        Image im = new Image(getClass().getResource("../uicontrolers/user.png").toURI().toString());
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }

    private void getNameOfCurrentUser(int id) {
        IUtilisateur iu = new UtilisateurServices();
        String nom = iu.queryUserById(id).getNom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getNom().substring(1);
        String prenom = iu.queryUserById(id).getPrenom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getPrenom().substring(1);
        nomPrenom.setText(nom + " " + prenom);
    }
}
