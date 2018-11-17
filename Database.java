import java.lang.*;
import java.util.Scanner;
import java.sql.*;

public class Database{

    private  String db_url;
    private  String db_user;
    private  String db_pw;

    public Database(){

        this.db_url = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db12";
        this.db_user = "Group12";
        this.db_pw = "apple";

    }

    public Connection getConnection(){

        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db_url, db_user, db_pw);
        }
        catch(ClassNotFoundException e){
            System.out.println("[ERROR]: java MYSQL DB Driver fot found !!");
        } 
        catch(SQLException e){
            System.out.println(e);
        }

        return connection;
    }

    public void CloseConnection(Connection con){
        try{
            con.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }


}