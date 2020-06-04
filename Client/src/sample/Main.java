package sample;

import connector.DataClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application implements Runnable {

    private static Socket socket;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/Authorization.fxml"));
        primaryStage.setTitle("My Course Project");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        new Thread(new Main()).start();
        launch(args);
    }

    @Override
    public void run() {
        try{
            socket = new Socket("127.0.0.1",5710);
        } catch (IOException e){
            System.out.println("Error:" + e);
        }
    }

    public static DataClass getData() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String inputJson = (String) inputStream.readObject();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputJson, DataClass.class);
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error:" +e);
        }
        throw  new RuntimeException();
    }

    public static void sendData(DataClass data){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            String outputJson = mapper.writeValueAsString(data);
            outputStream.writeObject(outputJson);
        }catch (IOException e){
            System.out.println("Error:" +e);
        }
    }
}
