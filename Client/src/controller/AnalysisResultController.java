package controller;

import connector.DataClass;
import entity.AnalysisData;
import entity.ExpertData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static sample.Main.getData;
import static sample.Main.sendData;

public class AnalysisResultController {
    private int i=0;
    private int j=0;
    private static boolean role;
    @FXML
    private TextField Z11EX1;

    @FXML
    private TextField Z22EX1;

    @FXML
    private TextField Z33EX1;

    @FXML
    private TextField Z44EX1;

    @FXML
    private TextField Z21EX1;

    @FXML
    private TextField Z31EX1;

    @FXML
    private TextField Z32EX1;

    @FXML
    private TextField Z41EX1;

    @FXML
    private TextField Z42EX1;

    @FXML
    private TextField Z43EX1;

    @FXML
    private TextField Z12EX1;

    @FXML
    private TextField Z13EX1;

    @FXML
    private TextField Z14EX1;

    @FXML
    private TextField Z23EX1;

    @FXML
    private TextField Z24EX1;

    @FXML
    private TextField Z34EX1;

    @FXML
    private TextField Z22EX2;

    @FXML
    private TextField Z11EX2;

    @FXML
    private TextField Z33EX2;

    @FXML
    private TextField Z44EX2;

    @FXML
    private TextField Z21EX2;

    @FXML
    private TextField Z32EX2;

    @FXML
    private TextField Z31EX2;

    @FXML
    private TextField Z43EX2;

    @FXML
    private TextField Z42EX2;

    @FXML
    private TextField Z41EX2;

    @FXML
    private TextField Z12EX2;

    @FXML
    private TextField Z13EX2;

    @FXML
    private TextField Z14EX2;

    @FXML
    private TextField Z24EX2;

    @FXML
    private TextField Z23EX2;

    @FXML
    private TextField Z34EX2;

    @FXML
    private TextField W1;

    @FXML
    private TextField W2;

    @FXML
    private TextField W3;

    @FXML
    private TextField W4;

    @FXML
    private Button onwardsButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField alternativeField1;

    @FXML
    private TextField alternativeField2;

    @FXML
    private TextField alternativeField3;

    @FXML
    private TextField alternativeField4;

    public static boolean isRole() {
        return role;
    }

    public static void setRole(boolean role) {
        AnalysisResultController.role = role;
    }

    @FXML
    void initialize() {
        DataClass setDataClass=new DataClass();
        setDataClass.setOperation("analysisResult");
        sendData(setDataClass);

        DataClass getDataClass=getData();
        ArrayList<ExpertData> expertDataArrayList=new ArrayList<>(getDataClass.getExpertData());
        ArrayList<AnalysisData> analysisDataArrayList=new ArrayList<>(getDataClass.getAnalysisData());

        onwardsButton.setOnAction(actionEvent -> {
            if(analysisDataArrayList.size()-1<=i){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Вы просмотрели все экспертные анализы!");
                alert.showAndWait();

                if(isRole()) {
                    onwardsButton.getScene().getWindow().hide();
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
                    return;
                }
                else{
                    onwardsButton.getScene().getWindow().hide();
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
                    return;
                }
            }
            alternativeField1.setText(analysisDataArrayList.get(i).getAlternativeName1());
            alternativeField2.setText(analysisDataArrayList.get(i).getAlternativeName2());
            alternativeField3.setText(analysisDataArrayList.get(i).getAlternativeName3());
            alternativeField4.setText(analysisDataArrayList.get(i).getAlternativeName4());

            W1.setText(String.valueOf(analysisDataArrayList.get(i).getW1()));
            W2.setText(String.valueOf(analysisDataArrayList.get(i).getW2()));
            W3.setText(String.valueOf(analysisDataArrayList.get(i).getW3()));
            W4.setText(String.valueOf(analysisDataArrayList.get(i).getW4()));

            int zMas1[][]=expertDataArrayList.get(j).getZ();
            int zMas2[][]=expertDataArrayList.get(j+1).getZ();

            Z11EX1.setText("—");
            Z22EX1.setText("—");
            Z33EX1.setText("—");
            Z44EX1.setText("—");
            Z11EX2.setText("—");
            Z22EX2.setText("—");
            Z33EX2.setText("—");
            Z44EX2.setText("—");

            Z12EX1.setText(String.valueOf(zMas1[1][2]));
            Z13EX1.setText(String.valueOf(zMas1[1][3]));
            Z14EX1.setText(String.valueOf(zMas1[1][4]));
            Z21EX1.setText(String.valueOf(zMas1[2][1]));
            Z23EX1.setText(String.valueOf(zMas1[2][3]));
            Z24EX1.setText(String.valueOf(zMas1[2][4]));
            Z31EX1.setText(String.valueOf(zMas1[3][1]));
            Z32EX1.setText(String.valueOf(zMas1[3][2]));
            Z34EX1.setText(String.valueOf(zMas1[3][4]));
            Z41EX1.setText(String.valueOf(zMas1[4][1]));
            Z42EX1.setText(String.valueOf(zMas1[4][2]));
            Z43EX1.setText(String.valueOf(zMas1[4][3]));

            Z12EX2.setText(String.valueOf(zMas2[1][2]));
            Z13EX2.setText(String.valueOf(zMas2[1][3]));
            Z14EX2.setText(String.valueOf(zMas2[1][4]));
            Z21EX2.setText(String.valueOf(zMas2[2][1]));
            Z23EX2.setText(String.valueOf(zMas2[2][3]));
            Z24EX2.setText(String.valueOf(zMas2[2][4]));
            Z31EX2.setText(String.valueOf(zMas2[3][1]));
            Z32EX2.setText(String.valueOf(zMas2[3][2]));
            Z34EX2.setText(String.valueOf(zMas2[3][4]));
            Z41EX2.setText(String.valueOf(zMas2[4][1]));
            Z42EX2.setText(String.valueOf(zMas2[4][2]));
            Z43EX2.setText(String.valueOf(zMas2[4][3]));


            i=i+2;
            j=j+2;

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

    void addRole(boolean role){
        setRole(role);
    }
}

