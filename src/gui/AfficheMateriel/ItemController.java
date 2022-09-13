package gui.AfficheMateriel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import model.MaterielSalle;
import main.MaterielListener;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(MaterielSalle);
    }

    private MaterielSalle MaterielSalle;
    private MaterielListener myListener;

    public void setData(MaterielSalle MaterielSalle, MaterielListener myListener) {
        this.MaterielSalle = MaterielSalle;
        this.myListener = myListener;
        nameLabel.setText(MaterielSalle.getNomMaterial());
      
        String path = MaterielSalle.getImageVitrine();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }
}
