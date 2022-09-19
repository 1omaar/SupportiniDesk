//package gestionpaiement;
//import interfaces.IProduits;
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//import java.util.Optional;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import model.LignePanier;
//import model.Panier;
//import model.Produit;
//import model.Utilisateur;
//import services.Panierservices;
//import services.Produitservices;
//public class PanierController implements Initializable {
//     @FXML
//   private AnchorPane rt;
//
//    private Label labelnom_produit;
//    private Label labelPrix;
//    private Text labelDescription;
//    private ImageView img;
//    IProduits ps=new Produitservices();
//    @FXML
//    private Text decoText;
//    @FXML
//    private Text usernameText;
//    @FXML
//    private ImageView imagePanier;
//    @FXML
//    private Label labPanier;
//   Panierservices servicepanier =new Panierservices();
//    Panier panierNonCommande;
//    
//    @FXML
//    private ScrollPane  panelProduit;
//    @FXML
//    private VBox panelAllPanier;
//    @FXML
//    private Button btnVider;
//    private int id_user=1;
//
// 
//
//    private void calculNbreLignePanier(){
//         Panier panier= servicepanier.getPanierByUser(id_user);
//    panelAllPanier.getChildren().clear();
//         if(panier!=null){
//       labPanier.setText(Integer.toString( panier.getLignespanier().size()));
//       panierNonCommande=panier;
//       
//     
//        for(LignePanier lp: panier.getLignespanier()){
//                
//    panelAllPanier.getChildren().add(new Text("     ♠ "+ lp.getProduit().getNom_produit()));
//   }
//        Button bt=new Button("passer la commande");
//       bt.setStyle("-fx-background-color: #ffc103");
//           bt.setTextFill(Color.WHITE);
//           bt.setPrefWidth(137);
//            
//     EventHandler<ActionEvent> buttonHandlerPasserCommande = new EventHandler<ActionEvent>() {
//    @Override
//    public void handle(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/commander.fxml"));
//            Parent root = loader.load();
//            
//            labPanier.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//     };
//     bt.setOnAction(buttonHandlerPasserCommande);
//        
//   panelAllPanier.getChildren().add(bt); 
//
//     
//   panelAllPanier.setSpacing(10);
//          }else{
//       panierNonCommande=new Panier();
//      labPanier.setText("0");
//   }
//
//    }
//    
//    private void remplirPanelProduit(){
//         VBox box = new VBox();
//        panelProduit.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        
//        List<Produit> listProduit= ps.DisplayAllproduit();
//        
//        for(Produit p : listProduit){
//            Produit_Panier Pp=new Produit_Panier(p.getId(), p.getNom_produit(), p.getDescription(),p.getImage(), p.getPrix());
//            Pp.setProduit(p);
//            VBox vboxTitre_Description_Image_prix=new VBox();
//            Text description=new Text(p.getDescription());
//            description.setWrappingWidth(140);
////            String Image=p.getImage();
////            String path = p.getImage();
////        Image aa = new Image("file:" + path);
////
////       img.setImage(aa);
//       
//            vboxTitre_Description_Image_prix.getChildren().addAll(new Label(p.getNom_produit()),description,new Label(p.getImage()),new Label(Float.toString(p.getPrix())));
//            vboxTitre_Description_Image_prix.setPrefWidth(200);
//            vboxTitre_Description_Image_prix.setPadding(new Insets(0, 20, 0, 20));
//            
//            HBox flowPane=new HBox();
//           
//            
//           
////        ImageView img=new ImageView();
////        
////         Image image = new Image("img/shopping cart.jpg");
////         img.setFitHeight(80);
////         img.setFitWidth(80);
////
////        img.setImage(image);
////        img.setCache(true);
////         HBox hbImage=new HBox(img);
////        
//            
//            
//             flowPane.getChildren().addAll(vboxTitre_Description_Image_prix,Pp.getBtn());
//
//            
//            
//            box.getChildren().add(flowPane);
//            box.setSpacing(60);
//           
//        }
//        
//        panelProduit.setContent(box);
//    }
//    
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//  calculNbreLignePanier();
//  remplirPanelProduit();
//    }
// @FXML
//   private void deconnexionHandleAction(MouseEvent event)throws IOException{
//       
//            
//            
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login/LoginFXML.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            Stage ThisStage = (Stage) usernameText.getScene().getWindow();
//            stage.show();
//            ThisStage.close();
//            
//            
//    }
//
//    @FXML
//    private void CommandeViderAction(ActionEvent event) {
//        
// servicepanier.deletePanier(panierNonCommande.getId());
//         calculNbreLignePanier();
//         remplirPanelProduit();
//    }
//
//
//    public class Produit_Panier{
//        private int id;
//        private String nom_Produit;
//        private String description;
//        private String Image;
//        private float prix;
//        private Produit produit;
//        private Button btn;
//      
//
//        public Produit_Panier(int id, String nom_Produit, String description,  float prix) {
//            this.id = id;
//            this.nom_Produit = nom_Produit;
//            this.description = description;
//             this.prix = prix;
//        }
//
//        public Produit_Panier(int id, String nom_Produit, String description,String Image, float prix) {
//            this.id = id;
//            this.nom_Produit=nom_Produit;
//            this.description = description;
//            this.Image = Image;
//            this.prix = prix;
//           
//           btn=new Button();
//            this.btn.setText("ajouter au panier");
//            final int idd=id;
//            btn.setStyle("-fx-background-color: #ffb303");
//            btn.setTextFill(Color.WHITE);
//            
//    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
//    @Override
//    public void handle(ActionEvent event) {
//       if(panierNonCommande.getId()==0 && panierNonCommande.getLignespanier().size()<1){
//            panierNonCommande.setPrix(getProduit().getPrix());
//            panierNonCommande.setId_user(id_user);
//            
//           int idpanierCree= servicepanier.ajouter(panierNonCommande);
//           if(idpanierCree>0){
//               panierNonCommande.setId(idpanierCree);
//               LignePanier ln=new LignePanier(panierNonCommande, getProduit());
//               int idligne=servicepanier.ajouterLignePanier(ln);
//               ln.setId_Lpanier(idligne);
//               panierNonCommande.getLignespanier().add(ln);
//           }
//        }else{
//            for(LignePanier lpp:panierNonCommande.getLignespanier()){
//            if(lpp.getProduit().getNom_produit().equals(getProduit().getNom_produit())){
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Produit dèja en panier", ButtonType.CLOSE);
//                alert.show();
//                return;
//            }
//        }
////            
//           panierNonCommande.setPrix(panierNonCommande.getPrix()+getProduit().getPrix());
//           int idpanierCree= servicepanier.update(panierNonCommande,0);
//           if(idpanierCree>0){
//
//               LignePanier ln=new LignePanier(panierNonCommande, getProduit());
//               int idligne=servicepanier.ajouterLignePanier(ln);
//               ln.setId_Lpanier(idligne);
//               panierNonCommande.getLignespanier().add(ln);
//           }
//        }
//        calculNbreLignePanier();
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous finaliser votre commande?", ButtonType.OK, ButtonType.NO);
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/panier/commander.fxml"));
//        Parent root = null;
//           try {
//               root = loader.load();
//           } catch (IOException ex) {
//               Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//           }
//
//        labPanier.getScene().setRoot(root);
//        }
//    }
//    
//   
//};
//      
//    btn.setOnAction(buttonHandler);
//            
//        }
//
//        public Produit getProduit() {
//            return produit;
//        }
//
//        public void setProduit(Produit produit) {
//            this.produit = produit;
//        }
//
//        
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
//            return nom_Produit;
//        }
//
//        public void setNom_Produit(String nom_Produit) {
//            this.nom_Produit= nom_Produit;
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
//        public String getImage() {
//            return Image;
//        }
//
//        public void setImage(String Image) {
//            this.Image = Image;
//        }
//        
//        public Button getBtn() {
//            return btn;
//        }
//
//        public void setBtn(Button btn) {
//            this.btn = btn;
//        }
//        
//
//        
//    }
//    
//}
