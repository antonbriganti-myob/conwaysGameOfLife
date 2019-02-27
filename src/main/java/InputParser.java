import java.util.Scanner;

public class InputParser {

    private Scanner scanner = new Scanner(System.in);

    boolean getUserBooleanDecision(String message){
        boolean validInput = false;
        String input = "";

        System.out.print(message +  " ('yes' or 'no'): ");

        while(!validInput){
            try{
                input = scanner.nextLine();
                validInput = (input.equals("yes")||input.equals("no"));
                if (!validInput){
                    System.out.println("That wasn't 'yes' or 'no'.");
                }

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter dimension again: ");
            }
        }

        return input.equals("yes");
    }

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
                System.out.println("Invalid input, please enter dimension again: ");
            }
        }

        return dimension;
    }

    GridCoordinates getGridCoordinateToUpdate(int rowSize, int colSize){
        System.out.println("\nChoose what Cell you want to change to be alive");
        int row = getSingleCoordinate(rowSize, "row");
        int col = getSingleCoordinate(colSize, "column");

        return new GridCoordinates(row, col);

    }

    GridCoordinates getGridCoordinates(int rowSize, int colSize){
        boolean validCoordinate = false;
        boolean validRow;
        boolean validCol;
        int row = 0;
        int col = 0;

        while(!validCoordinate){
            System.out.println("\nEnter coordinate as per the following: row,col (e.g. 6,7)");
            System.out.println("x's valid values are between 0 and " + (rowSize-1));
            System.out.println("y's valid values are between 0 and " + (colSize-1));

            try{
                String[] input = scanner.nextLine().split(",");
                row = Integer.valueOf(input[0]);
                col = Integer.valueOf(input[1]);

                validRow = ((row>=0)&(row < rowSize));
                validCol = ((col>=0)&(col < colSize));
                validCoordinate = validCol&&validRow;

                if (!validCoordinate){
                    System.out.println("Out of range, please enter coordinate again");
                }

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter coordinate again.");
            }
        }

        return new GridCoordinates(row, col);

    }


    int getSingleCoordinate(int limit, String type){
        int coordinate = 0;
        boolean validCoordinate = false;


        while(!validCoordinate){
            System.out.println("\nEnter " + type + " coordinate");
            System.out.print("Valid values are 0 to " + (limit-1) + " (inclusive): ");

            try{
                String input = scanner.nextLine();
                coordinate = Integer.valueOf(input);
                validCoordinate = ((coordinate>=0)&(coordinate < limit));
                if (!validCoordinate){
                    System.out.println("Out of range, please enter coordinate again");
                }

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter coordinate again.");
            }
        }

        return coordinate;
    }
}
