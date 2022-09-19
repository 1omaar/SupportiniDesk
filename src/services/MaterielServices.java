/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Imateriel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.MaterielSalle;
import model.SalleSport;
import util.MaConnexion;

/**
 *
 * @author Elife-Kef-113
 */
public class MaterielServices implements Imateriel {

    Connection cnx = MaConnexion.getInstance().getCnx();

    private static Imateriel MaterielSalle;

    public static Imateriel getInstance() {
        if (MaterielSalle == null) {
            MaterielSalle = new MaterielServices();
        }
        return MaterielSalle;
    }

    @Override
    public void ajouterMaterielSalle(MaterielSalle m) {

        String req = "INSERT INTO materialsalle( nomMaterial, Specialite, Quantite, description,imageVitrine, fk_idSalle) VALUES (?,?,?,?,?,?) ";
//       String req = "INSERT INTO materialsalle( nomMaterial, Specialite, Quantite, description,imageVitrine,) VALUES (?,?,?,?,?) ";
        try {
            java.sql.PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, m.getNomMaterial());
            ps.setString(2, m.getSpecialite());
            ps.setInt(3, m.getQuantite());
            ps.setString(4, m.getDescription());
            ps.setString(5, m.getImageVitrine());
            ps.setInt(6, m.getFk_idSalle());

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

    @Override
    public List<MaterielSalle> affichageById(int fk_idSalle) {
        String req = "SELECT * FROM `materialsalle` where `fk_idSalle` = ?";
        PreparedStatement ps;

        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, fk_idSalle);
            ResultSet res = ps.executeQuery();
            List<MaterielSalle> listMateriel = new ArrayList<>();
            while (res.next()) {
                MaterielSalle ms = new MaterielSalle();
                ms.setId(res.getInt(1));
                ms.setNomMaterial(res.getString(2));
                ms.setQuantite(res.getInt(4));
                ms.setSpecialite(res.getString(3));
                ms.setDescription(res.getString(5));
                ms.setFk_idSalle(res.getInt(6));
                ms.setImageVitrine(res.getString(7));

                listMateriel.add(ms);

            }
            return listMateriel;

        } catch (SQLException ex) {
            Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void supprimerMateriel(MaterielSalle m) {
                String req = "DELETE FROM materialsalle where id =?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, m.getId());
            ps.executeUpdate();
            System.out.println("PS : materialsalle supprimé avec succés!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("supprimer de materialsalle");

            alert.setHeaderText("supprission de materialsalle terminée");
            alert.setContentText("supprission de materialsalle terminée");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(SalleSportServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
