import java.lang.*;
import java.util.Scanner;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



class Passenger{
    public static void list(){
        Integer input = 0;
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Passenger, what would you like to do?");
        System.out.println("1. Request a ride");
        System.out.println("2. Check trip records");
        System.out.println("3. Rate a trip");
        System.out.println("4. Go back");

        while (input <= 0){
            System.out.println("Please enter [1-4].");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();

                switch (input){
                    case 1:   Passenger.Request_ride();;
                    break;
                    case 2:   Passenger.Check_records();;
                    break;
                    case 3:   Passenger.Rate_trip();;
                    break;
                    case 4:   project.main(null);
                    break;
                    default:    //System.out.println(input);
                    System.out.println(input_err);
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
    public static void Request_ride() {
        Integer input = 0;
        Integer pid = 0;
        Integer numPass = 0;
        String model_year = "";
        String model = "";
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);
        // Please enter your ID
        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                pid = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the number of passengers
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the number of passengers.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                // System.out.println(input);
                if (input > 8 || input < 3){
                    input = 0;
                    System.out.println("Seats number should be between 3 and 8!!!!");
                }
                else {
                    numPass = input;
                }
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the earlist model year. (Please enter to skip)
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the earlist model year. (Please enter to skip)");
            try{
                String readModelYear = scanner.nextLine();
                //System.out.println(readModelYear);
                if(readModelYear.isEmpty()||readModelYear.equals(" ")){
                    model_year = "";
                    input = 1;
                }
                else{
                    Integer valid_input = Integer.parseInt(readModelYear);
                    if(valid_input == Integer.parseInt(readModelYear))
                    {    
                        model_year = readModelYear;
                        input = 1;
                    }
                }
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the model. (Please enter to skip)
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the model. (Please enter to skip)");
            try{
                String readModel = scanner.nextLine();
                if(readModel.isEmpty()||readModel.equals(" ")){
                    model="";
                    input=1;
                }
                else{
                    model = readModel;
                    input = 1;
                }
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // System.out.println(numPass);
        // System.out.println(numPass);
        try{
            Database db = new Database();
            Connection conn = db.getConnection();
            Integer Available_Driver = 0;
            if((!(model_year.isEmpty()||model_year.equals(" ")))&&!(model.isEmpty()||model.equals(" "))){
                System.out.println("Model + Model year");
                String sql = "SELECT COUNT(*)"
                +"FROM (SELECT DISTINCT Vehicle.id as Car_id, Vehicle.model "
                +"FROM Driver, Vehicle "
                +"WHERE Driver.vid=Vehicle.id AND Vehicle.model LIKE ? AND Vehicle.model_year>=? "
                +"And Vehicle.seats >=?) as Available;";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, "%"+model+"%");
                pstmt.setString(2, model_year);
                pstmt.setInt(3, numPass);
                System.out.println(pstmt.toString());
                ResultSet resultSet = pstmt.executeQuery();
                if(!resultSet.isBeforeFirst())
                    System.out.println("No records found.");
                else{
                    // while(resultSet.next()){
                    //     Integer i = resultSet.getInt(1);
                    //     System.out.println(i);
                    // }
                    resultSet.next();
                    Available_Driver = resultSet.getInt(1);
                    System.out.println("Available Driver = "+ String.valueOf(Available_Driver));
                }
                //conn.close();
            }
            else if(!(model.isEmpty()||model.equals(" "))&&(model_year.isEmpty()||model_year.equals(" "))){
                System.out.println("Only model");
                System.out.println("Model + Model year");
                String sql = "SELECT COUNT(*)"
                +"FROM (SELECT DISTINCT Vehicle.id as Car_id, Vehicle.model "
                +"FROM Driver, Vehicle "
                +"WHERE Driver.vid=Vehicle.id AND Vehicle.model LIKE ? "
                +"AND Vehicle.seats >=?) as Available;";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setInt(2, numPass);
                pstmt.setString(1, "%"+model+"%");
                System.out.println(pstmt.toString());

                ResultSet resultSet = pstmt.executeQuery();
                if(!resultSet.isBeforeFirst())
                System.out.println("No records found.");
                else{
                    // while(resultSet.next()){
                    //     Integer i = resultSet.getInt(1);
                    //     System.out.println(i);
                    // }
                    resultSet.next();
                    Available_Driver = resultSet.getInt(1);
                    System.out.println("Available Driver = "+ String.valueOf(Available_Driver));
                }
                //conn.close();
            }
            else if(!(model_year.isEmpty()||model_year.equals(" ")) && (model.isEmpty()||model.equals(" "))){
                System.out.println("Only model_year");
                System.out.println("Model + Model year");
                String sql = "SELECT COUNT(*)"
                +"FROM (SELECT DISTINCT Vehicle.id as Car_id, Vehicle.model "
                +"FROM Driver, Vehicle "
                +"WHERE Driver.vid=Vehicle.id AND Vehicle.model_year>=? "
                +"And Vehicle.seats >=?) as Available;";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, model_year);
                pstmt.setInt(2, numPass);
                System.out.println(pstmt.toString());

                ResultSet resultSet = pstmt.executeQuery();
                if(!resultSet.isBeforeFirst())
                System.out.println("No records found.");
                else{
                    // while(resultSet.next()){
                    //     Integer i = resultSet.getInt(1);
                    //     System.out.println(i);
                    // }
                    resultSet.next();
                    Available_Driver = resultSet.getInt(1);
                    System.out.println("Available Driver = "+ String.valueOf(Available_Driver));
                }
                //conn.close();
            }
            else{
                System.out.println("Both no model year and model");
                String sql = "SELECT COUNT(*)"
                +"FROM (SELECT DISTINCT Vehicle.id as Car_id, Vehicle.model "
                +"FROM  Driver, Vehicle "
                +"WHERE Driver.vid=Vehicle.id AND Vehicle.seats >=?) as Available;";
                PreparedStatement pstmt = conn.prepareStatement(sql);


                pstmt.setInt(1, numPass);
                System.out.println(pstmt.toString());

                ResultSet resultSet = pstmt.executeQuery();
                if(!resultSet.isBeforeFirst())
                System.out.println("No records found.");
                else{
                    // while(resultSet.next()){
                    //     Integer i = resultSet.getInt(1);
                    //     System.out.println(i);
                    // }
                    resultSet.next();
                    Available_Driver = resultSet.getInt(1);
                    System.out.println("Available Driver = "+ String.valueOf(Available_Driver));
                }
                
                //conn.close();
            }
            //ResultSet resultSet = pstmt.executeQuery();
            if(Available_Driver > 0){

                String addRequest = "INSERT INTO Request(model_year,model,passengers) VALUES(?,?,?);";
                PreparedStatement ps = conn.prepareStatement(addRequest);
                if (model_year.equals("") || model_year.equals(" ")){
                    ps.setNull(1,Types.INTEGER);
                }
                else{
                    ps.setString(1, model_year);
                }
                if (model.equals("") || model.equals(" ")){
                    ps.setNull(2,Types.VARCHAR);
                }
                else{
                    ps.setString(2, model);
                }
                ps.setInt(3,numPass);
                ps.executeUpdate();

                System.out.println(
                    String.format("Your request is placed. %d drivers are able to take the request."
                    ,Available_Driver));
                
            }
            else{
                System.out.println("No matched result found.Please adjust your criterion.");
            }

            conn.close();
        }

        catch(SQLException e){
            System.out.println(e);
        }


        //scanner.close();
        list();

    }
    public static void Check_records() {
        Integer input = 0;
        Integer pid = 0;
        String start_date = "0000-00-00";
        String end_date = "0000-00-00";
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);
        // Please enter your ID
        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                pid = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the start date
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the start date.");
            try{
                String read_start_date = scanner.nextLine();
                start_date = read_start_date;
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                df.parse(read_start_date);
                input=1;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the end date
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the end date.");
            try{
                String read_end_date = scanner.nextLine();
                end_date = read_end_date;
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                df.parse(read_end_date);
                input=1;
            }
            catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        try{
            Database db = new Database();
            Connection conn = db.getConnection();
            String sql = 
            "SELECT Trip.id as Trip_ID, Driver.name as Driver_Name," 
            +"Vehicle.id as Vehicle_ID, Vehicle.model as Vehicle_model,"
            +"Trip.Start as Start, Trip.End as End, Trip.fee as Fee, Trip.rating as Rating\n"
            +"FROM Trip, Driver, Vehicle\n"
            +"WHERE  Trip.did = Driver.id AND Trip.pid=? AND Driver.vid=Vehicle.id\n" 
            +"AND DATE(Trip.Start)>=? AND DATE(Trip.End)<=?\n"
            +"ORDER BY Trip.start DESC;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pid);
            pstmt.setString(2, start_date);
            pstmt.setString(3, end_date);
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            Integer getColumnCount = rsmd.getColumnCount();
            if(!resultSet.isBeforeFirst()){
                System.out.println("No record found");
            }
            else{
                //print header
                System.out.println("Trip ID, Driver Name, Vehicle ID, Vehicle Model, Start, End, Fee, Rating");
                //print all record
                while(resultSet.next()){
                    StringBuilder record = new StringBuilder();
                    Boolean first1 = true;
                    for (int i = 1; i <= getColumnCount; i++ ) {
                        String col = resultSet.getString(i);
        
                        if(first1 == true){
                            first1 = false;
                            record.append(col);
                        }
                        else{
                            record.append(", ");
                            record.append(col);
                        }
                    }
                    
                    System.out.println(record.toString());

                }
            }

            conn.close();
        }
        //catch(ClassNotFoundException e){
        //    System.out.println("[ERROR]: java MYSQL DB Driver fot found !!");
        //}
        catch(SQLException e){
            System.out.println(e);
        }
        list();
    }
    public static void Rate_trip() {
        Integer input = 0;
        Integer pid;
        Integer tid;
        Integer rate;
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);
        // Please enter your ID
        while (input <= 0){
            System.out.println("Please enter your ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                pid = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the trip ID
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the trip ID.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                tid = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        // Please enter the rating
        input = 0;
        while (input <= 0){
            System.out.println("Please enter the rating.");
            try{
                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();
                rate = input;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }
        scanner.close();
        list();
    }
    public static void Go_back() {
        Administrator.list();
    }
}
