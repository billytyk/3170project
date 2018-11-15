import java.lang.*;
import java.util.Scanner;

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
        //System.out.println("Please enter [1-4].");

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

        list();
    }
    public static void Check_records() {

        list();
    }
    public static void Rate_trip() {

        list();
    }
    public static void Go_back() {

        list();
    }
}