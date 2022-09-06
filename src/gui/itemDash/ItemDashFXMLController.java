/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.itemDash;

import Exception.AuthException;
import com.mysql.jdbc.PreparedStatement;

import interfaces.ICoachings;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import model.Coachings;
import org.json.JSONException;

import services.CoachingsService;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ItemDashFXMLController implements Initializable {

    @FXML
    private TextField txtIdCoach;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDiscipline;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtPlaning;

    @FXML
    private TextField txtTitre;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtNbMax;
    @FXML
    private TextField TxtNbInit;
    
  
private int idRole , idUser ; 

      ICoachings ic = new CoachingsService();
  ObservableList<Coachings> listCoachings = FXCollections.observableArrayList();
     PreparedStatement pst,ps;
    int myIndex;
    int id;
    @FXML
    private TextField TxtImage;
    
    @FXML
    private Button btnImage;
    
    final FileChooser fc = new FileChooser();
    private File file ;
    File xxx = null ;
    @FXML
    private Label validationtRue;
    @FXML
    private Label validationtCodePostal;
    @FXML
    private ImageView img;
    

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
                idRole= Integer.parseInt(audience);
                 idUser =        Integer.parseInt(subject);
                   System.out.println(idUser);
                
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(ItemDashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    


    @FXML
    private void Ajouter(ActionEvent event) {
        
        ICoachings ip = new CoachingsService();
        Coachings p = new Coachings();
        
        
        
//       Coachings p = new Coachings(Integer.parseInt(txtIdCoach.getText()),txtTitre.getText(),  txtDiscipline.getText(), txtDescription.getText(),txtPlaning.getText(), txtPrix.getText());
        p.setIdcoach(idUser);
              
        p.setTitre(txtTitre.getText());
        p.setDiscipline(txtDiscipline.getText());
        p.setDescription(txtDescription.getText());
        p.setPlaning(txtPlaning.getText());
        p.setPrix(txtPrix.getText());
         p.setNbmax(Integer.parseInt(txtNbMax.getText()));
          p.setNbinscri(0);
          p.setImage(TxtImage.getText());
        System.out.println(idUser);
        ip.ajouterCoaching(p);
       
        
    }

    @FXML
    private void AjouterImage(ActionEvent event) throws IOException {
          fc.setTitle("Uplode Image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all file", "*.*"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
           
            String x = file.getAbsolutePath();
            String newpath = "src/images/";
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
            Files.copy(sourceFile.toPath(), xxx.toPath());
            //   System.out.println(destinationFile);
            System.out.println(xxx);
            TxtImage.appendText(file.getAbsolutePath() + "\n");
            img.setImage(new Image(file.toURI().toString()));
        } else {
            System.out.println("file is invalide");
        }

    }

    private String randomStringforimage() {
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


    
            
             
    

