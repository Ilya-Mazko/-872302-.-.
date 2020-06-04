package DAO;

import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExpertDAO {
    ConnectionPool pool = ConnectionPool.getInstance();

    public void insertExpertRating(int expertId,int analysisId,int z[][],int f1,int f2,int f3,int f4){
        try {
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("INSERT INTO expertrating VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
            statement.setInt(1,expertId);
            statement.setInt(2,analysisId);
            statement.setInt(3,z[1][2]);
            statement.setInt(4,z[1][3]);
            statement.setInt(5,z[1][4]);
            statement.setInt(6,z[2][1]);
            statement.setInt(7,z[2][3]);
            statement.setInt(8,z[2][4]);
            statement.setInt(9,z[3][1]);
            statement.setInt(10,z[3][2]);
            statement.setInt(11,z[3][4]);
            statement.setInt(12,z[4][1]);
            statement.setInt(13,z[4][2]);
            statement.setInt(14,z[4][3]);
            statement.setInt(15,f1);
            statement.setInt(16,f2);
            statement.setInt(17,f3);
            statement.setInt(18,f4);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error: " +e);
        }

    }

}
