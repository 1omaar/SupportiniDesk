/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supportini;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Elife-Kef-130
 */
public class MainSupportini extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
           Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        
        System.out.println(bearerToken);
        try {
            if(!bearerToken.equals("root")){
                         root = FXMLLoader.load(getClass().getResource("../gui/dashboard/DashboardFXML.fxml"));
             primaryStage.setTitle("Dashboard"); 
            }else {
                          root = FXMLLoader.load(getClass().getResource("../gui/login/LoginFXML.fxml"));
             primaryStage.setTitle("Se Connecter chez Supportini");
            }
  
            Scene scene = new Scene(root);
            Image icon;
            icon = new Image(getClass().getResourceAsStream("../gui/uicontrolers/logosportstrnsprt.png"));
            primaryStage.getIcons().add(icon);
           
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();

            primaryStage.show();

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

     private static Stage primaryStage; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        MainSupportini.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return MainSupportini.primaryStage;
    }
    /**
     * Requests an authorization code from the auth server
     *
     * @return
     * @throws MalformedURLException
     * @throws URISyntaxException
     * @throws AuthorizationException
     */
}
