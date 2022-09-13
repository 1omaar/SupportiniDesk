/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addMaterielSalleDeSport;

import Exception.AuthException;
import gui.dashPss.DashPssController;
import interfaces.ISalleSport;
import interfaces.Imateriel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.MaterielSalle;
import model.SalleSport;
import org.json.JSONException;
import services.MaterielServices;
import services.SalleSportServices;
import util.JWebToken;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class AddMaterielSalleDeSportController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private Label validationNom;
    @FXML
    private TextField txtSpecialite;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label validationtDescription;
    @FXML
    private ImageView imgVitrine;
    @FXML
    private Button BtnUpload;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label validationtSpecialite;
    @FXML
    private Label validationtQuantite;
 private Path filepath;
 final FileChooser fc = new FileChooser();
    private File file ;
    File xxx = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Preferences userPreferences = Preferences.userRoot();

        String bearerToken = userPreferences.get("BearerToken", "root");

        JWebToken incomingToken;

        try {
            incomingToken = new JWebToken(bearerToken);
            if (!incomingToken.isValid()) {

//                get id and idRole for current user
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                int idRole = Integer.parseInt(audience);
                int idUser = Integer.parseInt(subject);

            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(DashPssController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
         fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {

            String x = file.getAbsolutePath();
            String newpath = "src/gui/uicontrolers/imageSalleSport/";
            File dir = new File(newpath);
            if (!dir.exists()) {
                // folder wa7dd ken barchaa mkdirs
                dir.mkdirs();
            }
            File sourceFile = null;
            File destinationFile = null;
            String extension = x.substring(x.lastIndexOf('.') + 1);
            sourceFile = new File(x);
            xxx = new File(newpath + randomStringforimage() + "." + extension);
            filepath = Files.copy(sourceFile.toPath(), xxx.toPath());
            System.out.println(filepath);
               //System.out.println(destinationFile);
            System.out.println(xxx);
            //taimage.appendText(file.getAbsolutePath() + "\n");
            imgVitrine.setImage(new Image(file.toURI().toString()));
            //System.out.println(imgVitrine);
            System.out.println("********************"+file.toURI().toString());
            System.out.println("filename = " +filepath.getFileName());
        } else {
            System.out.println("file is invalide");
        }
    }
    
    public static String randomStringforimage() {
        //   String  randomString  =null;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 12;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();

        return randomString;
    }
    

    @FXML
    private void Ajouter(ActionEvent event) {
        Imateriel ip = new MaterielServices();
        MaterielSalle m = new MaterielSalle();
        m.setNomMaterial(txtNom.getText());
        m.setQuantite(Integer.parseInt(txtQuantite.getText()));
        m.setSpecialite(txtSpecialite.getText());
        m.setDescription(txtDescription.getText());
        m.setImageVitrine(String.valueOf(filepath.getFileName()));
//        m.setFk_idUser(idUser);
        ip.ajouterMaterielSalle(m);

    }

}
