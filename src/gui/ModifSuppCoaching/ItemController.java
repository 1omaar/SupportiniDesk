package gui.ModifSuppCoaching;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import interfaces.CoachingsListener;
import model.Coachings;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        CoachingsListener.onClickListener(Coachings);
    }

    private Coachings Coachings;
    private CoachingsListener CoachingsListener;

    public void setData(Coachings Coachings, CoachingsListener CoachingsListener) {
        this.Coachings = Coachings;
        this.CoachingsListener = CoachingsListener;
        nameLabel.setText(Coachings.getTitre());
      
        String path = Coachings.getImage();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }
}
