package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainControllerAdmin {

    @FXML
    private Button carPartWorkButton;

    @FXML
    private Button analysisButton;

    @FXML
    private Button aboutUserButton;

    @FXML
    private Button usersWorkButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        carPartWorkButton.setOnAction(actionEvent -> {
            carPartWorkButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/DetailsAdmin.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Работа с запчастями");
            stage.setResizable(false);
            stage.show();
        });
        analysisButton.setOnAction(actionEvent -> {
            analysisButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AnalysisAdminMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Экспертный анализ");
            stage.setResizable(false);
            stage.show();
        });
        usersWorkButton.setOnAction(actionEvent -> {
            usersWorkButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/UsersWork.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Работа с пользователями");
            stage.setResizable(false);
            stage.show();
        });
        aboutUserButton.setOnAction(actionEvent -> {
            aboutUserButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AboutUser.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Информация о пользователе");
            stage.setResizable(false);
            stage.show();
        });
        exitButton.setOnAction(actionEvent -> {
            exitButton.getScene().getWindow().hide();
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
