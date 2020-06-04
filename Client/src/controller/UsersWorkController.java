package controller;

import connector.DataClass;
import entity.DetailData;
import entity.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static sample.Main.getData;
import static sample.Main.sendData;

public class UsersWorkController {

    @FXML
    private TableView<UserData> tableUsers;

    @FXML
    private TableColumn<UserData, String> loginColumn;

    @FXML
    private TableColumn<UserData, String> lastNameColumn;

    @FXML
    private TableColumn<UserData, String> firstNameColumn;

    @FXML
    private TableColumn<UserData, String> countryColumn;

    @FXML
    private TableColumn<UserData, String> ageColumn;

    @FXML
    private TableColumn<UserData, String> statusColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button blockingUserButton;

    @FXML
    private Button delUserButton;

    @FXML
    private Button editUserButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button unblockingUserButton;

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

        updateButton.setOnAction(actionEvent -> {
            update();
        });

        blockingUserButton.setOnAction(actionEvent -> {
            UserData userData=tableUsers.getSelectionModel().getSelectedItem();
            DataClass dataClass=new DataClass();
            dataClass.setUserObj(userData);
            dataClass.setOperation("blockingUser");
            sendData(dataClass);
            update();
        });

        unblockingUserButton.setOnAction(actionEvent -> {
            UserData userData=tableUsers.getSelectionModel().getSelectedItem();
            DataClass dataClass=new DataClass();
            dataClass.setUserObj(userData);
            dataClass.setOperation("unblockingUser");
            sendData(dataClass);
            update();
        });

        delUserButton.setOnAction(actionEvent -> {
            UserData userData=tableUsers.getSelectionModel().getSelectedItem();
            DataClass dataClass=new DataClass();
            dataClass.setUserObj(userData);
            dataClass.setOperation("delUser");
            sendData(dataClass);
            update();
        });

        editUserButton.setOnAction(actionEvent -> {
            UserData userData=tableUsers.getSelectionModel().getSelectedItem();
            if(userData!=null){
                EditUserController controller= new EditUserController();
                controller.editUser(userData);
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/views/EditUser.fxml"));
                    stage.setTitle("Изменение информация о пользователе");
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
                Alert alert = new Alert(Alert.AlertType.ERROR,"Выберите пользователя для изменения!");
                alert.showAndWait();
            }
        });
    }

    void update(){
        DataClass getDataClass;
        DataClass setDataClass=new DataClass();
        setDataClass.setOperation("viewUserData");
        sendData(setDataClass);
        getDataClass = getData();
        ObservableList<UserData> userData = FXCollections.observableArrayList(getDataClass.getUserData());
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("Country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tableUsers.setItems(userData);
    }
}
