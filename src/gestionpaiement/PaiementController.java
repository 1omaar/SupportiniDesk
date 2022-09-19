package gestionpaiement;
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import model.paiement;
import services.paiementservices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import static sun.nio.cs.Surrogate.is;
import util.MaConnexion;

public class PaiementController implements Initializable {

    private Button btnAjout;

    private DatePicker date;

    private paiementservices ServicePaiement = new paiementservices();
    @FXML
    private Pane pane3;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane p1;
    @FXML
    private Pane p3;
    @FXML
    private Button btnA;
    @FXML
    private TableView<paiement> t;

    @FXML
    private TableColumn<paiement, ?> txt1;

    @FXML
    private TableColumn<paiement, ?> txt2;

    @FXML
    private TableColumn<paiement, ?> datep;

    @FXML
    private Pane paneButton;
    @FXML
    private Button btnAjoutP;

    @FXML
    private Label tt;
    @FXML
    private Label ff;
    @FXML
    private Button tfmod;
    @FXML
    private Button tfsupp;
    @FXML
    private TextField txt1_carte;
    @FXML
    private TextField txt2_carte;
    @FXML
    private DatePicker datex;
    @FXML
    private RadioButton master;
    @FXML
    private RadioButton visa;
    @FXML
    private RadioButton edinar;

    TableColumn<paiement, Void> colBtn;
    TableColumn<paiement, Void> colSupprimerBtn;
    @FXML
    private ImageView pic;
    @FXML
    private Pane pnlModif;
//    @FXML
//    private Label ff1;
////    @FXML
////    private TextField txt1_carte1;
////    @FXML
////    private TextField txt2_carte1;
//    @FXML
//    private DatePicker datex1;
//    @FXML
//    private RadioButton master1;
//    @FXML
//    private RadioButton visa1;
//    @FXML
//    private RadioButton edinar1;
    @FXML
    private AnchorPane pnllmodif;
    @FXML
    private Button btnMod;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane globalPane;
    @FXML
    private ImageView arriere;
//    @FXML
//    private ImageView arriere1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colBtn = new TableColumn<>("Modifier");
        colBtn.setMinWidth(100);
        colBtn.setMaxWidth(100);
        t.getColumns().add(colBtn);

        colSupprimerBtn = new TableColumn<>("Supprimer");
        colSupprimerBtn.setMinWidth(200);
        colSupprimerBtn.setMaxWidth(200);
        t.getColumns().add(colSupprimerBtn);
        addButtonDeleteToTable();
        addButtonModifToTable();

        try {
            obList = ServicePaiement.AfficherPaiement();
            p3.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setItems(obList);
        // TODO
    }

    @FXML
    public void ajouterPaiement(ActionEvent event) throws Exception {
        String selectedRadioValue = " ";
        ToggleGroup group = new ToggleGroup();

        String convert = txt1_carte.getText();
        int y = Integer.parseInt(convert);
        String cc = txt2_carte.getText();
        int x = Integer.parseInt(cc);

        master.setToggleGroup(group);
        visa.setToggleGroup(group);
        edinar.setToggleGroup(group);

        master.setSelected(true);
        if (master.isSelected() == true) {
            selectedRadioValue = master.getText();

        }
        if (visa.isSelected() == true) {
            selectedRadioValue = visa.getText();

        }
        if (edinar.isSelected() == true) {
            selectedRadioValue = edinar.getText();

        }

        System.out.println("clicked ===> " + selectedRadioValue);

        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate datePickerValue = datex.getValue();

        String dateConverted = d.format(datePickerValue);
        paiement pp = new paiement(selectedRadioValue, y, x, dateConverted);
        if (event.getSource().equals(btnAjoutP)) {

            ServicePaiement.AddPaiement(pp);
            showConfirmation();
        }

        obList.clear();
//        sendMail("asma.melliti@esprit.tn", selectedRadioValue, y, x, dateConverted);

    }

//    public static void sendMail(String recepient, String s, int y, int x, String d) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        //Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        //Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        //Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        //Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        //Your gmail address
//        String myAccountEmail = "rania.ouerghemmi@esprit.tn";
//        //Your gmail password
//        String password = "Netflix123";
//
//        //Create a session with account credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//
//        //Prepare email message
//        Message message = prepareMessage(session, myAccountEmail, recepient, s, y, x, d);
//
//        //Send mail
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String s, int y, int x, String d) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Äjout Paiement ");
//            String htmlCode = "<h1> formation a ete payee avec success </h1> "
//                    + "<br/> <h2><b>Detail paiement<br><p>"
//                    + "" + s + ", numero de carte :" + y + ", crypt:" + x + ", date :" + d + "</p> </b></h2>";
//            message.setContent(htmlCode, "text/html");
//            return message;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

    ObservableList<paiement> obList;

    @FXML
    public void afficherPaiement(ActionEvent event) throws SQLException {
        t.refresh();
        obList.clear();
        if (event.getSource().equals(btnA)) {
            arriere.setVisible(false);
            pane3.setVisible(true);
          
            p1.setVisible(false);
           
            pane3.toFront();
            obList = ServicePaiement.AfficherPaiement();

            t.refresh();

            txt1.setCellValueFactory(new PropertyValueFactory<>("num_carte"));
            txt2.setCellValueFactory(new PropertyValueFactory<>("cryptograme"));
            datep.setCellValueFactory(new PropertyValueFactory<>("date_exp"));

            System.out.println("table list  contains= " + obList);
            t.setItems(obList);

        }
    }

    @FXML
    private void modifierPaiement(ActionEvent event) {

        if (event.getSource().equals(btnMod)) {

            addButtonModifToTable();

        }
        paiementCourant.setNum_carte(Integer.valueOf(txt1_carte.getText()));
        paiementCourant.setCryptograme(Integer.valueOf(txt2_carte.getText()));
        try {
            ServicePaiement.ModifyPaiement(paiementCourant);

        } catch (Exception e) {
            System.out.println("exception" + e.getMessage());
        }
        showConfirmationModify();

    }

    @FXML
    private void supprimerPaiement(ActionEvent event) {
    }

    private void showConfirmation() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Paiement...");
        alert.setHeaderText("Paiement a ete ajouté avec success!!!");

////         option != null.
        Optional<ButtonType> option = alert.showAndWait();

    }

    /**
     * Add Button
     *
     */
    Button btn;
    int id;
    paiement paiementCourant = new paiement();

    public void addButtonModifToTable() {

        String etat = " ";

        Callback<TableColumn<paiement, Void>, TableCell<paiement, Void>> cellFactory = new Callback<TableColumn<paiement, Void>, TableCell<paiement, Void>>() {
            @Override
            public TableCell<paiement, Void> call(final TableColumn<paiement, Void> param) {

                final TableCell<paiement, Void> cell = new TableCell<paiement, Void>() {

                    {

                        btn = new Button("Modifier");
//                        btn.setStyle("-fx-background-color:black");
//                        btn.setStyle("-fx-pref-width: 200px;");
                        btn.setOnAction((ActionEvent event) -> {
                            arriere.setVisible(false);
                            arriere.setVisible(true);
                            t.setVisible(false);
                            pic.setVisible(false);
//                            new ZoomIn(pnllmodif).play();
                            pnllmodif.toFront();

                            paiementCourant = t.getSelectionModel().getSelectedItem();
                            if (master.isSelected()) {
                                master.setSelected(true);
                            } else if (visa.isSelected()) {
                                visa.setSelected(true);
                            } else {
                                edinar.setSelected(true);
                            }

                            txt1_carte.setText(String.valueOf(paiementCourant.getNum_carte()));
                            txt2_carte.setText(String.valueOf(paiementCourant.getCryptograme()));
                            datex.setValue(LocalDate.parse(String.valueOf(paiementCourant.getDate_exp())));

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

    }
    Button btnSupprimer;
    paiement c = new paiement();

    public void addButtonDeleteToTable() {

        Callback<TableColumn<paiement, Void>, TableCell<paiement, Void>> cellFactory = new Callback<TableColumn<paiement, Void>, TableCell<paiement, Void>>() {
            @Override
            public TableCell<paiement, Void> call(final TableColumn<paiement, Void> param) {

                final TableCell<paiement, Void> cell = new TableCell<paiement, Void>() {

                    {

                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            c = t.getSelectionModel().getSelectedItem();
                            try {
                                System.out.println("table view data ====> " + c);
                                showConfirmation(c);

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };

        colSupprimerBtn.setCellFactory(cellFactory);

    }
    private Label label;

    private void showConfirmation(paiement p) throws SQLException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Veuillez vous vraiment supprimer??");
        alert.setContentText("paiement est obligatoire !!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            ServicePaiement.DeletePaiement(p);
            obList.clear();
            ServicePaiement.AfficherPaiement();
            addButtonModifToTable();
            addButtonDeleteToTable();
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    private void showConfirmationModify() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Paiement...");
        alert.setHeaderText("Paiement a ete modifier avec success!!!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {

        }
//        oke button is pressed 

    }

    @FXML
    private void retourPaiement(MouseEvent event) {
        t.setVisible(true);
        arriere.setVisible(true);
        arriere.setVisible(false);
        pnllmodif.setVisible(false);
        pic.setVisible(false);
//        new ZoomIn(pane3).play();
        pane3.toFront();
    }

    @FXML
    private void NaviguerHandler(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/commander.fxml"));
        Parent root = loader.load();

        if (globalPane.getScene() != null) {
            globalPane.getScene().setRoot(root);
        }
    }

}
