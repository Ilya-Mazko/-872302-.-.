package DAO;

import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailDAO {
    ConnectionPool pool = ConnectionPool.getInstance();

    public void inputDetailCar(int indexDetail,int indexCar,String markName,String modelName,String typeMotor,double volumeMotor,
                               String detailName,String producerName,int price){
        try {
            Connection connection = pool.get();
            PreparedStatement statementCar=connection.prepareStatement("INSERT INTO car VALUES(?,?,?,?,?)");
            statementCar.setInt(1,indexCar);
            statementCar.setString(2,markName);
            statementCar.setString(3,modelName);
            statementCar.setString(4,typeMotor);
            statementCar.setDouble(5,volumeMotor);
            statementCar.executeUpdate();

            PreparedStatement statementDetail=connection.prepareStatement("INSERT INTO detail VALUES (?,?,?,?,?)");
            statementDetail.setInt(1,indexDetail);
            statementDetail.setString(2,detailName);
            statementDetail.setString(3,producerName);
            statementDetail.setInt(4,price);
            statementDetail.setInt(5,indexCar);
            statementDetail.executeUpdate();
            pool.release(connection);
        }catch (SQLException|NullPointerException e){
            e.printStackTrace();
        }
    }

    public ResultSet outputDetailCar(){
        ResultSet resultSet=null;
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("SELECT c.CarID,c.MarkName,c.ModelName,c.TypeMotor," +
                    "c.VolumeMotor,d.DetailID,d.DetailName,d.ProducerName,d.Price,d.CarId FROM car AS c JOIN detail AS d ON c.CarID=d.CarId");
            resultSet=statement.executeQuery();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error:" +e);
        }
        return  resultSet;
    }

    public void delDetailCar(int carId){
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("DELETE FROM car WHERE CarID=?");
            statement.setInt(1,carId);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException | NullPointerException  e){
            System.out.println("Error:" +e);
        }
    }

    public void editDetailCar(int indexDetail,int indexCar,String markName,String modelName,String typeMotor,double volumeMotor,
                               String detailName,String producerName,int price){
        try {
            Connection connection = pool.get();
            PreparedStatement statementCar=connection.prepareStatement("UPDATE car SET CarID=?, MarkName=?,ModelName=?,TypeMotor=?,VolumeMotor=? WHERE CarID=?");
            statementCar.setInt(1,indexCar);
            statementCar.setString(2,markName);
            statementCar.setString(3,modelName);
            statementCar.setString(4,typeMotor);
            statementCar.setDouble(5,volumeMotor);
            statementCar.setInt(6,indexCar);
            statementCar.executeUpdate();

            PreparedStatement statementDetail=connection.prepareStatement("UPDATE detail SET DetailID=?, DetailName=?,ProducerName=?,Price=?,CarId=? WHERE DetailID=?");
            statementDetail.setInt(1,indexDetail);
            statementDetail.setString(2,detailName);
            statementDetail.setString(3,producerName);
            statementDetail.setInt(4,price);
            statementDetail.setInt(5,indexCar);
            statementDetail.setInt(6,indexDetail);
            statementDetail.executeUpdate();
            pool.release(connection);
        }catch (SQLException|NullPointerException e){
            e.printStackTrace();
        }
    }
}
