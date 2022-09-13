package gui.AfficheMateriel;

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

import model.MaterielSalle;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import util.MaConnexion;
import main.MaterielListener;

public class ListeMaterielController implements Initializable {

    @FXML
    private VBox chosenFruitCard;


    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<MaterielSalle> clist = new ArrayList<>();
    private String image;
    private MaterielListener myListener;
    Connection cnx = MaConnexion.getInstance().getCnx();
    private Label prixlab;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ComboBox<?> CombiDiscipline;
    @FXML
    private Label txtSpecialite;
    @FXML
    private Label txtName;
    
    
    

    

    private List<MaterielSalle> getData() throws SQLException {
        List<MaterielSalle> clist = new ArrayList<>();
        MaterielSalle MaterielSalle;
        String tt = "SELECT * FROM `materialsalle` ";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            MaterielSalle = new MaterielSalle();
            MaterielSalle.setNomMaterial(queryoutput.getString("nomMaterial"));
         MaterielSalle.setSpecialite(queryoutput.getString("Specialite"));
         MaterielSalle.setDescription(queryoutput.getString("description"));
            MaterielSalle.setImageVitrine(queryoutput.getString("imageVitrine"));
             MaterielSalle.setQuantite(queryoutput.getInt("Quantite"));
             MaterielSalle.setFk_idSalle(queryoutput.getInt("Fk_idSalle"));
            clist.add(MaterielSalle);

        }

        return clist;
    }

    private void setChosenFruit(MaterielSalle MaterielSalle) {
        txtName.setText(MaterielSalle.getNomMaterial());
         txtSpecialite.setText(MaterielSalle.getSpecialite());
       txtDescription.setText(MaterielSalle.getDescription());
//       txtDescription.setText(MaterielSalle.getDescription());

        String path;

//           this.img.setImage(image);
        path = MaterielSalle.getImageVitrine();
        Image aa = new Image("file:" + path);
        System.out.println(image);
        fruitImg.setImage(aa);
        chosenFruitCard.setStyle("-fx-background-color: #" +  ";\n"
                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clist.addAll(getData());
            if (clist.size() > 0) {
                setChosenFruit(clist.get(0));
                myListener = new MaterielListener() {
                    @Override
                    public void onClickListener(MaterielSalle MaterielSalle) {
                        setChosenFruit(MaterielSalle);
                    }
                };
            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < clist.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
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
            Logger.getLogger(ListeMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
