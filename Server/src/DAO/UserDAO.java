package DAO;


import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    ConnectionPool pool = ConnectionPool.getInstance();

    public void inputUserInDataBase(int index, String login, String password, String firstName, String lastName, String country, int age,boolean role,boolean status) {
        try {
            Connection connection = pool.get();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person VALUES ( ?,?, ?, ?, ?, ?, ?, ?,?)");
            statement.setInt(1, index);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, country);
            statement.setInt(7, age);
            statement.setBoolean(8,status);
            statement.setBoolean(9,role);
            statement.executeUpdate();
            pool.release(connection);
        } catch (SQLException | NullPointerException e) {
            System.out.println("Error:" + e);
        }
    }

    public ResultSet authorizationUserCheck(String login, String password) {
        ResultSet resultSet = null;
        try {
            Connection connection = pool.get();
            PreparedStatement statement = connection.prepareStatement("SELECT* FROM person WHERE Login =? and Password =?");
            statement.setString(1,login);
            statement.setString(2,password);
            resultSet=statement.executeQuery();
            pool.release(connection);
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return resultSet;
    }

    public ResultSet registrationUserCheck(String login) {
        ResultSet resultSet = null;
        try {
            Connection connection = pool.get();
            PreparedStatement statement = connection.prepareStatement("SELECT* FROM person WHERE Login =?");
            statement.setString(1,login);
            resultSet=statement.executeQuery();
            pool.release(connection);
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return resultSet;
    }

    public ResultSet outputUserData(){
        ResultSet resultSet=null;
        try {
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM person WHERE Role=FALSE");
            resultSet=statement.executeQuery();
            pool.release(connection);
        } catch (SQLException e){
            System.out.println("Error:" +e);
        }
        return resultSet;
    }

    public void blockingUser(int index){
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("UPDATE person SET Status=FALSE WHERE PersonID=?");
            statement.setInt(1,index);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error:" +e);
        }
    }

    public void unblockingUser(int index){
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("UPDATE person SET Status=TRUE WHERE PersonID=?");
            statement.setInt(1,index);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error:" +e);
        }
    }

    public void delUser(int index){
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("DELETE FROM person WHERE PersonID=?");
            statement.setInt(1,index);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error:" +e);
        }
    }

    public void editUser(int index, String login, String password, String firstName, String lastName, String country, int age,boolean role,boolean status){
        try {
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("UPDATE person SET PersonID=?,Login=?,Password=?,FirstName=?,LastName=?," +
                    "Country=?,Age=?,Status=?,Role=? WHERE PersonID=?");
            statement.setInt(1, index);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, country);
            statement.setInt(7, age);
            statement.setBoolean(8,status);
            statement.setBoolean(9,role);
            statement.setInt(10, index);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException | NullPointerException e){
            System.out.println("Error:" +e);
        }
    }

    public ResultSet editUserCheck(String login,int index) {
        ResultSet resultSet = null;
        try {
            Connection connection = pool.get();
            PreparedStatement statement = connection.prepareStatement("SELECT* FROM person WHERE Login =? AND PersonID!=?");
            statement.setString(1,login);
            statement.setInt(2,index);
            resultSet=statement.executeQuery();
            pool.release(connection);
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return resultSet;
    }
}
