package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import connector.DataClass;
import entity.AnalysisData;
import entity.DetailData;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import static sample.Main.getData;
import static sample.Main.sendData;

public class AlternativeChoiceController {


    @FXML
    private ChoiceBox<DetailData> choiceBox1;

    @FXML
    private ChoiceBox<DetailData> choiceBox2;

    @FXML
    private ChoiceBox<DetailData> choiceBox3;

    @FXML
    private ChoiceBox<DetailData> choiceBox4;

    @FXML
    private Button onwardButton;

    @FXML
    void initialize() {
        DataClass getDataClass;
        DataClass setDataClass=new DataClass();
        setDataClass.setOperation("viewDetailData");
        sendData(setDataClass);
        getDataClass = getData();
        choiceBox1.setItems(FXCollections.observableList(getDataClass.getDetailData()));
        choiceBox2.setItems(FXCollections.observableArrayList(FXCollections.observableList(getDataClass.getDetailData())));
        choiceBox3.setItems(FXCollections.observableArrayList(FXCollections.observableList(getDataClass.getDetailData())));
        choiceBox4.setItems(FXCollections.observableArrayList(FXCollections.observableList(getDataClass.getDetailData())));
        onwardButton.setOnAction(actionEvent -> {
           AnalysisController controller=new AnalysisController();
            controller.addId(choiceBox1.getValue().getIndexDetail(),choiceBox2.getValue().getIndexDetail(),choiceBox3.getValue().getIndexDetail(),choiceBox4.getValue().getIndexDetail());
            onwardButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Analysis.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Анализ");
            stage.setResizable(false);
            stage.show();
        });
    }
}
