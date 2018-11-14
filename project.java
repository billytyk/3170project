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
      System.out.println("Welcome! Who are you?");
      System.out.println("1. An administrator");
      System.out.println("2. A passenger");
      System.out.println("3. A driver");
      System.out.println("4. None of the above");
      System.out.println("Please enter [1-4].");
      Administrator.list();

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
    scanner.close();
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

        list();
    }
    public static void Delete_Table() {

        list();
    }
    public static void Load_data() {

        list();
    }
    public static void Check_data() {

        list();
    }
    public static void Go_back() {

        list();
    }
}
class passenger{
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
        case "1": passenger.Request_ride();
                  break;
        case "2": passenger.Check_records();
                  break;
        case "3": passenger.Rate_trip();
                  break;
        case "4": passenger.Go_back();
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
class driver{
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
        case "1": driver.Take_request();
                  break;
        case "2": driver.Finish_trip();
                  break;
        case "3": driver.Check_rating();
                  break;
        case "4": driver.Go_back();
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
