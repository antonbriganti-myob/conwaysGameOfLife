import java.util.Scanner;

public class InputParser {

    private Scanner scanner = new Scanner(System.in);

    boolean getUserBooleanDecision(String message){
        boolean validInput = false;
        String input = "";

        System.out.print(message +  " ('yes' or 'no'): ");

        while(!validInput){
            input = scanner.nextLine().toLowerCase();
            validInput = (input.equals("yes")||input.equals("no")||input.equals("y")||input.equals("n"));
            if (!validInput){
                System.out.println("That wasn't 'yes' or 'no'.");
            }
        }

        return (input.substring(0, 1).equals("y"));
    }

    CellState getCellStateChoice(){
        boolean validInput = false;
        String input = "";

        System.out.print("What state do you want to set the cell to? ('alive' or 'dead'): ");

        while(!validInput){
            input = scanner.nextLine().toLowerCase();
            validInput = (input.equals("alive")||input.equals("dead"));
            if (!validInput){
                System.out.println("Please choose alive or dead");
            }

        }

        return input.equals("alive") ? CellState.ALIVE : CellState.DEAD;
    }

    int getGridDimension(String targetDimension) {
        int dimension = 0;
        boolean validDimension = false;

        System.out.print("Enter " + targetDimension + " size (greater than 0): ");

        while(!validDimension){
            try{
                String input = scanner.nextLine();
                dimension = Integer.valueOf(input);
                validDimension = (dimension > 0);

                if(!validDimension){
                    System.out.println("Please enter a number greater than 0");
                }

            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter dimension again: ");
            }
        }

        return dimension;
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

}
