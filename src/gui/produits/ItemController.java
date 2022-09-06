/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.produits;
import interfaces.MyListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class ItemController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    

     @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Prod);
    }

    private Produit Prod;
    private MyListener myListener;

    public void setData(Produit Prod, MyListener myListener) {
        this.Prod = Prod;
        this.myListener = myListener;
        nameLabel.setText(Prod.getNom_produit());
        priceLable.setText(Prod.getPrix()+"DT" );
        String path = Prod.getImage();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }
    
}
