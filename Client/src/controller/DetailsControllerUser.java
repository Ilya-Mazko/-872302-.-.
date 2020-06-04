package controller;

import java.io.IOException;

import connector.DataClass;
import entity.DetailData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static sample.Main.getData;
import static sample.Main.sendData;

public class DetailsControllerUser {

    @FXML
    private Label modelCarField;

    @FXML
    private Label typeMotorField;

    @FXML
    private Label volumeMotorField;

    @FXML
    private Label priceField;

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
        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/MainUser.fxml"));
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

}
