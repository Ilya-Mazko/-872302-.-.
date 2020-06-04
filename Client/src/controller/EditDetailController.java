package controller;

import connector.DataClass;
import entity.DetailData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import validation.DetailValidation;

import static sample.Main.sendData;

public class EditDetailController extends EditAbstractController {

    private  static DetailData detailData;

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

    public static DetailData getDetailData() {
        return detailData;
    }

    public static void setDetailData(DetailData detailData) {
        EditDetailController.detailData = detailData;
    }

    @Override
    void onClickEdit(ActionEvent actionEvent) {
        int indexCar=getDetailData().getIndexCar();
        int indexDetail=getDetailData().getIndexDetail();
        DetailData detailData = new DetailData();
        DataClass dataClass = new DataClass();
        DetailValidation detailValidation = new DetailValidation();
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
                detailData.setIndexCar(indexCar);
                detailData.setIndexDetail(indexDetail);
                dataClass.setOperation("editDetail");
                dataClass.setDetailObj(detailData);
                sendData(dataClass);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Деталь изменена!");
                alert.showAndWait();
                clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Неверно введеные данные!!!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Поля ввода пустые!!!");
            alert.showAndWait();
        }
    }

    void editDetail(DetailData data){
        setDetailData(data);
    }

    @Override
    void putData(){
        DetailData data= getDetailData();
        nameDetailField.setText(data.getName());
        producerDetailField.setText(data.getProducer());
        markCarField.setText(data.getMark());
        modelCarField.setText(data.getModel());
        typeMotorField.setText(data.getTypeMotor());
        volumeMotorField.setText(String.valueOf(data.getVolumeMotor()));
        priceDetailField.setText(String.valueOf(data.getPrice()));
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
