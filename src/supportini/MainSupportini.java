/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supportini;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        
      try {
            if (!bearerToken.equals("root")) {
           
                root = FXMLLoader.load(getClass().getResource("../gui/dashboard/DashboardFXML.fxml"));
        
                primaryStage.setTitle("Dashboard");
            } else {
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
                Logger.getLogger(MainSupportini.class.getName()).log(Level.SEVERE, null, ex);
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

    public static Stage getPrimaryStage() {
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



 //recieve the bearer token
//        Preferences userPreferences = Preferences.userRoot();
//        String bearerToken = userPreferences.get("BearerToken", "root");
//        //verify and use
//        JWebToken incomingToken;
//        try {
//            incomingToken = new JWebToken(bearerToken);
//               if (!incomingToken.isValid()) {
//
////                get id and idRole for current user
//                String audience = incomingToken.getAudience();
//                String subject = incomingToken.getSubject();
//                idRole= Integer.parseInt(audience);
//                 idUser =        Integer.parseInt(subject);
//                
//            }
//        } catch (JSONException | AuthException | IOException | InvalidKeyException ex) {
//            Logger.getLogger(CoachingFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }