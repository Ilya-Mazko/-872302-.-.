package controller;

import java.io.IOException;

import connector.DataClass;
import entity.DetailData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static sample.Main.getData;
import static sample.Main.sendData;

public class DetailsControllerAdmin {

    @FXML
    private Label modelCarField;

    @FXML
    private Label typeMotorField;

    @FXML
    private Label volumeMotorField;

    @FXML
    private Label priceField;

    @FXML
    private Button delDetailButton;

    @FXML
    private Button editDetailButton;

    @FXML
    private TableView<DetailData> tableDetail;

    @FXML
    private TableColumn<DetailData, String> nameColumn;

    @FXML
    private TableColumn<DetailData, String> producerColumn;

    @FXML
    private TableColumn<DetailData, String> markColumn;

    @FXML
    private Button updateDetailButton;

    @FXML
    private Button backButton;


    @FXML
    void initialize() {
        update();

        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/MainAdmin.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Главное меню");
            stage.setResizable(false);
            stage.show();
        });

        updateDetailButton.setOnAction(actionEvent -> {
            DataClass getDataClass;
            DataClass setDataClass=new DataClass();
            setDataClass.setOperation("viewDetailData");
            sendData(setDataClass);
            getDataClass = getData();
            ObservableList<DetailData> detailData = FXCollections.observableArrayList(getDataClass.getDetailData());
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));
            markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
            tableDetail.setItems(detailData);
            showDetails(null);
            tableDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
        });

        editDetailButton.setOnAction(actionEvent -> {
            DetailData detailData=tableDetail.getSelectionModel().getSelectedItem();
            if(detailData!=null){
                EditDetailController controller=new EditDetailController();
                controller.editDetail(detailData);
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/EditDetail.fxml"));
                    stage.setTitle("Изменение запчасти");
                    stage.setScene(new Scene(root, 492, 497));
                    stage.setResizable(false);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Выберите запчасть для изменения!");
                alert.showAndWait();
            }
        });

        delDetailButton.setOnAction(actionEvent -> {
            DetailData detailData=tableDetail.getSelectionModel().getSelectedItem();
            DataClass dataClass=new DataClass();
            dataClass.setDetailObj(detailData);
            dataClass.setOperation("delDetail");
            sendData(dataClass);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Запчасть успешно удалена!");
            alert.showAndWait();
            update();
        });
    }

    void showDetails(DetailData detailData){
        if(detailData!=null){
            modelCarField.setText(detailData.getModel());
            typeMotorField.setText(detailData.getTypeMotor());
            volumeMotorField.setText(String.valueOf(detailData.getVolumeMotor()));
            priceField.setText(String.valueOf(detailData.getPrice()));
        }
        else {
            modelCarField.setText("");
            typeMotorField.setText("");
            volumeMotorField.setText("");
            priceField.setText("");
        }
    }

    void update(){
        DataClass getDataClass;
        DataClass setDataClass=new DataClass();
        setDataClass.setOperation("viewDetailData");
        sendData(setDataClass);
        getDataClass = getData();
        ObservableList<DetailData> detailData = FXCollections.observableArrayList(getDataClass.getDetailData());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));
        markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
        tableDetail.setItems(detailData);
        showDetails(null);
        tableDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
    }

    @FXML
    void addDetailWindow(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/views/AddDetail.fxml"));
            stage.setTitle("Добавление запчасти");
            stage.setScene(new Scene(root, 492, 497));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
