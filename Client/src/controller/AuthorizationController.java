package controller;

import java.io.IOException;

import entity.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import connector.DataClass;
import validation.UserValidation;

import static hashcode.HashCode.hasher;
import static sample.Main.getData;
import static sample.Main.sendData;

public class AuthorizationController {

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private Button authSignInButton;

    @FXML
    void initialize() {

        loginSignUpButton.setOnAction(event ->{
           loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Registration.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Регистрация");
            stage.setResizable(false);
            stage.show();
        } );

        authSignInButton.setOnAction(actionEvent -> {
            UserValidation userValidation=new UserValidation();
            DataClass setDataClass=new DataClass();
            DataClass getDataClass;
            UserData userData=new UserData();
            if(!login_field.getText().equals("") && !password_field.getText().equals("")) {
                if (userValidation.loginValidation(login_field.getText().trim()) && userValidation.passwordValidation(password_field.getText().trim())) {
                    userData.setLogin(login_field.getText().trim());
                    userData.setPassword(hasher(password_field.getText().trim()));
                    setDataClass.setOperation("authorization");
                    setDataClass.setUserObj(userData);
                    sendData(setDataClass);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Неверные данные!!!");
                    alert.showAndWait();
                    return;
                }
            } else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Поля ввода пустые!!!");
                alert.showAndWait();
                return;
            }

            getDataClass=getData();
            if(!getDataClass.isResult()){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Пользователь не найден!!!");
                alert.showAndWait();
                return;
            }
            if(!getDataClass.getUserObj().isStatus()){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Пользователь заблокирован!!!");
                alert.showAndWait();
                return;
            }
            else{
                AnalysisResultController analysisResultController= new AnalysisResultController();
                analysisResultController.addRole(getDataClass.getUserObj().isRole());
                AboutUserController controller=new AboutUserController();
                controller.aboutUser(setDataClass.getUserObj());
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Вход выполнен!");
                alert.showAndWait();

                if (getDataClass.getUserObj().isRole()){
                    authSignInButton.getScene().getWindow().hide();
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
                else {
                    authSignInButton.getScene().getWindow().hide();
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

            }
        });
    }
}

