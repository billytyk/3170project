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
    // Please enter your ID
    Integer input = 0;
    Integer pid;
    Integer numPass;
    String model_year;
    String model;
    String input_err = "[ERROR] Invalid input.";
    Scanner scanner = new Scanner(System.in);

    while (input <= 0){
      System.out.println("Please enter your ID.");
      try{
        String line = scanner.nextLine();
        Scanner validate = new Scanner(line);
        input = validate.nextInt();
        validate.close();
        pid = input;
      } catch(Exception e){
        //System.out.println(e);
        System.out.println(input_err);
        //System.exit(1);
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
        //System.out.println(e);
        System.out.println(input_err);
        //System.exit(1);
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
        //Scanner readModelYear = new Scanner(line);
        //String readModelYear = validate.nextLine();
        if(readModelYear.isEmpty()||readModelYear.equals(" ")){
          model_year = "";
          input = 1;
        }
        //validate.close();
        model_year = readModelYear;
      } catch(Exception e){
        //System.out.println(e);
        System.out.println(input_err);
        //System.exit(1);
        input = 0;
      }
    }
    // Please enter the model. (Please enter to skip)
    input = 0;
    while (input <= 0){
      System.out.println("Please enter the model. (Please enter to skip)");
      try{
        String readModel = scanner.nextLine();
        //Scanner validate = new Scanner(line);
        //String readModel = validate.nextLine();
        if(readModel.isEmpty()||readModel.equals(" ")){
          model="";
          input=1;
        }
        //validate.close();
        model = readModel;
        // System.out.println(model);
      } catch(Exception e){
        //System.out.println(e);
        System.out.println(input_err);
        //System.exit(1);
        input = 0;
      }
    }


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
