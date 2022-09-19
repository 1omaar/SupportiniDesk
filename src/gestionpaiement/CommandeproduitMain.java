
package gestionpaiement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Anis-PC
 */
public class CommandeproduitMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        


        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/panier/consulterCommande_Produit.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion des commandesProduit");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(CommandeproduitMain .class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
