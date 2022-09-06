package gui.annonceCoachings;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import supportini.Main;
import supportini.MyListener;
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
        myListener.onClickListener(Coachings);
    }

    private Coachings Coachings;
    private MyListener myListener;

    public void setData(Coachings Coachings, MyListener myListener) {
        this.Coachings = Coachings;
        this.myListener = myListener;
        nameLabel.setText(Coachings.getTitre());
      
        String path = Coachings.getImage();
        Image aa = new Image("file:" + path);

        img.setImage(aa);
    }
}
