/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.profil;

import Exception.AuthException;
import gui.ItemSalleSport.ItemSalleSportFXMLController;
import interfaces.IEntrainee;
import interfaces.IGalerie;
import interfaces.IUtilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.GalerieImage;
import org.json.JSONException;
import services.EntraineeServices;
import services.GalerieServices;
import services.UtilisateurServices;
import util.JWebToken;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfilFXMLController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private Label nomPrenom;
    @FXML
    private Label typeUser;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    private Pane detailProfilPane;
    @FXML
    private Label poids;
    @FXML
    private Label age;
    @FXML
    private Label taille;

    @FXML
    private ImageView iconSexe;
    @FXML
    private ScrollPane scrollProfil;
    private int idUser, idRole;
    @FXML
    private Button addImages;
    @FXML
    private HBox sceneGalerie;
    @FXML
    private GridPane gridImg;
    @FXML
    private ScrollPane scrolImg;
    private List<GalerieImage> listImg = new ArrayList<>();
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
            idRole = Integer.parseInt(audience);
            idUser = Integer.parseInt(subject);
            getGalerie();
            getInfoCurrentUser(idUser);
        } catch (URISyntaxException | JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getImageProfil(String path) throws URISyntaxException {
        Image im = new Image(getClass().getResource(path).toURI().toString());
        myCircle.setFill(new ImagePattern(im));
//        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITESMOKE));
        myCircle.setStroke(Color.WHITESMOKE);
    }

    private void getGalerie() throws URISyntaxException {
        IGalerie ig = new GalerieServices();
       
        listImg.addAll(ig.selectImageById(idUser));
        if (listImg.size() == 0) {
            sceneGalerie.setVisible(false);
        } else {
            int column = 1;
            int row = 0;
            for (int i = 0; i < listImg.size(); i++) {
                if (column == 3) {
                    column = 1;
                    row++;
                }

                HBox hBox = new HBox();
                      Image im = new Image(getClass().getResource("../uicontrolers/galerie/" + listImg.get(i).getImageName()).toURI().toString(), 350, 200, false, false);
                ImageView image = new ImageView(im);
                   hBox.getChildren().add(image);
                

               

                System.out.println(column + " " + row);

                gridImg.add(hBox, column++, row);
//                System.out.println(column+" "+row);
                //set grid width
                gridImg.setMinWidth(100);
                gridImg.setPrefWidth(100);
                gridImg.setMaxWidth(100);
//
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                GridPane.setMargin(hBox, new Insets(15));
                GridPane.setValignment(scrolImg, VPos.CENTER);
            }
//                getIn
        }
    }

    @FXML
    private void attachImages() throws IOException {
        FileChooser chooser = new FileChooser();
        IGalerie ig = new GalerieServices();
        chooser.setTitle("Choisir vos Images");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        List<File> file = (List<File>) chooser.showOpenMultipleDialog(null);
        for (int i = 0; i < file.size(); i++) {
            if (file.get(i).isFile()) {

                String absPath = file.get(i).getAbsolutePath();
                String newpath = "src/gui/uicontrolers/galerie/";
                File dir = new File(newpath);
                if (!dir.exists()) {
                    // folder wa7dd ken barchaa mkdirs
                    dir.mkdir();
                }
                File sourceFile = null;
                File destinationFile = null;
                String extension = absPath.substring(absPath.lastIndexOf('.') + 1);
                sourceFile = new File(absPath);
                String nameFile = Validation.randomString();
                File newFile = new File(newpath + nameFile + "." + extension);
                Files.copy(sourceFile.toPath(), newFile.toPath());
                //   System.out.println(destinationFile);
                String path = nameFile + "." + extension;
                ig.addImage(new GalerieImage(idUser, path));
                listImg.clear();
                listImg.addAll(ig.selectImageById(idUser));
            } else {
                Notification.notificationError("ERREUR", "Il faut choisir une image");
            }
        }

    }

    private void getInfoCurrentUser(int id) throws URISyntaxException {
        IUtilisateur iu = new UtilisateurServices();
        String nom = iu.queryUserById(id).getNom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getNom().substring(1);
        String prenom = iu.queryUserById(id).getPrenom().substring(0, 1).toUpperCase() + iu.queryUserById(id).getPrenom().substring(1);

        nomPrenom.setText(nom + " " + prenom);
        email.setText(iu.queryUserById(id).getEmail());
        phone.setText(iu.queryUserById(id).getPhone());

        if (iu.queryUserById(id).getImageName() == null) {
            String pathLocale = "../uicontrolers/user.png";
            getImageProfil(pathLocale);
        } else {
            String pathLocale = "../uicontrolers/users/" + iu.queryUserById(id).getImageName();
            getImageProfil(pathLocale);
        }

        IEntrainee ie = new EntraineeServices();
        age.setText(String.valueOf(ie.queryById(id).getAge()) + " ans");
        poids.setText(String.valueOf(ie.queryById(id).getPoids() + " kg"));
        taille.setText(String.valueOf(ie.queryById(id).getTaille()) + " cm");
        typeUser.setText("Entrainé");
        if ("homme".equals(ie.queryById(id).getSexe())) {
            Image imageSexe = new Image(getClass().getResource("../uicontrolers/male.jpg").toURI().toString());
            iconSexe.setImage(imageSexe);
            iconSexe.setFitWidth(50);
            iconSexe.setFitHeight(50);
        } else {
            Image imageSexe = new Image(getClass().getResource("../uicontrolers/femine.jpg").toURI().toString());
            iconSexe.setImage(imageSexe);
            iconSexe.setFitWidth(70);
            iconSexe.setFitHeight(70);

        }

    }

}
