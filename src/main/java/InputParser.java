import java.util.Scanner;

public class InputParser {

    private Scanner scanner = new Scanner(System.in);

    int getGridDimension(String targetDimension) {
        int dimension = 0;
        boolean validDimension = false;

        System.out.print("Enter " + targetDimension + " size: ");

        while(!validDimension){
            try{
                String input = scanner.nextLine();
                dimension = Integer.valueOf(input);
                validDimension = true;

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter salary again: ");
            }
        }

        return dimension;
    }
}
