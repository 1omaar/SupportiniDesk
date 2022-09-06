package gui.annonceCoachings;


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
import supportini.Main;
import supportini.MyListener;
import model.Coachings;

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
import util.MaConnexion;

public class MarketController implements Initializable {

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
    private MyListener myListener;
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private Label prixlab;

    private List<Coachings> getData() throws SQLException {
        List<Coachings> clist = new ArrayList<>();
        Coachings Coachings;
        String tt = "SELECT * FROM `coachings` where nbinscri<nbmax";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            Coachings = new Coachings();
            Coachings.setTitre(queryoutput.getString("titre"));
         Coachings.setPrix(queryoutput.getString("prix"));
            Coachings.setImage(queryoutput.getString("image"));
          
            clist.add(Coachings);

        }

        return clist;
    }

    private void setChosenFruit(Coachings Coachings) {
        fruitNameLable.setText(Coachings.getTitre());
       prixlab.setText(Coachings.getPrix());
        String path;

        //   this.img.setImage(image);
        path = Coachings.getImage();
        Image aa = new Image("file:" + path);
        System.out.println(image);
        fruitImg.setImage(aa);
//        chosenFruitCard.setStyle("-fx-background-color: #" +  ";\n"
//                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clist.addAll(getData());
            if (clist.size() > 0) {
                setChosenFruit(clist.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Coachings Coachings) {
                        setChosenFruit(Coachings);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clist.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/annonceCoachings/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(clist.get(i), myListener);

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
            Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
