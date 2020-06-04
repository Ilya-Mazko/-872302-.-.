package DAO;

import connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisDAO {
    ConnectionPool pool = ConnectionPool.getInstance();

    public void inputAnalysis(int analysisId,int index1,int index2,int index3,int index4,double mark1,double mark2,double mark3,double mark4,int scaleSize,int N){
        try {
            Connection connection = pool.get();
            PreparedStatement statement=connection.prepareStatement("INSERT INTO analysis VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1,analysisId);
            statement.setInt(2,index1);
            statement.setInt(3,index2);
            statement.setInt(4,index3);
            statement.setInt(5,index4);
            statement.setDouble(6,mark1);
            statement.setDouble(7,mark2);
            statement.setDouble(8,mark3);
            statement.setDouble(9,mark4);
            statement.setInt(10,scaleSize);
            statement.setInt(11,N);
            statement.executeUpdate();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error" +e );
        }
    }

    public ResultSet outputAnalysis(){
        ResultSet resultSet=null;
        try{
            Connection connection=pool.get();
            PreparedStatement statement=connection.prepareStatement("SELECT* FROM Analysis AS a JOIN ExpertRating AS e ON a.AnalysisId = E.AnalysisId JOIN Detail AS d1  ON  a.DetailId1=d1.DetailID JOIN Detail AS d2  ON  a.DetailId2=d2.DetailID JOIN Detail AS d3  ON  a.DetailId3=d3.DetailID JOIN Detail AS d4  ON  a.DetailId4=d4.DetailID");
            resultSet=statement.executeQuery();
            pool.release(connection);
        }catch (SQLException e){
            System.out.println("Error:" +e);
        }
        return resultSet;
    }
}
