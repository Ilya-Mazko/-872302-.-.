package sample;

import DAO.AnalysisDAO;
import DAO.DetailDAO;
import DAO.ExpertDAO;
import DAO.UserDAO;
import connection.ConnectionPool;
import connector.DataClass;
import entity.AnalysisData;
import entity.DetailData;
import entity.ExpertData;
import entity.UserData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main extends Application implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.create("jdbc:mysql://localhost:3306/coursedatabase?serverTimezone=UTC", "root","root");
        new Thread(new Main()).start();
        launch(args);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5710, 10);
            while (true) {
                socket = serverSocket.accept();
                while (true) {
                    DataClass setDataClass = new DataClass();
                    DataClass getDataClass = getData();
                    switch (getDataClass.getOperation()) {
                        case "registration": {
                            int counter = 0;
                            UserDAO userDAO = new UserDAO();
                            ResultSet resultSet = userDAO.registrationUserCheck(getDataClass.getUserObj().getLogin());
                            try {
                                while (resultSet.next()) {
                                    counter++;
                                }
                            } catch (SQLException e) {
                                System.out.println("Error:" + e);
                            }
                            if (counter != 0) {
                                System.out.println("User exists");
                                setDataClass.setResult(false);
                                sendData(setDataClass);
                            } else {
                                userDAO.inputUserInDataBase(getDataClass.getUserObj().getIndex(),getDataClass.getUserObj().getLogin(),
                                        getDataClass.getUserObj().getPassword(), getDataClass.getUserObj().getFirstName(), getDataClass.getUserObj().getLastName(),
                                        getDataClass.getUserObj().getCountry(), getDataClass.getUserObj().getAge(),getDataClass.getUserObj().isRole(),getDataClass.getUserObj().isStatus());
                                setDataClass.setResult(true);
                                sendData(setDataClass);
                                System.out.println("User add");
                            }
                            break;
                        }
                        case "authorization": {
                            int counter = 0;
                            UserDAO userDAO = new UserDAO();
                            UserData userData=new UserData();
                            ResultSet resultSet = userDAO.authorizationUserCheck(getDataClass.getUserObj().getLogin(), getDataClass.getUserObj().getPassword());
                            try {
                                while (resultSet.next()) {
                                    userData.setRole(resultSet.getBoolean("Role"));
                                    userData.setStatus(resultSet.getBoolean("Status"));
                                    counter++;
                                }
                            } catch (SQLException e) {
                                System.out.println("Error:" + e);
                            }
                            if (counter != 0) {
                                System.out.println("User authorization");
                                setDataClass.setUserObj(userData);
                                setDataClass.setResult(true);
                                sendData(setDataClass);
                            } else {
                                System.out.println("User not found");
                                setDataClass.setResult(false);
                                sendData(setDataClass);
                            }
                            break;
                        }
                        case "aboutUser":{
                            UserData userData=new UserData();
                            UserDAO userDAO=new UserDAO();
                            ResultSet resultSet=userDAO.registrationUserCheck(getDataClass.getUserObj().getLogin());
                            try {
                                while (resultSet.next()) {
                                    userData.setIndex(resultSet.getInt("PersonID"));
                                    userData.setLogin(resultSet.getString("Login"));
                                    userData.setPassword(resultSet.getString("Password"));
                                    userData.setFirstName(resultSet.getString("FirstName"));
                                    userData.setLastName(resultSet.getString("LastName"));
                                    userData.setCountry(resultSet.getString("Country"));
                                    userData.setAge(resultSet.getInt("Age"));
                                    userData.setRole(resultSet.getBoolean("Role"));
                                    setDataClass.setUserObj(userData);
                                    sendData(setDataClass);
                                }
                            } catch (SQLException e){
                                System.out.println("Error:" +e);
                            }
                            break;
                        }
                        case "addDetail":{
                            DetailDAO detailDAO=new DetailDAO();
                            detailDAO.inputDetailCar(getDataClass.getDetailObj().getIndexDetail(),getDataClass.getDetailObj().getIndexCar(),
                                    getDataClass.getDetailObj().getMark(),getDataClass.getDetailObj().getModel(),getDataClass.getDetailObj().getTypeMotor(),
                                    getDataClass.getDetailObj().getVolumeMotor(),getDataClass.getDetailObj().getName(),getDataClass.getDetailObj().getProducer(),
                                    getDataClass.getDetailObj().getPrice());
                            break;
                        }
                        case "viewDetailData":{
                            ArrayList <DetailData> dataArrayList=new ArrayList<>();
                            DetailDAO detailDAO=new DetailDAO();
                            ResultSet resultSet=detailDAO.outputDetailCar();
                            try{
                                while(resultSet.next()){
                                    DetailData detailData=new DetailData();
                                    detailData.setIndexCar(resultSet.getInt("CarID"));
                                    detailData.setMark(resultSet.getString("MarkName"));
                                    detailData.setModel(resultSet.getString("ModelName"));
                                    detailData.setTypeMotor(resultSet.getString("TypeMotor"));
                                    detailData.setVolumeMotor(resultSet.getInt("VolumeMotor"));
                                    detailData.setIndexDetail(resultSet.getInt("DetailID"));
                                    detailData.setName(resultSet.getString("DetailName"));
                                    detailData.setProducer(resultSet.getString("ProducerName"));
                                    detailData.setPrice(resultSet.getInt("Price"));
                                    dataArrayList.add(detailData);
                                }
                                setDataClass.setDetailData(dataArrayList);
                                sendData(setDataClass);
                            }catch (SQLException e){
                                System.out.println("Error:" +e);
                            }
                            break;
                        }
                        case "delDetail":{
                            DetailDAO detailDAO=new DetailDAO();
                            detailDAO.delDetailCar(getDataClass.getDetailObj().getIndexCar());
                            break;
                        }
                        case "editDetail":{
                            DetailDAO detailDAO=new DetailDAO();
                            detailDAO.editDetailCar(getDataClass.getDetailObj().getIndexDetail(),getDataClass.getDetailObj().getIndexCar(),
                                    getDataClass.getDetailObj().getMark(),getDataClass.getDetailObj().getModel(),getDataClass.getDetailObj().getTypeMotor(),
                                    getDataClass.getDetailObj().getVolumeMotor(),getDataClass.getDetailObj().getName(),getDataClass.getDetailObj().getProducer(),
                                    getDataClass.getDetailObj().getPrice());
                            break;
                        }
                        case "viewUserData":{
                            ArrayList <UserData> dataArrayList=new ArrayList<>();
                            UserDAO dao=new UserDAO();
                            ResultSet resultSet=dao.outputUserData();
                            try{
                                while (resultSet.next()){
                                    UserData userData=new UserData();
                                    userData.setIndex(resultSet.getInt("PersonID"));
                                    userData.setLogin(resultSet.getString("Login"));
                                    userData.setPassword(resultSet.getString("Password"));
                                    userData.setFirstName(resultSet.getString("FirstName"));
                                    userData.setLastName(resultSet.getString("LastName"));
                                    userData.setCountry(resultSet.getString("Country"));
                                    userData.setAge(resultSet.getInt("Age"));
                                    userData.setStatus(resultSet.getBoolean("Status"));
                                    dataArrayList.add(userData);
                                }
                                setDataClass.setUserData(dataArrayList);
                                sendData(setDataClass);
                            }catch (SQLException e){
                                System.out.println("Error:" +e);
                            }
                            break;
                        }
                        case "blockingUser":{
                            UserDAO userDAO=new UserDAO();
                            userDAO.blockingUser(getDataClass.getUserObj().getIndex());
                            break;
                        }
                        case "unblockingUser":{
                            UserDAO userDAO=new UserDAO();
                            userDAO.unblockingUser(getDataClass.getUserObj().getIndex());
                            break;
                        }
                        case "delUser":{
                            UserDAO userDAO=new UserDAO();
                            userDAO.delUser(getDataClass.getUserObj().getIndex());
                            break;
                        }
                        case "editUser":{
                            int counter=0;
                            UserDAO userDAO = new UserDAO();
                            ResultSet resultSet = userDAO.editUserCheck(getDataClass.getUserObj().getLogin(),getDataClass.getUserObj().getIndex());
                            try {
                                while (resultSet.next()) {
                                    counter++;
                                }
                            } catch (SQLException e) {
                                System.out.println("Error:" + e);
                            }
                            if (counter != 0) {
                                System.out.println("User exists");
                                setDataClass.setResult(false);
                                sendData(setDataClass);
                            } else {
                                userDAO.editUser(getDataClass.getUserObj().getIndex(),getDataClass.getUserObj().getLogin(),
                                        getDataClass.getUserObj().getPassword(), getDataClass.getUserObj().getFirstName(), getDataClass.getUserObj().getLastName(),
                                        getDataClass.getUserObj().getCountry(), getDataClass.getUserObj().getAge(),getDataClass.getUserObj().isRole(),getDataClass.getUserObj().isStatus());
                                setDataClass.setResult(true);
                                sendData(setDataClass);
                                System.out.println("User edit");
                            }
                            break;
                        }
                        case "analysisData":{
                            AnalysisDAO analysisDao=new AnalysisDAO();
                            analysisDao.inputAnalysis(getDataClass.getAnalysisObj().getAnalysisIndex(),getDataClass.getAnalysisObj().getAlternativeIndex1(),
                                    getDataClass.getAnalysisObj().getAlternativeIndex2(),getDataClass.getAnalysisObj().getAlternativeIndex3(),
                                    getDataClass.getAnalysisObj().getAlternativeIndex4(),getDataClass.getAnalysisObj().getW1(),getDataClass.getAnalysisObj().getW2(),
                                    getDataClass.getAnalysisObj().getW3(),getDataClass.getAnalysisObj().getW4(),getDataClass.getAnalysisObj().getScaleSize(),
                                    getDataClass.getAnalysisObj().getN());
                            break;
                        }
                        case "expertData1":{
                            ExpertDAO expertDAO=new ExpertDAO();
                            expertDAO.insertExpertRating(getDataClass.getExpertObj().getExpertId(),getDataClass.getExpertObj().getAnalysisId(),
                                    getDataClass.getExpertObj().getZ(),getDataClass.getExpertObj().getF1(),getDataClass.getExpertObj().getF2(),
                                    getDataClass.getExpertObj().getF3(),getDataClass.getExpertObj().getF4());
                            break;
                        }
                        case "expertData2":{
                            ExpertDAO expertDAO=new ExpertDAO();
                            expertDAO.insertExpertRating(getDataClass.getExpertObj().getExpertId(),getDataClass.getExpertObj().getAnalysisId(),
                                    getDataClass.getExpertObj().getZ(),getDataClass.getExpertObj().getF1(),getDataClass.getExpertObj().getF2(),
                                    getDataClass.getExpertObj().getF3(),getDataClass.getExpertObj().getF4());
                            break;
                        }
                        case "analysisResult":{
                            AnalysisDAO analysisDAO=new AnalysisDAO();
                            ResultSet resultSet=analysisDAO.outputAnalysis();
                            ArrayList<AnalysisData> analysisDataArrayList=new ArrayList<>();
                            ArrayList<ExpertData> expertDataArrayList=new ArrayList<>();
                            try{
                                while (resultSet.next()){
                                    int z[][]=new int[5][5];
                                    ExpertData expertData=new ExpertData();
                                    AnalysisData analysisData=new AnalysisData();
                                    analysisData.setAnalysisIndex(resultSet.getInt("AnalysisId"));
                                    analysisData.setAlternativeIndex1(resultSet.getInt("DetailId1"));
                                    analysisData.setAlternativeIndex2(resultSet.getInt("DetailId2"));
                                    analysisData.setAlternativeIndex3(resultSet.getInt("DetailId3"));
                                    analysisData.setAlternativeIndex4(resultSet.getInt("DetailId4"));
                                    analysisData.setW1(resultSet.getDouble("Mark1"));
                                    analysisData.setW2(resultSet.getDouble("Mark2"));
                                    analysisData.setW3(resultSet.getDouble("Mark3"));
                                    analysisData.setW4(resultSet.getDouble("Mark4"));
                                    analysisData.setScaleSize(resultSet.getInt("ScaleSize"));
                                    analysisData.setN(resultSet.getInt("N"));
                                    expertData.setExpertId(resultSet.getInt("ExpertId"));
                                    expertData.setAnalysisId(resultSet.getInt("AnalysisId"));
                                    expertData.setF1(resultSet.getInt("F1"));
                                    expertData.setF2(resultSet.getInt("F2"));
                                    expertData.setF3(resultSet.getInt("F3"));
                                    expertData.setF4(resultSet.getInt("F4"));
                                    z[1][2]=resultSet.getInt("Mark12");
                                    z[1][3]=resultSet.getInt("Mark13");
                                    z[1][4]=resultSet.getInt("Mark14");
                                    z[2][1]=resultSet.getInt("Mark21");
                                    z[2][3]=resultSet.getInt("Mark23");
                                    z[2][4]=resultSet.getInt("Mark24");
                                    z[3][1]=resultSet.getInt("Mark31");
                                    z[3][2]=resultSet.getInt("Mark32");
                                    z[3][4]=resultSet.getInt("Mark34");
                                    z[4][1]=resultSet.getInt("Mark41");
                                    z[4][2]=resultSet.getInt("Mark42");
                                    z[4][3]=resultSet.getInt("Mark43");
                                    expertData.setZ(z);
                                    analysisData.setAlternativeName1(resultSet.getString(31));
                                    analysisData.setAlternativeName2(resultSet.getString(36));
                                    analysisData.setAlternativeName3(resultSet.getString(41));
                                    analysisData.setAlternativeName4(resultSet.getString(46));
                                    analysisDataArrayList.add(analysisData);
                                    expertDataArrayList.add(expertData);
                                }
                                setDataClass.setAnalysisData(analysisDataArrayList);
                                setDataClass.setExpertData(expertDataArrayList);
                                sendData(setDataClass);
                            }catch (SQLException e){
                                System.out.println("Error:" +e);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (IOException|ClassNotFoundException  e){
            System.out.println("Error:" +e);
        }
    }


    public DataClass getData() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
        String inputJson=(String) inputStream.readObject();
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(inputJson,DataClass.class);
    }


    public void sendData(DataClass data) throws IOException {
        ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
        ObjectMapper mapper=new ObjectMapper();
        String outputJson=mapper.writeValueAsString(data);
        outputStream.writeObject(outputJson);
    }

}
