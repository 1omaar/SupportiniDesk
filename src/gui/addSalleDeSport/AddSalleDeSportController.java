/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.addSalleDeSport;

import Exception.AuthException;
import gui.dashboard.DashboardFXMLController;
import interfaces.ISalleSport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.SalleSport;
import org.json.JSONException;
import services.SalleSportServices;
import util.JWebToken;
import util.Notification;
import util.Validation;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-113
 */
public class AddSalleDeSportController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtNumtel;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtRue;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button BtnUpload;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label validationNom;
    @FXML
    private Label validationtNumtel;
    @FXML
    private Label validationtVille;
    @FXML
    private Label validationtRue;
    @FXML
    private Label validationtCodePostal;
    @FXML
    private Label validationtPrix;
    @FXML
    private Label validationtDuration;
    @FXML
    private Label validationtDescription;
    @FXML
    private ImageView imgVitrine;
    final FileChooser fc = new FileChooser();
    private File file;
    private Path filepath;
    File xxx = null;
    String filename = null;
     private String path;
    
    private int idRole, idUser ;
    @FXML
    private TextField taimage;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
                idRole= Integer.parseInt(audience);
                 idUser =Integer.parseInt(subject);
                   System.out.println(idUser);
               
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(AddSalleDeSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        ISalleSport ip = new SalleSportServices();
        if (txtNom.getText().isEmpty()) {
            validationNom.setText("svp entrer le nom de salle de sport");
            return;
        }
        if (txtNumtel.getText().isEmpty()) {
            validationtNumtel.setText("svp entrer le num_tel");
            return;
        }
        if (txtNumtel.getText().length() != 8) {
            validationtNumtel.setText("Il faut 8 chiffre");
            return;

        }
        if (txtVille.getText().isEmpty()) {
            validationtVille.setText("svp entrer le nom de la ville");
            return;
        }
        if (txtRue.getText().isEmpty()) {
            validationtRue.setText("svp entrer le rue de la ville");
            return;
        }
        if (txtCodePostal.getText().isEmpty()) {
            validationtCodePostal.setText("svp entrer le code postal");
            return;
        }
        if (txtCodePostal.getText().length() != 4) {
            validationtCodePostal.setText("Il faut 4 chiffre");
            return;
        }
        if (txtPrix.getText().isEmpty()) {
            validationtPrix.setText("svp entrer le prix");
            return;
        }
        if (txtDuration.getText().isEmpty()){
        validationtDuration.setText("svp entrer la duration ");
        return;
        }
        if (txtDescription.getText().isEmpty()) {
            validationtDescription.setText("svp entrer la description");
            return;
            
        }

//        SalleSport p = new SalleSport(Integer.parseInt(txtNumtel.getText()), Integer.parseInt(txtCodePostal.getText()), (txtNom.getText()), txtVille.getText(), txtRue.getText(), txtDescription.getText(), txtDuration.getText(),Float.valueOf(txtPrix.getText()) );
        SalleSport p = new SalleSport();
        p.setNomSalle(txtNom.getText());
        p.setNumTel(Integer.parseInt(txtNumtel.getText()));
        p.setVille(txtVille.getText());
        p.setRue(txtRue.getText());
        p.setCodePostal(txtCodePostal.getText());
        p.setDescription(txtDescription.getText());
        p.setDuration(txtDuration.getText());
        p.setPrix(Float.valueOf(txtPrix.getText()));
        p.setImageVitrine(String.valueOf(filepath.getFileName()));
        p.setFk_idUser(idUser);
        ip.ajouterSalleSport(p);
//        init();
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
}
