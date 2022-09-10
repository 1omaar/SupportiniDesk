/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.suivi.histosuivi;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Suivi;

/**
 * FXML Controller class
 *
 * @author GIGABYTE
 */
public class HistoSuiviController implements Initializable {

    @FXML
    private Label NomEntrainer;
    @FXML
    private Label PrenomEntrainer;
    @FXML
    private Label DateSuivi;
    @FXML
    private Label PoidsActuelle;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private DatePicker datepicked;
    @FXML
    private Label dateshow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //Suivi s = (datepicked.getValue());
        
    }    

    @FXML
    private void show(ActionEvent event) {
        
//         //if (datePicker.getValue()== )
//        NomEntrainer.setText(s.getNomE());
//        PrenomEntrainer.setText(s.getPrenomE());
//        DateSuivi.setText(String.valueOf(s.getDate()));
//        PoidsActuelle.setText(String.valueOf(s.getPoidsActuelle()));
//
//        XYChart.Series series = new XYChart.Series();
//        series.setName("Poids");
//
//        series.getData().add(new XYChart.Data("Jan", 100));
//
//        chart.getData().add(series);

    }
        
        
    }
    

