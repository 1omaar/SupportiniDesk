package gestionpaiement;
import interfaces.ICommande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produit;
import services.Commandeservices;
public class ConsulterCommande_Produit implements Initializable {
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TableView<Produit> tableProduit;
    @FXML
    private TableColumn<Produit, String> tablecolumnNom_Prod;
    @FXML
    private TableColumn<Produit, String> tablecolumnquantite;
    @FXML
    private TableColumn<Produit, Float> tablecolumnPrix;
   Commandeservices scommand=new Commandeservices();
    ObservableList<Produit> lista = FXCollections.observableArrayList(); 
    public int idcmd;
    @FXML
    private Button btn_Commandes;
   
    @FXML
    private ImageView retourhandle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }  
    
    public void remplir(){
           List<Produit> ls=scommand.afficherProduitParCommande(idcmd);
     for(Produit c: ls){
      lista.add(c);
     }
     
      tablecolumnNom_Prod.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));
        tablecolumnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tablecolumnquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       
     
     tableProduit.setItems(lista);
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         
        if (event.getSource() == btn_Commandes) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
           stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/panier/consulterCommande.fxml")));
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void handleretourCmd(MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/panier/consulterCommande.fxml")));
            stage.setScene(scene);
            stage.show();
    }
    
}
