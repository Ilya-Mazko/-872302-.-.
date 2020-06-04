package controller;

import connector.DataClass;
import entity.AnalysisData;
import entity.ExpertData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static sample.Main.sendData;

public class AnalysisController {
    private static int analysisId;
    private static int alternativeIndex1;
    private static int alternativeIndex2;
    private static int alternativeIndex3;
    private static int alternativeIndex4;

    @FXML
    private Spinner<Integer> Z12EX1;

    @FXML
    private Spinner<Integer> Z13EX1;

    @FXML
    private Spinner<Integer> Z14EX1;

    @FXML
    private Spinner<Integer> Z23EX1;

    @FXML
    private Spinner<Integer> Z24EX1;

    @FXML
    private Spinner<Integer> Z34EX1;

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
    private Spinner<Integer> Z12EX2;

    @FXML
    private Spinner<Integer> Z13EX2;

    @FXML
    private Spinner<Integer> Z14EX2;

    @FXML
    private Spinner<Integer> Z23EX2;

    @FXML
    private Spinner<Integer> Z24EX2;

    @FXML
    private Spinner<Integer> Z34EX2;

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
    private TextField f11;

    @FXML
    private TextField f12;

    @FXML
    private TextField f13;

    @FXML
    private TextField f14;

    @FXML
    private TextField f21;

    @FXML
    private TextField f22;

    @FXML
    private TextField f23;

    @FXML
    private TextField f24;

    @FXML
    private TextField v11;

    @FXML
    private TextField v12;

    @FXML
    private TextField v13;

    @FXML
    private TextField v14;

    @FXML
    private TextField v21;

    @FXML
    private TextField v22;

    @FXML
    private TextField v23;

    @FXML
    private TextField v24;

    @FXML
    private TextField W1;

    @FXML
    private TextField W2;

    @FXML
    private TextField W3;

    @FXML
    private TextField W4;

    @FXML
    private Button calculationButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField scaleSize;

    @FXML
    private Button scaleButton;


    public static int getAlternativeIndex1() {
        return alternativeIndex1;
    }

    public static void setAlternativeIndex1(int alternativeIndex1) {
        AnalysisController.alternativeIndex1 = alternativeIndex1;
    }

    public static int getAlternativeIndex2() {
        return alternativeIndex2;
    }

    public static void setAlternativeIndex2(int alternativeIndex2) {
        AnalysisController.alternativeIndex2 = alternativeIndex2;
    }

    public static int getAlternativeIndex3() {
        return alternativeIndex3;
    }

    public static void setAlternativeIndex3(int alternativeIndex3) {
        AnalysisController.alternativeIndex3 = alternativeIndex3;
    }

    public static int getAlternativeIndex4() {
        return alternativeIndex4;
    }

    public static void setAlternativeIndex4(int alternativeIndex4) {
        AnalysisController.alternativeIndex4 = alternativeIndex4;
    }

    public static int getAnalysisId() {
        return analysisId;
    }

    public static void setAnalysisId(int analysisId) {
        AnalysisController.analysisId = analysisId;
    }

    @FXML
    void initialize() {
        final int N=12;
        scaleButton.setOnAction(actionEvent -> {
            int size=Integer.parseInt(scaleSize.getText().trim());
            SpinnerValueFactory<Integer> valueFactory12EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory13EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory14EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory23EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory24EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory34EX1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory12EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory13EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory14EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory23EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory24EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            SpinnerValueFactory<Integer> valueFactory34EX2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,size);
            Z11EX1.setText("—");
            Z22EX1.setText("—");
            Z33EX1.setText("—");
            Z44EX1.setText("—");
            Z11EX2.setText("—");
            Z22EX2.setText("—");
            Z33EX2.setText("—");
            Z44EX2.setText("—");
            Z12EX1.setValueFactory(valueFactory12EX1);
            Z13EX1.setValueFactory(valueFactory13EX1);
            Z14EX1.setValueFactory(valueFactory14EX1);
            Z23EX1.setValueFactory(valueFactory23EX1);
            Z24EX1.setValueFactory(valueFactory24EX1);
            Z34EX1.setValueFactory(valueFactory34EX1);
            Z12EX2.setValueFactory(valueFactory12EX2);
            Z13EX2.setValueFactory(valueFactory13EX2);
            Z14EX2.setValueFactory(valueFactory14EX2);
            Z23EX2.setValueFactory(valueFactory23EX2);
            Z24EX2.setValueFactory(valueFactory24EX2);
            Z34EX2.setValueFactory(valueFactory34EX2);
        });
        calculationButton.setOnAction(actionEvent -> {
            int size=Integer.parseInt(scaleSize.getText().trim());
            Z21EX1.setText(String.valueOf(size-Z12EX1.getValue()));
            Z31EX1.setText(String.valueOf(size-Z13EX1.getValue()));
            Z32EX1.setText(String.valueOf(size-Z23EX1.getValue()));
            Z41EX1.setText(String.valueOf(size-Z14EX1.getValue()));
            Z42EX1.setText(String.valueOf(size-Z24EX1.getValue()));
            Z43EX1.setText(String.valueOf(size-Z34EX1.getValue()));

            Z21EX2.setText(String.valueOf(size-Z12EX2.getValue()));
            Z31EX2.setText(String.valueOf(size-Z13EX2.getValue()));
            Z32EX2.setText(String.valueOf(size-Z23EX2.getValue()));
            Z41EX2.setText(String.valueOf(size-Z14EX2.getValue()));
            Z42EX2.setText(String.valueOf(size-Z24EX2.getValue()));
            Z43EX2.setText(String.valueOf(size-Z34EX2.getValue()));

            f11.setText((Z12EX1.getValue() + Z13EX1.getValue() + Z14EX1.getValue()) +"/"+size);
            f12.setText((Integer.parseInt(Z21EX1.getText().trim()) + Z23EX1.getValue() + Z24EX1.getValue()) +"/"+size);
            f13.setText((Integer.parseInt(Z31EX1.getText().trim()) + Integer.parseInt(Z32EX1.getText().trim()) + Z34EX1.getValue()) +"/"+size);
            f14.setText((Integer.parseInt(Z41EX1.getText().trim()) + Integer.parseInt(Z42EX1.getText().trim()) + Integer.parseInt(Z43EX1.getText().trim())) +"/"+size);

            f21.setText((Z12EX2.getValue() + Z13EX2.getValue() + Z14EX2.getValue()) +"/"+size);
            f22.setText((Integer.parseInt(Z21EX2.getText().trim()) + Z23EX2.getValue() + Z24EX2.getValue()) +"/"+size);
            f23.setText((Integer.parseInt(Z31EX2.getText().trim()) + Integer.parseInt(Z32EX2.getText().trim()) + Z34EX2.getValue()) +"/"+size);
            f24.setText((Integer.parseInt(Z41EX2.getText().trim()) + Integer.parseInt(Z42EX2.getText().trim()) + Integer.parseInt(Z43EX2.getText().trim())) +"/"+size);

            v11.setText((Z12EX1.getValue() + Z13EX1.getValue() + Z14EX1.getValue()) +"/"+size+"/"+N);
            v12.setText((Integer.parseInt(Z21EX1.getText().trim()) + Z23EX1.getValue() + Z24EX1.getValue()) +"/"+size+"/"+N);
            v13.setText((Integer.parseInt(Z31EX1.getText().trim()) + Integer.parseInt(Z32EX1.getText().trim()) + Z34EX1.getValue()) +"/"+size+"/"+N);
            v14.setText((Integer.parseInt(Z41EX1.getText().trim()) + Integer.parseInt(Z42EX1.getText().trim()) + Integer.parseInt(Z43EX1.getText().trim())) +"/"+size+"/"+N);

            v21.setText((Z12EX2.getValue() + Z13EX2.getValue() + Z14EX2.getValue()) +"/"+size+"/"+N);
            v22.setText((Integer.parseInt(Z21EX2.getText().trim()) + Z23EX2.getValue() + Z24EX2.getValue()) +"/"+size+"/"+N);
            v23.setText((Integer.parseInt(Z31EX2.getText().trim()) + Integer.parseInt(Z32EX2.getText().trim()) + Z34EX2.getValue()) +"/"+size+"/"+N);
            v24.setText((Integer.parseInt(Z41EX2.getText().trim()) + Integer.parseInt(Z42EX2.getText().trim()) + Integer.parseInt(Z43EX2.getText().trim())) +"/"+size+"/"+N);

            W1.setText(String.valueOf((double)(Z12EX1.getValue() + Z13EX1.getValue() + Z14EX1.getValue()+Z12EX2.getValue() + Z13EX2.getValue() + Z14EX2.getValue())/size/N));
            W2.setText(String.valueOf((double)(Integer.parseInt(Z21EX1.getText().trim()) + Z23EX1.getValue() + Z24EX1.getValue()+Integer.parseInt(Z21EX2.getText().trim()) + Z23EX2.getValue() + Z24EX2.getValue())/size/N));
            W3.setText(String.valueOf((double)(Integer.parseInt(Z31EX1.getText().trim()) + Integer.parseInt(Z32EX1.getText().trim()) + Z34EX1.getValue() + Integer.parseInt(Z31EX2.getText().trim()) + Integer.parseInt(Z32EX2.getText().trim()) + Z34EX2.getValue())/size/N));
            W4.setText(String.valueOf((double)(Integer.parseInt(Z41EX1.getText().trim()) + Integer.parseInt(Z42EX1.getText().trim()) + Integer.parseInt(Z43EX1.getText().trim()) + Integer.parseInt(Z41EX2.getText().trim()) + Integer.parseInt(Z42EX2.getText().trim()) + Integer.parseInt(Z43EX2.getText().trim()))/size/N));

            sendAnalysisData(N,size);
            sendExpertData1();
            sendExpertData2();

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

    public void addId (int index1,int index2,int index3,int index4)
    {
        Random random=new Random();
        setAlternativeIndex1(index1);
        setAlternativeIndex2(index2);
        setAlternativeIndex3(index3);
        setAlternativeIndex4(index4);
        setAnalysisId(random.nextInt(100000));
    }

    public void sendAnalysisData(int N, int size){
        AnalysisData analysisData=new AnalysisData();
        DataClass dataClass=new DataClass();
        analysisData.setAlternativeIndex1(getAlternativeIndex1());
        analysisData.setAlternativeIndex2(getAlternativeIndex2());
        analysisData.setAlternativeIndex3(getAlternativeIndex3());
        analysisData.setAlternativeIndex4(getAlternativeIndex4());
        analysisData.setN(N);
        analysisData.setScaleSize(size);
        analysisData.setW1(Double.valueOf(W1.getText()));
        analysisData.setW2(Double.valueOf(W2.getText()));
        analysisData.setW3(Double.valueOf(W3.getText()));
        analysisData.setW4(Double.valueOf(W4.getText()));
        analysisData.setAnalysisIndex(getAnalysisId());
        dataClass.setOperation("analysisData");
        dataClass.setAnalysisObj(analysisData);
        sendData(dataClass);
    }

    public void sendExpertData1(){
        ExpertData expertData1=new ExpertData();
        DataClass dataClass=new DataClass();
        Random random=new Random();

        expertData1.setAnalysisId(getAnalysisId());
        expertData1.setExpertId(random.nextInt(100000));
        expertData1.setF1(Z12EX1.getValue() + Z13EX1.getValue() + Z14EX1.getValue());
        expertData1.setF2(Integer.parseInt(Z21EX1.getText().trim()) + Z23EX1.getValue() + Z24EX1.getValue());
        expertData1.setF3(Integer.parseInt(Z31EX1.getText().trim()) + Integer.parseInt(Z32EX1.getText().trim()) + Z34EX1.getValue());
        expertData1.setF4(Integer.parseInt(Z41EX1.getText().trim()) + Integer.parseInt(Z42EX1.getText().trim()) + Integer.parseInt(Z43EX1.getText().trim()));

        int zMas1[][]=new int[5][5];
        zMas1[1][2]=Z12EX1.getValue();
        zMas1[1][3]=Z13EX1.getValue();
        zMas1[1][4]=Z14EX1.getValue();
        zMas1[2][1]=Integer.parseInt(Z21EX1.getText().trim());
        zMas1[2][3]=Z23EX1.getValue();
        zMas1[2][4]=Z24EX1.getValue();
        zMas1[3][1]=Integer.parseInt(Z31EX1.getText().trim());
        zMas1[3][2]=Integer.parseInt(Z32EX1.getText().trim());
        zMas1[3][4]=Z34EX1.getValue();
        zMas1[4][1]=Integer.parseInt(Z41EX1.getText().trim());
        zMas1[4][2]=Integer.parseInt(Z42EX1.getText().trim());
        zMas1[4][3]=Integer.parseInt(Z43EX1.getText().trim());
        expertData1.setZ(zMas1);
        dataClass.setOperation("expertData1");
        dataClass.setExpertObj(expertData1);
        sendData(dataClass);

    }

    public void sendExpertData2(){
        ExpertData expertData2=new ExpertData();
        DataClass dataClass=new DataClass();
        Random random=new Random();

        expertData2.setAnalysisId(getAnalysisId());
        expertData2.setExpertId(random.nextInt(100000));
        expertData2.setF1(Z12EX2.getValue() + Z13EX2.getValue() + Z14EX2.getValue());
        expertData2.setF2(Integer.parseInt(Z21EX2.getText().trim()) + Z23EX2.getValue() + Z24EX2.getValue());
        expertData2.setF3(Integer.parseInt(Z31EX2.getText().trim()) + Integer.parseInt(Z32EX2.getText().trim()));
        expertData2.setF4(Integer.parseInt(Z41EX2.getText().trim()) + Integer.parseInt(Z42EX2.getText().trim()) + Integer.parseInt(Z43EX2.getText().trim()));

        int zMas2[][]=new int[5][5];
        zMas2[1][2]=Z12EX2.getValue();
        zMas2[1][3]=Z13EX2.getValue();
        zMas2[1][4]=Z14EX2.getValue();
        zMas2[2][1]=Integer.parseInt(Z21EX2.getText().trim());
        zMas2[2][3]=Z23EX2.getValue();
        zMas2[2][4]=Z24EX2.getValue();
        zMas2[3][1]=Integer.parseInt(Z31EX2.getText().trim());
        zMas2[3][2]=Integer.parseInt(Z32EX2.getText().trim());
        zMas2[3][4]=Z34EX2.getValue();
        zMas2[4][1]=Integer.parseInt(Z41EX2.getText().trim());
        zMas2[4][2]=Integer.parseInt(Z42EX2.getText().trim());
        zMas2[4][3]=Integer.parseInt(Z43EX2.getText().trim());
        expertData2.setZ(zMas2);
        dataClass.setOperation("expertData2");
        dataClass.setExpertObj(expertData2);
        sendData(dataClass);
    }
}
