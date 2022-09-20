/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addMaterielSalleDeSport;

import gui.PssAffiche.PssAfficheController;
import gui.dashboard.DashboardFXMLController;
import interfaces.ISalleSport;
import interfaces.Imateriel;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MaterielSalle;
import model.SalleSport;
import services.MaterielServices;
import services.SalleSportServices;
import util.Notification;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class AddMaterielSalleDeSportController implements Initializable {

    @FXML
    private Label prix;

    private SalleSport Salle;
    private int idRole, idUser, idSalleSport;
    @FXML
    private ImageView img;
    @FXML
    private Label descreption;
    @FXML
    private Label nomSalle;
    @FXML
    private Label numTelSalle;
    @FXML
    private Label duration;
    @FXML
    private Label adress;
    @FXML
    private Button back;

    private MaterielSalle Materiel;

    @FXML
    private ScrollPane scrollListMateriel;

    @FXML
    private GridPane gridMateriel;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idSalleSport =Statics.xx.getId();
        displaySalle();
        Imateriel im = new MaterielServices();
        List<MaterielSalle> listMateriel = new ArrayList<>();
        listMateriel.addAll(im.affichageById(Salle.getId()));
        int column = 1;
        int row = 0;
        if (listMateriel.size() != 0) {
            try {
                for (int i = 0; i < listMateriel.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("./ItemMateriel.fxml"));

                    VBox vbox = loader.load();

                    ItemMaterielController c = loader.getController();

                    c.setData(listMateriel.get(i));

                    gridMateriel.add(vbox, column++, row);

                    System.out.println(column + " " + row);
                    //set grid width
                    gridMateriel.setMinWidth(500);
                    gridMateriel.setPrefWidth(500);
                    gridMateriel.setMaxWidth(500);
//
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                    GridPane.setMargin(vbox, new Insets(15));
                    GridPane.setValignment(scrollListMateriel, VPos.CENTER);
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(AddMaterielSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public void displaySalle() {
        ISalleSport is = new SalleSportServices();
        this.Salle = is.affichageById(idSalleSport);

        System.out.println(Salle);
        nomSalle.setText(Salle.getNomSalle());
        prix.setText(String.valueOf(Salle.getPrix()) + " DT");
        adress.setText(Salle.getVille() + " ," + Salle.getRue() + " " + Salle.getCodePostal());
        descreption.setText(Salle.getDescription());
        prix.setText(String.valueOf(Salle.getPrix()));
        duration.setText(Salle.getDuration());
        numTelSalle.setText(String.valueOf(Salle.getNumTel()));
        idSalleSport = Salle.getId();

        Image im;
        try {
            im = new Image(getClass().getResource("../uicontrolers/imageSalleSport/" + Salle.getImageVitrine()).toURI().toString());
            img.setImage(im);
        } catch (URISyntaxException ex) {
            Logger.getLogger(PssAfficheController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void backToDashboard(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();

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

    @FXML
    private void ajouterMateriel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ajouter.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../addMaterielSalleDeSport/AjouterDeMaterielFXML.fxml"));
        Image icon;
        icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Ajout Materiel");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void init(MouseEvent event) {
    }
}
