package controller;

import java.io.*;

import entity.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import connector.DataClass;
import javafx.stage.Stage;

import static sample.Main.getData;
import static sample.Main.sendData;


public class AboutUserController {

    private static boolean role;

    private static UserData userData;

    @FXML
    private Button backButton;

    @FXML
    private Label lastNameField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label countryField;

    @FXML
    private Label ageField;

    @FXML
    private Label loginField;

    @FXML
    private Label roleField;

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        AboutUserController.userData = userData;
    }

    public static boolean isRole() {
        return role;
    }

    public static void setRole(boolean role) {
        AboutUserController.role = role;
    }

    @FXML
    void initialize() {

        UserData userData=new UserData();
        userData.setLogin(getUserData().getLogin());
        DataClass setDataClass=new DataClass();
        setDataClass.setOperation("aboutUser");
        setDataClass.setUserObj(userData);
        sendData(setDataClass);

        DataClass getDataClass=getData();
        setRole(getDataClass.getUserObj().isRole());
        firstNameField.setText(getDataClass.getUserObj().getFirstName());
        firstNameField.setText(getDataClass.getUserObj().getFirstName());
        lastNameField.setText(getDataClass.getUserObj().getLastName());
        countryField.setText(getDataClass.getUserObj().getCountry());
        ageField.setText(String.valueOf(getDataClass.getUserObj().getAge()));
        loginField.setText(getDataClass.getUserObj().getLogin());
        if(!getDataClass.getUserObj().isRole()){
            roleField.setText("Пользователь");
        }
        else {
            roleField.setText("Администратор");
        }
        backButton.setOnAction(actionEvent -> {
            if (isRole()) {
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
            }
            else{
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
            }
        });
    }

    void aboutUser(UserData data){
        setUserData(data);
    }
}
