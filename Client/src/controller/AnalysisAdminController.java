package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AnalysisAdminController {

    @FXML
    private Button createAnalysisButton;

    @FXML
    private Button viewAnalysisButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        createAnalysisButton.setOnAction(actionEvent -> {
            createAnalysisButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AlternativeChoice.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Выбор альтернатив");
            stage.setResizable(false);
            stage.show();
        });
        viewAnalysisButton.setOnAction(actionEvent -> {
            viewAnalysisButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/AnalysisResult.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Результаты анализа");
            stage.setResizable(false);
            stage.show();
        });
        exitButton.setOnAction(actionEvent -> {
            exitButton.getScene().getWindow().hide();
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
    }
}

