//import java.beans.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.Scanner;
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

            try{

                Statement stmt = con.createStatement();
                sql = new Scanner(new File("CREATE_TABLE.sql")).useDelimiter("\\Z").next();
                //System.out.println(sql);
                //ResultSet rs = stmt.executeQuery(sql);

            }
            catch(FileNotFoundException e){

                System.out.println(e);

            }
            catch(SQLException e){

                System.out.println(e);

            }

        }
        else{

            System.out.println("Cannot connect to db");
        }
        
        System.exit(0);
    }
    public static void Delete_Table() {
        System.out.println("Delete_Table");
        System.exit(0);
    }
    public static void Load_data() {

        System.out.println("Load_data");
        System.exit(0);
    }
    public static void Check_data() {

        System.out.println("Check_data");
        System.exit(0);
    }

}