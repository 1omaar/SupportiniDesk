/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ModifSuppCoaching;

import static gui.ModifSuppCoaching.MesCoaching.idselect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.MaConnexion;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-114
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtDiscipline;
    @FXML
    private TextField txtPlaning;
    @FXML
    private Label validationtRue;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtNbMax;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnAjouter;
    @FXML
    private ImageView img;
    @FXML
    private TextField TxtImage;
    @FXML
    private Button btnImage;
    Connection cnx = MaConnexion.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtTitre.setText(Statics.xx.getTitre());
       txtDiscipline.setText(Statics.xx.getDiscipline());
       txtPlaning.setText(Statics.xx.getPlaning());
       txtNbMax.setText(String.valueOf(Statics.xx.getNbmax()));
       txtDescription.setText(Statics.xx.getDescription());
       txtPrix.setText(Statics.xx.getPrix());
    }    


    @FXML
    private void AjouterImage(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        String req = "UPDATE coachings SET titre=?,discipline=?,description= ?,planing=?,prix=?,nbmax=? WHERE id= ?";
     
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,txtTitre.getText());
            ps.setString(2,txtDiscipline.getText());
            ps.setString(3, txtDescription.getText());
            ps.setString(4, txtPlaning.getText());
            ps.setString(5, txtPrix.getText());
            ps.setString(6, txtNbMax.getText());
        
            ps.setInt(7, Statics.xx.getId());

            ps.executeUpdate();
         
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("modification terminée");

            alert.setHeaderText("modification terminée");
            alert.setContentText("modification de Coachings de sport!");
              alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
        /////////////////////
        
//                Parent root = FXMLLoader.load(getClass().getResource("/ModifSuppCoaching/MesCoaching.fxml"));
//        Stage stage = (Stage) btnAjouter.getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
        
        /////////////////////////
        
    }
    
}
