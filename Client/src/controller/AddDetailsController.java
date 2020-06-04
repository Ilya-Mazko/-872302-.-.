package controller;


import java.util.Random;


import connector.DataClass;
import entity.DetailData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import validation.DetailValidation;

import static sample.Main.sendData;

public class AddDetailsController {

    @FXML
    private TextField nameDetailField;

    @FXML
    private TextField producerDetailField;

    @FXML
    private TextField markCarField;

    @FXML
    private TextField modelCarField;

    @FXML
    private TextField typeMotorField;

    @FXML
    private TextField volumeMotorField;

    @FXML
    private TextField priceDetailField;

    @FXML
    private Button addDetailButton;

    @FXML
    void initialize() {
        addDetailButton.setOnAction(actionEvent -> {
            DetailData detailData = new DetailData();
            DataClass dataClass = new DataClass();
            DetailValidation detailValidation = new DetailValidation();
            Random random = new Random();
            if (!nameDetailField.getText().equals("") && !producerDetailField.getText().equals("") &&
                    !markCarField.getText().equals("") && !modelCarField.getText().equals("") && !typeMotorField.getText().equals("") && !volumeMotorField.getText().equals("") &&
                    !priceDetailField.getText().equals("")) {
                if (detailValidation.nameDetailValidation(nameDetailField.getText().trim()) && detailValidation.producerDetailValidation(producerDetailField.getText().trim()) &&
                        detailValidation.markDetailValidation(markCarField.getText().trim()) && detailValidation.modelDetailValidation(modelCarField.getText().trim())
                        && detailValidation.typeMotorValidation(typeMotorField.getText().trim()) && detailValidation.volumeMotorValidation(volumeMotorField.getText().trim())
                        && detailValidation.priceDetailValidation(priceDetailField.getText().trim())) {
                    detailData.setName(nameDetailField.getText().trim());
                    detailData.setProducer(producerDetailField.getText().trim());
                    detailData.setMark(markCarField.getText().trim());
                    detailData.setModel(modelCarField.getText().trim());
                    detailData.setTypeMotor(typeMotorField.getText().trim());
                    detailData.setVolumeMotor(Double.valueOf(volumeMotorField.getText().trim()));
                    detailData.setPrice(Integer.parseInt(priceDetailField.getText().trim()));
                    detailData.setIndexDetail(random.nextInt(100000));
                    detailData.setIndexCar(random.nextInt(100000));
                    dataClass.setOperation("addDetail");
                    dataClass.setDetailObj(detailData);
                    sendData(dataClass);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Деталь добавлена!");
                    alert.showAndWait();
                    clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Неверно введеные данные!!!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Поля ввода пустые!!!");
                alert.showAndWait();
                return;
            }
        });
    }

    void clear(){
        nameDetailField.clear();
        producerDetailField.clear();
        priceDetailField.clear();
        markCarField.clear();
        modelCarField.clear();
        typeMotorField.clear();
        volumeMotorField.clear();
        priceDetailField.clear();
    }
}
