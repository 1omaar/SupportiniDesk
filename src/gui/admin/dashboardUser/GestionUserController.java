/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.dashboardUser;

import Exception.AuthException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.dashboard.DashboardFXMLController;
import interfaces.IUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Utilisateur;
import org.json.JSONException;
import services.UtilisateurServices;
import util.JWebToken;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class GestionUserController implements Initializable {

    @FXML
    private Circle myCircle;
    private int id, idRole;
    @FXML
    private Button btnLogout;
    @FXML
    private TableView<Utilisateur> tableUser;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colEmail;
    @FXML
    private TableColumn<Utilisateur, String> colCin;
    @FXML
    private TableColumn<Utilisateur, String> colPhone;
    @FXML
    private TableColumn<Utilisateur, String> colBtn;
    @FXML
    private TableColumn<Utilisateur, String> colId;
    ObservableList<Utilisateur> list = FXCollections.observableArrayList();
    @FXML
    private Button searchEnt;
    @FXML
    private Button searchCoachs;
    @FXML
    private Button seachPss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");

        JWebToken incomingToken;

        try {
            incomingToken = new JWebToken(bearerToken);

            if (!incomingToken.isValid()) {
                String audience = incomingToken.getAudience();
                String subject = incomingToken.getSubject();
                idRole = Integer.parseInt(audience);
                id = Integer.parseInt(subject);

                listUsers();
            }
        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
            Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        Preferences userPreferences = Preferences.userRoot();
        try {
            userPreferences.clear();
            redirectToLogin();
        } catch (BackingStoreException ex) {
            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void redirectToLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../../login/LoginFXML.fxml"));
            Stage primaryStage = (Stage) btnLogout.getScene().getWindow();
            primaryStage.close();
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../uicontrolers/logosportstrnsprt.png"));
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Se Connecter chez Supportini");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void refreshTable() {

        list.clear();
        IUtilisateur iu = new UtilisateurServices();

        list.addAll(iu.displayUser());

        tableUser.setItems(list);

    }

    private void listUsers() {
        refreshTable();

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        colCin.setCellValueFactory(
                new PropertyValueFactory<>("cin"));
//        ObservableList<ImageView> listImg = FXCollections.observableArrayList();
//        for (int i = 0; i < list.size(); i++) {
//            ImageView Image = new ImageView();
//            Image img = new Image("../../uicontrolers/users/" + list.get(i).getImageName());
//            Image.setImage(img);
//            listImg.add(Image);
//        }
        IUtilisateur iu = new UtilisateurServices();
        tableUser.setFixedCellSize(50);
        tableUser.prefHeightProperty().bind(tableUser.fixedCellSizeProperty().multiply(Bindings.size(tableUser.getItems()).add(1.01)));
        tableUser.minHeightProperty().bind(tableUser.prefHeightProperty());
        tableUser.maxHeightProperty().bind(tableUser.prefHeightProperty());

        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory;
        cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell;
            cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView unlockIcon = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK);
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.BAN);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Utilisateur user = tableUser.getSelectionModel().getSelectedItem();

                            iu.banUser(user.getId(), user.getStatus());

                            refreshTable();

                        });
                        
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

//                     
                    }
                }
            };
            return cell;
        };
        colBtn.setCellFactory(cellFoctory);

        tableUser.setFixedCellSize(50);
        tableUser.prefHeightProperty().bind(tableUser.fixedCellSizeProperty().multiply(Bindings.size(tableUser.getItems()).add(1.01)));
        tableUser.minHeightProperty().bind(tableUser.prefHeightProperty());
        tableUser.maxHeightProperty().bind(tableUser.prefHeightProperty());
    }

    @FXML
    public void searchByIdRole() {
        searchEnt.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(2));
            
             tableUser.setItems(list);
        });
            searchCoachs.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(3));
            
             tableUser.setItems(list);
        });
                seachPss.setOnAction((event) -> {
            IUtilisateur iu = new UtilisateurServices();
            list.clear();
            list.addAll(iu.queryUserByRoleId(4));
            
             tableUser.setItems(list);
        });
    }

}
