import java.lang.*;
import java.util.Scanner;

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