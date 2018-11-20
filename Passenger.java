import java.lang.*;
import java.util.Scanner;
import java.sql.*;



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
        Integer pid;
        Integer numPass;
        String model_year;
        String model;
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
                if (input > 8 || input < 1)
                input = 0;
                else  numPass = input;
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
                if(readModelYear.isEmpty()||readModelYear.equals(" ")){
                    model_year = "";
                    input = 1;
                }
                model_year = readModelYear;
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
                model = readModel;
            } catch(Exception e){
                System.out.println(input_err);
                input = 0;
            }
        }

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
            String sql = "SELECT * FROM Trip WHERE pid=? and date(start)=? and date(end)=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pid);
            pstmt.setString(2, start_date);
            pstmt.setString(3, end_date);
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            Integer getColumnCount = rsmd.getColumnCount();

            //print header
            System.out.println("Trip ID, Driver Name, ");
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
