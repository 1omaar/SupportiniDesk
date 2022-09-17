/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supportini;

import Exception.AuthException;
import gui.profil.ProfilFXMLController;
import interfaces.IDemandeSuivi;
import interfaces.Isuivi;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import model.Suivi;
import org.json.JSONException;
import services.DemandeSuivi_Service;
import services.Suivie_Services;
import util.JWebToken;

/**
 *
 * @author Elife-Kef-130
 */
public class Supportini {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Preferences userPreferences = Preferences.userRoot();
        String bearerToken = userPreferences.get("BearerToken", "root");
        //verify and use
        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(bearerToken);
            String audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
            //int idRole = Integer.parseInt(audience);
            int idUser = Integer.parseInt(subject);
            Suivi suivi = new Suivi();
            Isuivi is = new Suivie_Services();
            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            
            //is.ajouterSuivi(s);
            Suivi s = new Suivi(idUser, 55, 66, 77, date, "Mouhib", "Hayouni");
            is.ajouterSuivi(s);
        } catch (JSONException | AuthException | IOException ex) {
            Logger.getLogger(ProfilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDemandeSuivi ids = new DemandeSuivi_Service();
        System.out.println(ids.afficherDemandeSuivi(4).getDemande());
        
    }

}
