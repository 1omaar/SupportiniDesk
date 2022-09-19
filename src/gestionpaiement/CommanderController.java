//package gestionpaiement;
//import model.lignedecommande;
//import model.LignePanier;
//import model.Panier;
//import model.Utilisateur;
//import model.commande;
//import interfaces.ICommande;
//import interfaces.IPaiement;
//import interfaces.IPanier;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Hyperlink;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import model.paiement;
//import services.Commandeservices;
//import services.Panierservices;
//import services.paiementservices;
//import util.MaConnexion;
//
//
//public class CommanderController implements Initializable {
//
//    @FXML
//    private AnchorPane rt;
//    @FXML
//    private TableView<f> tablePanier;
//    @FXML
//    private TableColumn<f, String> columnProduit;
//    @FXML
//    private TableColumn<f, String> ColumnDescription;
//    @FXML
//    private TableColumn<f, Float> ColumnPrix;
//    @FXML
//    private TableColumn<f, String> ColumnSuppression;
//    @FXML
//    private Label prixTotal;
//    @FXML
//    private Button btnCommande;
//    @FXML
//    private Text decoText;
//    @FXML
//    private Text usernameText;
//    @FXML
//    private ImageView imagePanier;
//    @FXML
//    private Label labPanier;
//    ObservableList<f> lista = FXCollections.observableArrayList();
//    Panier panierNonCommande;
//  Panierservices servicepanier=new Panierservices();
//    paiementservices servicepaiement=new paiementservices();
//    private int idUser=1;
//    private String username="";
//    
//    @FXML
//    private ComboBox<String> comboPaiement;
//    ObservableList<String> listapaiement =FXCollections.observableArrayList( servicepaiement.AfficherModePaiement());
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        comboPaiement.setItems(listapaiement);
//       usernameText.setText(username);
//        calculNbreLignePanier();
//       for(LignePanier lpanier: panierNonCommande.getLignePanier()){
//         f p=new f(lpanier.getId_Lpanier(), lpanier.getProduit().getId(), lpanier.getProduit().getNom_produit(), lpanier.getProduit().getDescription(),lpanier.getProduit().getPrix());
//           lista.add(p);
//       }
//       columnProduit.setCellValueFactory(new PropertyValueFactory<>("Produit"));
//        ColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        ColumnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        ColumnSuppression.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
//        tablePanier.setItems(lista);
//        
//    }
//    Commandeservices servicecommande=new Commandeservices();
//
//    @FXML
//     private void NaviguerHandler(MouseEvent event)throws IOException{
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/panier.fxml"));
//        Parent root = loader.load();
//
//        labPanier.getScene().setRoot(root);
//    }
//    
//    @FXML
//    private void CommandeHandleAction(ActionEvent event) throws IOException {
//      if(lista.size()<1){
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir un panier", ButtonType.OK);
//                    alert.show();
//                    return;
//      }
//        if(comboPaiement.getValue()!=null){
//      paiement  paim=servicepaiement.AfficherPaiement(comboPaiement.getValue().toString());
//
//           
//        commande c=new commande(Float.parseFloat( prixTotal.getText()), new Utilisateur(idUser), panierNonCommande, paim);
//        int cmdId= servicecommande.ajouter(c);
//               
//        c.setRef(cmdId);
//           
//        for (LignePanier lp:panierNonCommande.getLignespanier()){
//         lignedecommande lc=new lignedecommande(c, lp.getProduit());
//            servicecommande.ajouterLigneCommande(lc);
//            
//        }
//        servicepanier.update(panierNonCommande, 1);
//       }else{
//           Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner ou ajouter un mode de paiement", ButtonType.OK);
//                    alert.show();
//                    return;
//       }
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/panier.fxml"));
//        Parent root = loader.load();
//
//        labPanier.getScene().setRoot(root);
//       }
//
//
//    @FXML
//    private void deconnexionHandleAction(MouseEvent event) throws IOException {
//          
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/Login.FXML.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            Stage ThisStage = (Stage) usernameText.getScene().getWindow();
//            stage.show();
//            ThisStage.close();
//    }
//    
//    private void calculNbreLignePanier(){
//         Panier panier= servicepanier.getPanierByUser(idUser);
//   if(panier!=null){
//       labPanier.setText(Integer.toString( panier.getLignespanier().size()));
//       panierNonCommande=panier;
//       prixTotal.setText(Float.toString( panier.getPrix()));
//   }else{
//       panierNonCommande=new Panier();
//   }
//    }
//
//    @FXML
//    private void AjouterModePaiement(ActionEvent event) throws IOException {
//        
//        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/paiement.fxml"));
//            Parent root = loader.load();
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//            ((Stage) labPanier.getScene().getWindow()).close();
//    }
//    public class f{
//   private int idPanier;     
//    private int id; 
//    private String Nom_Produit;
//    private String description;   
//    private float prix;
//    private Hyperlink supprimer;
//
//        public f(int idPanier, int id, String Nom_Produit, String description, float prix) {
//            this.idPanier = idPanier;
//            this.id = id;
//            this.Nom_Produit=Nom_Produit;
//            this.description = description;
//            this.prix = prix;
//            this.supprimer =new Hyperlink("supprimer");
//            
//            
//                this.supprimer.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                   
//
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment supprimer ce produit du panier?", ButtonType.YES, ButtonType.NO);
//                    alert.showAndWait();
//
//                    if (alert.getResult() == ButtonType.YES) {
//                        panierNonCommande.setPrix(panierNonCommande.getPrix()-prix);
//                        servicepanier.update(panierNonCommande, 0);
//                        servicepanier.deleteLignePanier(idPanier);
//                        
//                         calculNbreLignePanier();
//                         lista.clear();
//       for(LignePanier lpanier: panierNonCommande.getLignespanier()){
//          f p=new f(lpanier.getId_Lpanier(), lpanier.getProduit().getId(), lpanier.getProduit().getNom_produit(), lpanier.getProduit().getDescription(),lpanier.getProduit().getPrix());
//           lista.add(p);
//       }
//       tablePanier.setItems(lista);
//                        
//                    }
//                }
//                });
//                }
//            
//
//            
//
//        public int getIdPanier() {
//            return idPanier;
//        }
//
//        public void setIdPanier(int idPanier) {
//            this.idPanier = idPanier;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getNom_Produit() {
//            return Nom_Produit;
//        }
//
//        public void setNom_Produit(String titre) {
//            this.Nom_Produit = Nom_Produit;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
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
//        public Hyperlink getSupprimer() {
//            return supprimer;
//        }
//
//        public void setSupprimer(Hyperlink supprimer) {
//            this.supprimer = supprimer;
//        }
//    
//    
//            
//
//    }
//}
