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
        Integer get_identity = Riding_System.Welcome_list();

        //Administrator.list();
    }
}

class Riding_System {
    public static Integer Welcome_list(){
        System.out.println("Welcome! Who are you?");
        System.out.println("1. An administrator");
        System.out.println("2. A passenger");
        System.out.println("3. A driver");
        System.out.println("4. None of the above");
        System.out.println("Please enter [1-4].");
        Scanner scanner = new Scanner(System.in);
        Integer input = scanner.nextInt();
        System.out.format("Your input is %d %n", input);
        return input;
    }
}
class Administrator{
    public static void list(){
    System.out.println("Administrator, what would you like to do?");
    System.out.println("1. Create Table");
    System.out.println("2. Delete Table");
    System.out.println("3. Load data");
    System.out.println("4. Check data");
    System.out.println("5. Go back");
    System.out.println("Please enter [1-5].");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    //scanner.close();
      switch (input){
        case "1":   Administrator.Create_Table();
                    break;
        case "2":   Administrator.Delete_Table();
                    break;
        case "3":   Administrator.Load_data();
                    break;
        case "4":   Administrator.Check_data();
                    break;
        case "5":   Administrator.Go_back();
                    break;
      }
    }
    public static void Create_Table() {

        Administrator.list();
    }
    public static void Delete_Table() {

        Administrator.list();
    }
    public static void Load_data() {

        Administrator.list();
    }
    public static void Check_data() {

        Administrator.list();
    }
    public static void Go_back() {

        Administrator.list();
    }
}
class Passenger{
    public static void list(){
    System.out.println("Passenger, what would you like to do?");
    System.out.println("1. Request a ride");
    System.out.println("2. Check trip records");
    System.out.println("3. Rate a trip");
    System.out.println("4. Go back");
    System.out.println("Please enter [1-4].");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();
      switch (input){
        case "1": Passenger.Request_ride();
                  break;
        case "2": Passenger.Check_records();
                  break;
        case "3": Passenger.Rate_trip();
                  break;
        case "4": Passenger.Go_back();
                  break;
      }
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
    System.out.println("Driver, what would you like to do?");
    System.out.println("1. Take a request");
    System.out.println("2. Finish a trip");
    System.out.println("3. Check driver rating");
    System.out.println("4. Go back");
    System.out.println("Please enter [1-4].");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();
      switch (input){
        case "1": Driver.Take_request();
                  break;
        case "2": Driver.Finish_trip();
                  break;
        case "3": Driver.Check_rating();
                  break;
        case "4": Driver.Go_back();
                  break;
      }
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