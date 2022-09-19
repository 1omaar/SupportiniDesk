package gestionpaiement;


import model.commande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.Commandeservices;

public class ConsulterCommandeController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    private Button btn_Exam;
   
    @FXML
    private Pane pnl_abonnement;
    
    
    
    @FXML
    private TableView<Cmd> tableCommande;
    @FXML
    private TableColumn<Cmd, Integer> tablecolumnRef;
    @FXML
    private TableColumn<Cmd, Float> tablecolumnPrix;
    @FXML
    private TableColumn<Cmd, String> tablecolumnPaiement;
    @FXML
    private TableColumn<Cmd, Integer> tablecolumnNombreP;
    @FXML
    private TableColumn<Cmd, Hyperlink> tablecolumnDetails;
  Commandeservices scommand=new Commandeservices();
    ObservableList<Cmd> lista = FXCollections.observableArrayList(); 
    @FXML
    private Button btn_Commandes;
    @FXML
    private Button btn_Stats;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     List<commande> ls=scommand.AfficherTousCommandes();
     for(commande c: ls){
         Cmd cc=new Cmd(c.getRef(), c.getPrix(), c.getPaiment().getMode_paiement(), c.getNbrProduit());
     lista.add(cc);
     }
     
      tablecolumnRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        tablecolumnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tablecolumnPaiement.setCellValueFactory(new PropertyValueFactory<>("paiement"));
        tablecolumnNombreP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       tablecolumnDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
     
     
     tableCommande.setItems(lista);
    }    

     @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         
        if (event.getSource() == btn_Commandes) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../gui/panier/ConsulterCommande.fxml")));
            stage.setScene(scene);
            stage.show();
        }
//        if (event.getSource() == btn_Stats) {
//            Node node = (Node) event.getSource();
//
//            Stage stage = (Stage) node.getScene().getWindow();
//            //stage.setMaximized(true);
//            stage.close();
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Back_StatCommande.fxml")));
//            stage.setScene(scene);
//            stage.show();
//        }
    }
    
    public class Cmd{
        private int ref;
        private float prix;
        private String paiement;
        private int nombre;
        private Hyperlink details;

        public Cmd(int ref, float prix, String paiement, int nombre) {
            this.ref = ref;
            this.prix = prix;
            this.paiement = paiement;
            this.nombre = nombre;
            details=new Hyperlink("détails");
            
             this.details.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   
                    try {
                        
                        
                        
            Stage stage = (Stage) tableCommande.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/consulterCommande_Produit.fxml"));
                        Parent root = loader.load();
                        ConsulterCommande_Produit controller = loader.getController();
                        controller.idcmd=ref;
                        controller.remplir();
                        Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                });
              
            
            
//        }
//
//        public int getRef() {
//            return ref;
//        }
//
//        public void setRef(int ref) {
//            this.ref = ref;
//        }
//
//        public float getPrix() {
//            return prix;
//        }
//
//        public void setPrix(float prix) {
//            this.prix = prix;
//        }
//
//        public String getPaiement() {
//            return paiement;
//        }
//
//        public void setPaiement(String paiement) {
//            this.paiement = paiement;
//        }
//
//        public int getNombre() {
//            return nombre;
//        }
//
//        public void setNombre(int nombre) {
//            this.nombre = nombre;
//        }
//
//        public Hyperlink getDetails() {
//            return details;
//        }
//
//        public void setDetails(Hyperlink details) {
//            this.details = details;
//        }
////        
//        
        
    }
    
    }
 
    }   

