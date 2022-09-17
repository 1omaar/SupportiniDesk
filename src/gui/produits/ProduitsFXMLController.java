/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produits;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import util.MaConnexion;
 


import gui.produits.ItemController;
import interfaces.ICategories;
import interfaces.IProduits;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import util.MaConnexion;
import model.Produit;
import javafx.scene.layout.GridPane;
import interfaces.MyListener;
import services.Categorieservices;
import services.Produitservices;
import java.awt.event.ItemEvent;
import static java.lang.reflect.Array.set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Categories;

public class ProduitsFXMLController implements Initializable {
    Produit chosenproduct;
        
   
    @FXML
    private ImageView ProdImg;


    @FXML
    private Label ProdNameLable;

    @FXML
    private Label ProdPriceLabel;

    @FXML
    private VBox chosenProdCard;

    @FXML
    private GridPane grid;
    
  
   
    @FXML
    private ScrollPane scroll;
    
  
    
    Connection cnx = MaConnexion.getInstance().getCnx();
    private ArrayList<Produit> produits = new ArrayList<Produit>();

    private String image;
    private MyListener myListener;

/**
 * FXML Controller class
 *
 * @author Asus
 */


   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            produits.addAll(getData());
            if (produits.size() > 0) {
                setChosenProd(produits.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Produit produits) {
                        setChosenProd(produits);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < produits.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../../gui/produits/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(produits.get(i), myListener);

                    if (column == 3) {
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
            Logger.getLogger(ProduitsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Produit> getData() throws SQLException {
        List<Produit> Produits = new ArrayList<>();
        Produit Prod;
        String tt = "SELECT * FROM `produits`";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Prod = new Produit();
            Prod.setNom_produit(queryoutput.getString("Nom_produit"));
            Prod.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            Prod.setDescription(queryoutput.getString("Description"));
            ICategories deptdao = Categorieservices.getInstance();
            Prod.setQuantite(Integer.parseInt(queryoutput.getString("Quantite")));
            Prod.setImage(queryoutput.getString("image"));

            Produits.add(Prod);

        }

        return Produits;
    }

    private void setChosenProd(Produit prod) {
        ProdNameLable.setText(prod.getNom_produit());
        ProdPriceLabel.setText(prod.getPrix() + "DT");
        String path;
 
//          this.img.setImage(image);
        path = prod.getImage();
        Image aa = new Image("file:" + path);
        System.out.println(image);

        ProdImg.setImage(aa);
          chosenproduct=prod;
       
       
    }
}

  
        
   
 

