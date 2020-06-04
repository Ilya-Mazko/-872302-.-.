package controller;

import connector.DataClass;
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
import validation.UserValidation;

import java.io.IOException;
import java.util.Random;

import static hashcode.HashCode.hasher;
import static sample.Main.getData;
import static sample.Main.sendData;

public class RegistrationController {

    @FXML
    private TextField signUpLogin;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpCountry;


    @FXML
    private TextField signUpAge;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            Random random = new Random();
            UserValidation userValidation=new UserValidation();
            DataClass setDataClass=new DataClass();
            DataClass getDataClass;
            UserData userData=new UserData();
            if(!signUpLogin.getText().equals("") && !signUpPassword.getText().equals("") && !signUpFirstName.getText().equals("") &&
                    !signUpLastName.getText().equals("") && !signUpCountry.getText().equals("") && !signUpAge.getText().equals("")) {
                if (userValidation.loginValidation(signUpLogin.getText().trim()) && userValidation.passwordValidation(signUpPassword.getText().trim())
                        && userValidation.firstNameValidation(signUpFirstName.getText().trim()) && userValidation.lastNameValidation(signUpLastName.getText().trim())
                        && userValidation.countryValidation(signUpCountry.getText().trim()) && userValidation.ageValidation(signUpAge.getText().trim())) {
                    userData.setLogin(signUpLogin.getText().trim());
                    userData.setPassword(hasher(signUpPassword.getText().trim()));
                    userData.setFirstName(signUpFirstName.getText().trim());
                    userData.setLastName(signUpLastName.getText().trim());
                    userData.setCountry(signUpCountry.getText().trim());
                    userData.setAge(Integer.parseInt(signUpAge.getText().trim()));
                    userData.setRole(false);
                    userData.setStatus(true);
                    userData.setIndex(random.nextInt(100000));
                    setDataClass.setOperation("registration");
                    setDataClass.setUserObj(userData);
                    sendData(setDataClass);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Неверные данные!!!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Поля ввода пустые!!!");
                alert.showAndWait();
                return;
            }

            getDataClass=getData();
            if(!getDataClass.isResult()){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Пользователь существует!!!");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Пользователь зарегистрирован!!!");
                alert.showAndWait();
                signUpButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/Authorization.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Authorization.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("My Course Project");
            stage.setResizable(false);
            stage.show();
        });
    }
}
