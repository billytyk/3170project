import java.lang.*;
import java.util.Scanner;

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

        list();
    }
    public static void Finish_trip() {

        list();
    }
    public static void Check_rating() {

        list();
    }
    public static void Go_back() {

        list();
    }
}
