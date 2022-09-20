package gui.ModifSuppCoaching;

import Exception.AuthException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import interfaces.CoachingsListener;
import model.Coachings;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONException;
import util.JWebToken;
import util.MaConnexion;
import util.Statics;

public class MesCoaching implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Coachings> clist = new ArrayList<>();
    private String image;
    private CoachingsListener CoachingsListener;
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Label prixlab;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ComboBox CombiDiscipline;
    static int idRole, idUser , idselect , nb , idcoach;
    static String des , disc , titre,prx,pl ;
    
    
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnSupprimer;
    
    private Coachings acoaching ;
    @FXML
    private Label Txtid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
        try {
            clist.addAll(getData());
            if (clist.size() > 0) {
                seletedCoaching(clist.get(0));
                CoachingsListener = new CoachingsListener() {
                    @Override
                    public void onClickListener(Coachings Coachings) {
                        seletedCoaching(Coachings);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clist.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/ModifSuppCoaching/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(clist.get(i), CoachingsListener);

                    if (column == 2) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                   
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MesCoaching.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public Integer jwt() {

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
                idRole = Integer.parseInt(audience);
                idUser = Integer.parseInt(subject);

                System.out.println(idUser);

            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(gui.AffichCoaching.ListCoachings.class.getName()).log(Level.SEVERE, null, ex); ////////ItemDashFXMLController////
        }

        return idUser;

    }



 
    
    private List<Coachings> getData() throws SQLException {
        List<Coachings> clist = new ArrayList<>();
        Coachings Coachings;
        String tt = "SELECT * FROM `coachings` where idcoach ='" + jwt() + "' ";
        System.out.println(idUser);

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Coachings = new Coachings();
            Coachings.setId(queryoutput.getInt("id"));
            Coachings.setTitre(queryoutput.getString("titre"));
            Coachings.setPrix(queryoutput.getString("prix"));
            Coachings.setImage(queryoutput.getString("image"));
            Coachings.setDiscipline(queryoutput.getString("discipline"));
            Coachings.setDescription(queryoutput.getString("description"));
            clist.add(Coachings);


        }

        return clist;
    }

    private void seletedCoaching(Coachings Coachings) {
        fruitNameLable.setText(Coachings.getTitre());
        prixlab.setText(Coachings.getPrix());
//       labdiscipline.setText(Coachings.getDiscipline());
        txtDescription.setText(Coachings.getDescription());
           Txtid.setText(String.valueOf(Coachings.getId()));
        String path;
        //   this.img.setImage(image);
        path = Coachings.getImage();
        Image aa = new Image("file:" + path);
        System.out.println(image);
        fruitImg.setImage(aa);
        chosenFruitCard.setStyle("-fx-background-color: #" + ";\n"
                + "    -fx-background-radius: 30;");
        
        
        
// recuperer les données dune annonce de coachings
  idselect = Coachings.getId();
  des=Coachings.getDescription();
  titre=Coachings.getTitre();
  disc=Coachings.getDiscipline();
  prx=Coachings.getPrix();
  pl=Coachings.getPrix();
  nb=Coachings.getNbmax();
  
  idcoach=Coachings.getIdcoach();
  
 
       
        
       
    }

    
    
    
    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
          Statics.xx.setId(idselect);
    
        Statics.xx.setTitre(titre);
         Statics.xx.setDiscipline(disc);
          Statics.xx.setDescription(des);
           Statics.xx.setPlaning(pl);
            Statics.xx.setPrix(prx);
             Statics.xx.setNbmax(nb);
            
               //Statics.xx.setIdcoach(acoaching.getIdcoach());
             
             
             Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifSuppCoaching/Modifier.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
      
           String req = "DELETE FROM  coachings where id =?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idselect);
            ps.executeUpdate();
            System.out.println("PS : sallesport supprimé avec succés!");
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("supprimer de coaching");

            alert.setHeaderText("supprission de caoching terminée");
            alert.setContentText("supprission de caoching terminée");
 alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(MesCoaching.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
