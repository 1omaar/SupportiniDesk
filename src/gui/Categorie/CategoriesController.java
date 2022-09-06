package gui.Categorie;

import interfaces.ICategories;
import services.Categorieservices;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import static javax.swing.JOptionPane.showMessageDialog;
import model.Categories;
import util.MaConnexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author bouden
 */
public class CategoriesController implements Initializable {

    int index = -1;
    
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField rechercher;
    @FXML
    private Label welcome;
    @FXML
    private TextField id;
    @FXML
    private Button addd11;
    @FXML
    private Button addd;
    @FXML
    private Button addd1;
    @FXML
    private TableColumn<Categories, Integer> idCol;
    @FXML
    private TableColumn<Categories, String> cat;
    @FXML
    private TextField nomcategorie;
    ObservableList<Categories> listM;
    ObservableList<Categories> data;
    private final ObservableList<Categories> dataList = FXCollections.observableArrayList();

    Connection cnx = MaConnexion.getInstance().getCnx();
    private final ObservableList<Categories> catlist = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Categories catego = null;
    @FXML
    private TableView<Categories> categoTable;
    @FXML
    private Label validationcat;

    /**
     * Initializes the controller class.
     */
@Override
    public void initialize(URL url, ResourceBundle rb) {

        String tt = "SELECT * FROM `categories`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String x = queryoutput.getString("name");
                Integer y = queryoutput.getInt("id");
                dataList.add(new Categories(y, x));
            }
            cat.setCellValueFactory(new PropertyValueFactory<>("name"));
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            categoTable.setItems(dataList);
            search();

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void search(){
        FilteredList<Categories> filteredData = new FilteredList<>(dataList, b -> true);
            rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Categories) -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Categories.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.

                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Categories> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(categoTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
           categoTable.setItems(sortedData);
    }



    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (this.isValidated()) {
            
              Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Supprimer categorie");
            alert.setHeaderText("You're about to delete categorie!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {
            PreparedStatement ps;
            ResultSet rs;
            String nom = nomcategorie.getText();
            String xx = id.getText();
            String yy = "delete   from  categories where name = '" + nom + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();

            showMessageDialog(null, "categorie supprimer avec succes");
            nomcategorie.clear();
            id.clear();
            refreshTable();
            search();
        }
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException, SQLException {

        if (this.isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yy = "SELECT * FROM categories WHERE name ='" + nomcategorie.getText() + "'";
            ps = cnx.prepareStatement(yy);

            rs = ps.executeQuery();
            if (rs.next()) {
                showMessageDialog(null, "deja existe");
            } else {

                Categories cat = new Categories();
                cat.setName(nomcategorie.getText());
                ICategories deptdao = Categorieservices.getInstance();
                deptdao.insertcat(cat);
                showMessageDialog(null, "categorie ajouter avec succes");
                nomcategorie.clear();
                id.clear();
                refreshTable();
                search();
                

            }
        }
    }

    private boolean isValidated() {

        if (nomcategorie.getText().equals("")) {
            validationcat.setText("nom categorie text field cannot be blank.");

            nomcategorie.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        if (this.isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            String nom = nomcategorie.getText();
            String xx = id.getText();
            String yy = "update   categories set  name ='" + nom + "' where id = '" + xx + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();

            showMessageDialog(null, "categorie modifier avec succes");
            refreshTable();
            search();
        }
    }

   
    @FXML

    private void refreshTable() {
        dataList.clear();
        try {

            String query = "SELECT * FROM `categories`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dataList.add(new Categories(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
               categoTable.setItems(dataList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void getSelected(MouseEvent event) {

        index = categoTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        id.setText(idCol.getCellData(index).toString());
        nomcategorie.setText(cat.getCellData(index));

    }
    
}
