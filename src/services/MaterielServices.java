/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Imateriel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.MaterielSalle;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-113
 */
public class MaterielServices  implements Imateriel {
        Connection cnx = MaConnexion.getInstance().getCnx();

        private static Imateriel MaterielSalle;
        public static Imateriel getInstance(){
        if (MaterielSalle == null){
            MaterielSalle = new MaterielServices();
        }
        return MaterielSalle;
        }
    
    
      @Override
    public void ajouterMaterielSalle(MaterielSalle m) {
//       String req = "INSERT INTO materialsalle( nomMaterial, Specialite, Quantite, description,imageVitrine, fk_idSalle) VALUES (?,?,?,?,?,?) ";
       String req = "INSERT INTO materialsalle( nomMaterial, Specialite, Quantite, description,imageVitrine) VALUES (?,?,?,?,?) ";
       try {
            java.sql.PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, m.getNomMaterial());
            ps.setString(2, m.getSpecialite());
            ps.setInt(3, m.getQuantite());
            ps.setString(4, m.getDescription());
           ps.setString(5, m.getImageVitrine());
//            ps.setInt(6, m.getFk_idSalle());
          
            ps.executeUpdate();
            System.out.println("PS :material Salle De Sport ajoutée avec succés!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("material Salle De Sport AJOUTER");

            alert.setHeaderText("AJOUTER AVEC SUCCES");
            alert.setContentText("material Salle De Sport AJOUTER AVEC SUCCES!");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
