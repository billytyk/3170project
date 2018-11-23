//import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

import javafx.scene.shape.Path;

import java.sql.*;


public class Administrator{
    
    //method
    public static void list(){

        Integer input = 0;
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Administrator, what would you like to do?");
        System.out.println("1. Create Table");
        System.out.println("2. Delete Table");
        System.out.println("3. Load data");
        System.out.println("4. Check data");
        System.out.println("5. Go back");

        while (input <= 0){
            System.out.println("Please enter [1-5].");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                
                switch (input){
                    case 1:   Administrator.Create_Table();
                                break;
                    case 2:   Administrator.Delete_Table();
                                break;
                    case 3:   Administrator.Load_data();
                                break;
                    case 4:   Administrator.Check_data();
                                break;
                    case 5:   project.main(null);
                                break;
                    default:  System.out.println(input_err);
                                input = 0;
                                //System.exit(1);
                                break; 
                }

            } catch(Exception e){
                //System.out.println(e);
                System.out.println(input_err);
                //System.exit(1);
                input = 0;
            }

        }

        scanner.close();
    }
    public static void Create_Table() {

        System.out.println("Create_Table");
        Database db = new Database();
        String sql = "";
        Connection con = db.getConnection();

        if(con != null){

            System.out.println("Database connected");
            String fail = "Delete table fail";
            

            try{
                //con.setAutoCommit(false);
                Statement stmt = con.createStatement();
                //sql = new Scanner(new File("CREATE_TABLE.sql")).useDelimiter("\\Z").next();
                //System.out.println(sql);
                File f = new File("CREATE_TABLE.sql");

                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = null , old = "";
                line = bf.readLine();
                while(line != null){
                    if(line.endsWith(";")){
                        sql = "";
                        sql = old + line;
                        System.out.println(sql);
                        stmt.addBatch(sql);
                        //stmt.executeUpdate(sql);
                        old = "";
                    }
                    else{
                        old = old + "\n" + line;
                    }
                    line = bf.readLine();
                }
                stmt.executeBatch();
                //con.commit();
                System.out.println("Create table success");
            }
            catch(FileNotFoundException e){
                System.out.println(e);
                System.out.println(fail);

            }
            catch(SQLException e){

                System.out.println(e);
                System.out.println(fail);

            }
            catch(IOException e){
                System.out.println(e);
                System.out.println(fail);
            }


        }
        else{

            System.out.println("Cannot connect to db");
        }
        db.CloseConnection(con);
        System.exit(0);
    }

    public static void Delete_Table() {
        System.out.println("Delete_Table");
        Database db = new Database();
        //String sql = "";
        Connection con = db.getConnection();

        if(con != null){

            System.out.println("Database connected");
            String fail = "Delete table fail";

            try{

                Statement stmt = con.createStatement();
                //sql = new Scanner(new File("CREATE_TABLE.sql")).useDelimiter("\\Z").next();
                //System.out.println(sql);
                File f = new File("DROP_TABLE.sql");

                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = null , old = "";
                line = bf.readLine();
                while(line != null){
                    if(line.endsWith(";")){
                        System.out.println(old+line);
                        stmt.executeUpdate(old + line);
                        old = "";
                    }
                    else{
                        old = old + "\n" + line;
                    }
                    line = bf.readLine();
                }
                System.out.println("Delete table success");

            }
            catch(FileNotFoundException e){

                System.out.println(e);
                System.out.println(fail);

            }
            catch(SQLException e){

                System.out.println(e);
                System.out.println(fail);

            }
            catch(IOException e){
                System.out.println(e);
                System.out.println(fail);
            }

        }
        else{

            System.out.println("Cannot connect to db");
        }
        db.CloseConnection(con);
        System.exit(0);
    }
    public static void Load_data() {

        System.out.println("Load_data");
        Database db = new Database();
        //String sql = "";
        
        try{
            Connection con = db.getConnection();
            CSVLoader loader = new CSVLoader(con);
            //String[] attributes = new String[] {"id", "name", "vid"};
            Scanner scanner = new Scanner(System.in);
            String dir = "";

            while(true){
                System.out.println("Please enter the folder path");
                try{
                     dir = scanner.nextLine();
                     break;
                }
                catch(Exception e){
                    System.out.println(e);
                }

            }
            System.out.println(System.getProperty("user.dir"));
            File f = new File(System.getProperty("user.dir"), dir);

            if(f.exists() && f.isDirectory()){
            //System.out.println(f.getPath()+"/vehicles.csv");
            System.out.println("Processing...");
            loader.loadCSV(f.getPath()+"/vehicles.csv", "Vehicle", 
                new String[] {"id", "model", "model_year", "seats"}, true);
            loader.loadCSV(f.getPath()+"/drivers.csv", "Driver", new String[] {"id", "name", "vid"}, true);
            loader.loadCSV(f.getPath()+"/passengers.csv", "Passenger", new String[] {"id", "name"}, true);
            loader.loadCSV(f.getPath()+"/trips.csv", "Trip", 
                new String[] {"id", "did", "pid", "start", "end", "fee", "rating"}, true);
            System.out.println("Load Data Complete");
            }
            else{
                System.out.println("No such folder");
            }
            
            db.CloseConnection(con);
            scanner.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
    public static void Check_data() {

        System.out.println("Check_data");
        Database db = new Database();
        String sql = "SHOW TABLES";
        Connection con = db.getConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            //System.out.println(resultSet.toString());
            
            if(!resultSet.isBeforeFirst())
	            System.out.println("No records found.");
            else{
                System.out.println("Number of records in each table:");
	            while(resultSet.next()){
                    String tableName = resultSet.getString(1);
                    //System.out.println(tableName);
                    String query = String.format("SELECT COUNT(*) FROM %s;",tableName);
                    Statement stmt1 = con.createStatement();
                    ResultSet getNumRes = stmt1.executeQuery(query);
                    if(getNumRes.next()){
                        Integer getNumber = getNumRes.getInt(1);
                        System.out.printf("Table %s: %d\n", tableName ,getNumber);
                    }
	            }
            }

            con.close();

        }
        catch(SQLException e){
            System.out.println(e);
            //con.close();
        }

        System.exit(0);
    }

}