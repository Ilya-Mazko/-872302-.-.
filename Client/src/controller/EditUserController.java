package controller;

import connector.DataClass;
import entity.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import validation.UserValidation;

import static sample.Main.getData;
import static sample.Main.sendData;

public class EditUserController extends EditAbstractController {

    private static UserData userData;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ageField;

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        EditUserController.userData = userData;
    }

    @Override
    void onClickEdit(ActionEvent actionEvent) {
        UserValidation userValidation=new UserValidation();
        DataClass setDataClass=new DataClass();
        DataClass getDataClass;
        UserData userData=new UserData();
        if(!loginField.getText().equals("") && !passwordField.getText().equals("") && !firstNameField.getText().equals("") &&
                !lastNameField.getText().equals("") && !countryField.getText().equals("") && !ageField.getText().equals("")) {
            if (userValidation.loginValidation(loginField.getText().trim()) && userValidation.passwordValidation(passwordField.getText().trim())
                    && userValidation.firstNameValidation(firstNameField.getText().trim()) && userValidation.lastNameValidation(lastNameField.getText().trim())
                    && userValidation.countryValidation(countryField.getText().trim()) && userValidation.ageValidation(ageField.getText().trim())) {
                userData.setLogin(loginField.getText().trim());
                userData.setPassword(passwordField.getText().trim());
                userData.setFirstName(firstNameField.getText().trim());
                userData.setLastName(lastNameField.getText().trim());
                userData.setCountry(countryField.getText().trim());
                userData.setAge(Integer.parseInt(ageField.getText().trim()));
                userData.setRole(getUserData().isRole());
                userData.setStatus(getUserData().isStatus());
                userData.setIndex(getUserData().getIndex());
                setDataClass.setOperation("editUser");
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
            Alert alert = new Alert(Alert.AlertType.ERROR,"Логин существует!!!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Информация изменена!!!");
            alert.showAndWait();
            clear();
        }
    }

    void editUser(UserData userData){
        setUserData(userData);
    }

    @Override
    void putData(){
        UserData userData=getUserData();
        loginField.setText(userData.getLogin());
        firstNameField.setText(userData.getFirstName());
        lastNameField.setText(userData.getLastName());
        countryField.setText(userData.getCountry());
        ageField.setText(String.valueOf(userData.getAge()));
    }

    void clear(){
        loginField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
        countryField.clear();
        ageField.clear();
    }
}

