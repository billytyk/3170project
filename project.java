import java.lang.*;
import java.util.Scanner;

// public class MyClass {
//   int x = 5;

//   public static void main(String[] args) {
//     MyClass myObj1 = new MyClass();  // Object 1
//     MyClass myObj2 = new MyClass();  // Object 2
//     System.out.println(myObj1.x);
//     System.out.println(myObj2.x);
//   }
// }

// public class project {
//     public static void main() {
//         // Do something here
//         System.out.println(x)
//     }
// }
// pr

public class project {
    public static void main(String[] args) {

        Integer input = 0;
        String input_err = "[ERROR] Invalid input.";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Who are you?");
        System.out.println("1. An administrator");
        System.out.println("2. A passenger");
        System.out.println("3. A driver");
        System.out.println("4. None of the above");

        while (input <= 0){

            System.out.println("Please enter [1-4].");
            try{

                String line = scanner.nextLine();
                Scanner validate = new Scanner(line);
                input = validate.nextInt();
                validate.close();

                switch (input){
                    case 1:   Administrator.list();
                                break;
                    case 2:   Passenger.list();
                                break;
                    case 3:   Driver.list();
                                break;
                    case 4:   input = 0;
                                break;
                    default:    System.out.println(input_err);
                                input = 0;
                                break;
                }
            } catch(Exception e){

                System.out.println(input_err);

            }

        }
        scanner.close();

    }
}

class Administrator{

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
