package gui.TousLesRegimes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import interfaces.RegimeListen;
import model.Regime;

public class ItemController {

   @FXML
    private Label nameLabel;
    private Label TxtType;


    private Label nbkg;

    @FXML
    private void click(MouseEvent mouseEvent) {
        RegimeListen.onClickListener(Regime);
    }

    private Regime Regime;
    private RegimeListen RegimeListen;

    public void setData(Regime Regime, RegimeListen RegimeListen) {
        this.Regime = Regime;
        this.RegimeListen = RegimeListen;
        nameLabel.setText(Regime.getNom());
//        nbkg.setText(String.valueOf(Regime.getNbkg()));
//        TxtType.setText(Regime.getType());
    }
}
