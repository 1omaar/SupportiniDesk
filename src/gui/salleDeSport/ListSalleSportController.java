/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.salleDeSport;

import gui.ItemSalleSport.ItemSalleSportFXMLController;
import interfaces.ISalleSport;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.SalleSport;
import services.SalleSportServices;

/**
 * FXML Controller class
 *
 * @author HSOUNA
 */
public class ListSalleSportController implements Initializable {

    @FXML
    private GridPane Lsport;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ISalleSport ss = new SalleSportServices();
        List<SalleSport> listSalleSport = new ArrayList<>();

        listSalleSport.addAll(ss.affichage());
//        System.out.println(listSalleSport.size());
        int column = 0;
        int row = 0;
        try {
            for (int i = 0; i < listSalleSport.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ItemSalleSport/ItemSalleSportFXML.fxml"));

                AnchorPane anchorPane = loader.load();

                ItemSalleSportFXMLController c = loader.getController();
                c.setData(listSalleSport.get(i));
                Lsport.add(anchorPane, column, row++);
//                System.out.println(column+" "+row);
                //set grid width
                Lsport.setMinWidth(500);
                Lsport.setPrefWidth(500);
                Lsport.setMaxWidth(500);
//
//                //set grid height
//                Lsport.setMinHeight(300);
//                Lsport.setPrefHeight(400);
//                Lsport.setMaxHeight(400);

                GridPane.setMargin(anchorPane, new Insets(25));
            }
        } catch (IOException ex) {
            Logger.getLogger(ListSalleSportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
