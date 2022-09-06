/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Produit;

import interfaces.ICategories;
import interfaces.IProduits;
import services.Categorieservices;
import services.Produitservices;
import gui.Categorie.CategoriesController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
  import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Categories;
import model.Produit;
import util.MaConnexion;

  
/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ProduitController implements Initializable {
  @FXML
    private ImageView image;
   int index = -1;
   String filename = null;
    byte[] person_image = null;
    // appel connexion 
    Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML 
    private Button Acceuil;

    @FXML
    private TextField Description;

  
   
    @FXML
    private Button Mdf;

    @FXML
    private TextField Quantite;

    @FXML
    private Button Supp;

    @FXML
    private Button addd;

    @FXML
    private ComboBox<Categories> categorie;
    private Button pp;
    @FXML
    private TextField id;


    @FXML
    private TextField nomproduit;

    @FXML
    private Pane pnlOverview;

      @FXML
    
    private TextField rechercher;
    @FXML
    private Label welcome;
   
    @FXML
    private Label validationcat;
    @FXML
    private TextField prix;
    @FXML
   
    
    private TableView<Produit> produitTable;
    @FXML
    private TableColumn<Produit, Integer> idcol;
    @FXML
    private TableColumn<Produit, String> idnomproduit;

    @FXML
    private TableColumn<Produit, Float> idprix;
    @FXML
    private TableColumn<Produit, String> iddescription;
    @FXML
    private TableColumn<Produit, Categories> idcategorie;
     @FXML
    private TableColumn<Produit, String> idimage;
    @FXML
    private TableColumn<Produit, Integer> idquantite;
    ObservableList<Categories> dataList = FXCollections.observableArrayList();
    ObservableList<Produit> Produitlist = FXCollections.observableArrayList();
    private InputStream input;
     @FXML
    private ImageView img;
  
     
   

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String tt = "SELECT * FROM `categories`";

        Statement statement;
        try {
            
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String x = queryoutput.getString("name");

                dataList.add(new Categories(x));
                categorie.setItems(dataList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // afficahage produit
        IProduits x = Produitservices.getInstance();
        Produitlist = x.DisplayAllproduit();

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idnomproduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));

        idprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        iddescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        idcategorie.setCellValueFactory(new PropertyValueFactory<>("cat"));
        idquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        produitTable.setItems(Produitlist);
        search();

    }

    public void search() {

        FilteredList<Produit> filteredData = new FilteredList<>(Produitlist, b -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((pr) -> {
                // If filter text is empty, display all product.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (pr.getNom_produit().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.

                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(produitTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        produitTable.setItems(sortedData);

    }

    
    @FXML
    private void delete(ActionEvent event) throws SQLException {

        if (id.getText().equals("")) {
            showMessageDialog(null, "you must select produit");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer");
            alert.setHeaderText("You're about to delete product!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                ResultSet rs;
                Integer nom = Integer.parseInt(id.getText());
                String xx = id.getText();
                String yy = "delete   from  produits where id = '" + nom + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "produits supprimer avec succes");
                nomproduit.clear();
                prix.clear();
                Description.clear();
                Quantite.clear();
                refreshTable();
                this.img.setVisible(false);
                search();
            }
        }
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {

        if (categorie.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "categorie  must be selected");
            categorie.requestFocus();
            
        } else {

            if (this.isValidated()) {
                String s = categorie.getSelectionModel().getSelectedItem().toString();
                PreparedStatement ps, cat;
                ResultSet rs, rs2;
                String req = "INSERT INTO `produits` ( `nom_Produit`, `prix`, `Description`, `categories`, `quantite`,`image`) VALUES (?,?,?,?,?,?)";
                String yy = "SELECT * FROM produits WHERE nom_Produit ='" + nomproduit.getText() + "'";
                 String query = "select * from Categories WHERE Name = ?";
                ps = cnx.prepareStatement(yy);
                 cat = cnx.prepareStatement(query);
                cat.setString(1, s);
                rs2 = cat.executeQuery();
                rs = ps.executeQuery();
                if (rs.next()) {
                    showMessageDialog(null, "deja existe");
                    nomproduit.requestFocus();
                } else {
                    Produit produit = new Produit();
                    produit.setNom_produit(nomproduit.getText());
                   
                    produit.setPrix(Integer.parseInt(prix.getText()));
                    produit.setDescription(Description.getText());
                    ICategories deptdao = Categorieservices.getInstance();
                    produit.setCat(deptdao.findcatBynom(s));
                    produit.setQuantite(Integer.parseInt(Quantite.getText()));
                    produit.setImage(filename);
                   IProduits x = Produitservices.getInstance();
                    x.insertproduit(produit);

                    System.out.println("PS : produit ajoutée avec succés!");
                    nomproduit.clear();
                    prix.clear();
                    Description.clear();
                    Quantite.clear();
                    refreshTable();
                      this.img.setVisible(false);
                    showMessageDialog(null, "produit ajouter avec succes");
                    search();

                }

            }
        }
    }

    private boolean isValidated() {
//        String s = categorie.getSelectionModel().getSelectedItem().toString();
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);
        if (nomproduit.getText().equals("")) {

            showMessageDialog(null, "nom produit text field cannot be blank.");
            nomproduit.requestFocus();
        } else if (prix.getText().equals("")) {
            showMessageDialog(null, "prix text field cannot be blank.");
            prix.requestFocus();
        
        } else if (Description.getText().equals("")) {
            showMessageDialog(null, "Description text field cannot be blank.");
            Description.requestFocus();
        } else if (Quantite.getText().equals("") && Quantite.equals("[a-zA-Z_]+")) {
            showMessageDialog(null, "Quantite text field cannot be blank.");
            Quantite.requestFocus();
        } else if (!x.matcher(Quantite.getText()).matches()) {
            showMessageDialog(null, "Quantite contains only number.");
            Quantite.requestFocus();
        } else if (!x.matcher(prix.getText()).matches()) {
            showMessageDialog(null, "prix contains only number.");
            prix.requestFocus();
        } else if (categorie.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "categorie  must be selected");
            categorie.requestFocus();
        } else if (filename == null) {
            showMessageDialog(null, "image required");
            pp.requestFocus();} 
        else {
            return true;
        }
        return false;
    }

//!name.matches("[a-zA-Z_]+")
    @FXML

    private void update(ActionEvent event) throws SQLException {

        if (id.getText().equals("")) {
            showMessageDialog(null, "you must select produit");
        } else {

            PreparedStatement cat;
            ResultSet rs2;
            String s = categorie.getSelectionModel().getSelectedItem().toString();
            ICategories x = Categorieservices.getInstance();
            String query = "select * from categories WHERE name = ?";
            cat = cnx.prepareStatement(query);
            cat.setString(1, s);
            rs2 = cat.executeQuery();
            if (rs2.next()) {

                String s1 = rs2.getString("id");
                PreparedStatement ps;
                ResultSet rs;
                String nom = nomproduit.getText();
                String xx = id.getText();

                String yy = "update   produits set nom_Produit ='" + nom + "' , prix ='" + prix.getText() + "', Description ='" + Description.getText() + "' , quantite ='" + Quantite.getText() + "' , categories ='" + s1 + "' ,image =?  where id = '" + xx + "' ";

                ps = cnx.prepareStatement(yy);
               ps.setString(1, filename);
                ps.execute();

                showMessageDialog(null, "produit modifier avec succes");
                refreshTable();
                search();
            }
        }
        // cat = cnx.prepareStatement(query);
        //  cat.setString(1, s);
        // rs2 = cat.executeQuery();

    }

    @FXML
    private void refreshTable() {
        dataList.clear();
        IProduits x = Produitservices.getInstance();
        Produitlist = x.DisplayAllproduit();
        produitTable.setItems(Produitlist);
        String tt = "SELECT * FROM `categories`";

        Statement statement;
        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String xx = queryoutput.getString("name");

                dataList.add(new Categories(xx));
                categorie.setItems(dataList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    private void uploud(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fd = new FileNameExtensionFilter("PNG JPG", "png", "jpg");
        chooser.addChoosableFileFilter(fd);

        int response = chooser.showOpenDialog(null); //select file to open
        //int response = fileChooser.showSaveDialog(null); //select file to save

        if (response == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (fd.accept(f)) {
                filename = f.getAbsolutePath();
            } else {
                showMessageDialog(null, "invalid extension");
            }

        } else {
            showMessageDialog(null, "you must select photo");
        }

    }


    @FXML
    void getSelected(MouseEvent event) {

        index = produitTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        id.setText(String.valueOf(idcol.getCellData(index)));
        nomproduit.setText(idnomproduit.getCellData(index));
        prix.setText(String.valueOf(idprix.getCellData(index)));
        Description.setText(iddescription.getCellData(index));
        Quantite.setText(String.valueOf(idquantite.getCellData(index)));
        Categories xx = idcategorie.getCellData(index);

        categorie.getSelectionModel().select(xx);
        this.img.setVisible(true);
        String path = idimage.getCellData(index);
        Image image = new Image("file:" + path);
        this.img.setImage(image);

     

    }}