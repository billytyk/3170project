import java.lang.*;
import java.util.Scanner;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Driver{
    public static void list(){
        Integer input = 0;
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Driver, what would you like to do?");
        System.out.println("1. Take a request");
        System.out.println("2. Finish a trip");
        System.out.println("3. Check driver rating");
        System.out.println("4. Go back");
        //System.out.println("Please enter [1-4].");

        while (input <= 0){
            System.out.println("Please enter [1-4].");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                
                switch (input){
                    case 1:   Driver.Take_request();;
                                break;
                    case 2:   Driver.Finish_trip();;
                                break;
                    case 3:   Driver.Check_rating();;
                                break;
                    case 4:   project.main(null);
                                break;
                    default:    //System.out.println(input);
                                System.out.println(input_err);
                                //System.exit(1);
                                input = 0;
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
    public static void Take_request() {
        Integer tripid = 0;
        Integer input = 0;
        Integer did = 0;
        Integer rid = 0;
        String start = "0000-00-00 00:00:00";
        String start_date = "0000-00-00";
        String end_date = "0000-00-00";
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);

        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                did = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        
        try{
            String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db12";
            String dbUsername = "Group12";
            String dbPassword = "apple";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            
                String sql = "SELECT Request.id as Request_ID, Passenger.name as Passenger_Name," 
                +"Request.passengers as Passenger\n"
                +"FROM Request, Passenger, Driver, Vehicle\n"
                +"WHERE Driver.id = ? AND Vehicle.id = Driver.vid AND Passenger.id = Request.pid AND Vehicle.seats >= Request.passengers" 
                +" AND (Request.model_year IS NULL OR Vehicle.model_year >= Request.model_year) AND Request.taken IS NULL AND (Vehicle.model IS NULL OR Vehicle.model LIKE CONCAT('%',Request.model,'%')) ;\n";
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, did);
               
                ResultSet resultSet = pstmt.executeQuery();
               // System.out.println(resultSet);
                if(!resultSet.isBeforeFirst())
                System.out.println("No records found.");
                else{
                    StringBuilder record = new StringBuilder();
                    System.out.println("Request ID, Passenger name, Passenger");
                    while(resultSet.next()){
                        Integer i = resultSet.getInt(1);
                        String passname = resultSet.getString(2);
                        Integer ppl = resultSet.getInt(3);

                        record.append(i);
                        record.append(", ");
                        record.append(passname);
                        record.append(", ");
                        record.append(ppl);
                        record.append("\n");
                        
                    }
                    System.out.println(record.toString());
                    input = 0;
             while (input <= 0){
            System.out.println("Please enter the request ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                rid = input;
                System.out.println(rid);
              
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }


        String sql1 = "SELECT Passenger.id, Passenger.name, Request.passengers FROM Request, Passenger" 
        +" WHERE Request.id = ? AND Request.pid = Passenger.id";
            Integer j = 0;
            String k = "";
            Integer l = 0;

        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setInt(1, rid);
               // System.out.println(pstmt1);
                ResultSet resultSet1 = pstmt1.executeQuery();
                while(resultSet1.next()){
                    j = resultSet1.getInt(1);
                    k = resultSet1.getString(2);
                    l = resultSet1.getInt(3);
                
                }
              
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date date = new Date();
                start = df.format(date); 
        String sql2 = "INSERT INTO Trip(did,pid,start) VALUES(?,?,?);";
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
       // System.out.println(pstmt2);
                pstmt2.setInt(1,did);
                pstmt2.setInt(2,j);
                pstmt2.setString(3,start);
                try{
                 pstmt2.executeUpdate();
                 ResultSet GeneratedKeys = pstmt2.getGeneratedKeys();
                 tripid = GeneratedKeys.getInt(1);
                 System.out.println(GeneratedKeys);
                }
                catch(SQLException e){
                System.out.println("[ERROR]: Cannot insert data!!");

                }
                System.out.println("Trip ID, Passenger name, Start");
                StringBuilder record1 = new StringBuilder();
                record1.append(tripid);
                record1.append(", ");
                record1.append(k);
                record1.append(", ");
                record1.append(start);
                System.out.println(record1.toString());
                conn.close();
        }
    }
        catch(ClassNotFoundException e){
            System.out.println("[ERROR]: java MYSQL DB Driver nSot found !!");
        }
        catch(SQLException e){
            System.out.println(e);
        }

        list();
    }

    public static void Finish_trip() {
        Integer input = 0;
        Integer did = 0;
        Integer pid = 0;
        Integer tripid = 0;
        String choose = "";
        String end = "0000-00-00 00-00-00";
        String start_date = "0000-00-00";
        String end_date = "0000-00-00";
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);
        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                did = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
       

        try{
            String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db12";
            String dbUsername = "Group12";
            String dbPassword = "apple";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            String sql = "SELECT Trip.id as Trip_ID, Trip.pid as Passenger_ID,Trip.start as Start\n" 
            +"FROM Trip \n"
            +"WHERE Trip.did = ? AND Trip.end IS NULL\n;" ;
               
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, did);
            
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            Integer getColumnCount = rsmd.getColumnCount();
                if(!resultSet.isBeforeFirst())
                    System.out.println("No record found");
                else{
                //print header
                System.out.println("Trip ID, Passenger ID, Start");
                
                while(resultSet.next()){

                    StringBuilder record = new StringBuilder();

                    for (int i = 1; i <= getColumnCount; i++ ) {
                        tripid = resultSet.getInt(1);
                        Integer passgeid = resultSet.getInt(2);
                        String starttime = resultSet.getString(3);

                            record.append(tripid);
                            record.append(", ");
                            record.append(passgeid);
                            record.append(", ");
                            record.append(starttime);
                        }

                    System.out.println(record.toString());
                }
            }

            input = 0;
        while (input<=0){
            System.out.println("Do you wish to finish the trip.");
            try{
                String line1 = scanner.nextLine();
                ////Scanner validate = new Scanner(line);
                //input = validate.nextInt();
                
                //validate.close();
                if(line1.equals("y")||line1.equals("n")){
                choose = line1;
                input = 1;
                }

            }
           
            catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }

            if (choose.equals("y")) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date date = new Date();
                end = df.format(date); //2016/11/16 12:08:43

                String set_end = "UPDATE Trip SET Trip.end = ? WHERE Trip.id = ?";
                PreparedStatement setting = conn.prepareStatement(set_end);
                setting.setString(1, end);
                setting.setInt(2, tripid);

                String finish = "SELECT Trip.id, Passenger.name, Trip.start, Trip.end FROM Passenger, Trip WHERE Trip.did = ? AND Trip.pid = Passenger.id";
                
                PreparedStatement print = conn.prepareStatement(finish);
                print.setInt(1, did);
            
            ResultSet resultSet1 = print.executeQuery();
            ResultSetMetaData rsmd1 = resultSet1.getMetaData();
            Integer getColumn = rsmd1.getColumnCount();
                if(!resultSet1.isBeforeFirst())
                    System.out.println("No record found");
                else{
                //print header
                System.out.println("Trip ID, Passenger Name, Start, End, Fee");
                
                while(resultSet1.next()){

                    StringBuilder record1 = new StringBuilder();

                    for (int i = 1; i <= getColumn; i++ ) {
                        Integer tripid1 = resultSet1.getInt(1);
                        String passgename = resultSet1.getString(2);
                        String starttime1 = resultSet1.getString(3);
                        String endtime = resultSet1.getString(4);

                            record1.append(tripid1);
                            record1.append(", ");
                            record1.append(passgename);
                            record1.append(", ");
                            record1.append(starttime1);
                            record1.append(", ");
                            record1.append(endtime);
                        }

                    System.out.println(record1.toString());
                }
            }


                
            }
        }

       // SELECT Passenger.name FROM Passenger, Trip WHERE Trip.did = 1 AND Trip.pid = Passenger.id;

                conn.close();
        }
        catch(ClassNotFoundException e){
            System.out.println("[ERROR]: java MYSQL DB Driver not found !!");
        }
        catch(SQLException e){
            System.out.println(e);
        }

        list();
    }


    public static void Check_rating() {
        
        Integer input = 0;
        Integer pid = 0;
        Integer did = 0;
        Double avg = 0.0;
        Integer rating = 0;
        String start_date = "0000-00-00";
        String end_date = "0000-00-00";
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);
        
        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                did = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }

        try{
            Database db = new Database();
            Connection con = db.getConnection();
            String sql = "SELECT EXISTS(SELECT 1 FROM Driver WHERE Driver.id = ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,did);
            ResultSet result = pstmt.executeQuery();
            result.next();
            Integer isDriverExist = result.getInt(1);
            if(isDriverExist == 0){
                System.out.println("No such driver record");
                con.close();
                list();
            }

            String sql2 = "SELECT AVG(selected_trips.rating) as avg_rating FROM (SELECT * FROM Trip as T\n"
            +"WHERE T.did = ? AND 5 <= (SELECT COUNT(*) FROM Trip, Driver WHERE Driver.id = T.did AND Trip.did = Driver.id)\n"
            +"ORDER BY T.id DESC LIMIT 5) as selected_trips;";
            //System.out.println(sql2);
            Connection con2 = db.getConnection();
            PreparedStatement pstmt2 = con2.prepareStatement(sql2);
            pstmt2.setInt(1,did);
            ResultSet resultSet2 = pstmt2.executeQuery();
            //System.out.println(resultSet2.isBeforeFirst());
            if(!resultSet2.isBeforeFirst()){
                System.out.println("Your rating is not yet determined");
            }
            else
            {
                resultSet2.next();
                //avg = resultSet2.getDouble(1);
                String gets = resultSet2.getString(1);
                if(gets ==null)
                    System.out.println("Your rating is not yet determined\n");
                else{
                    avg = Double.parseDouble(gets);
                    System.out.printf("Your driving rating is %.2f\n\n", avg);
                }
                
            }


            con2.close();
            con.close();

        }
        catch(SQLException e){
            System.out.println(e);
        }
        list();
    }
    public static void Go_back() {
        Administrator.list();
    }
}
