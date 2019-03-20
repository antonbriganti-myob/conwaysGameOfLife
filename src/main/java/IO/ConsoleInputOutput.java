package IO;

import java.util.Scanner;

import Game.CellState;
import Game.GridCoordinates;
import Validation.InputValidator;

public class ConsoleInputOutput implements UserInputOutput {
    private Scanner scanner;
    private InputValidator validator;

    public ConsoleInputOutput() {
        scanner = new Scanner(System.in);
        validator = new InputValidator();
    }

    @Override
    public void sendOutput(String output) {
        System.out.println(output);
    }

    @Override
    public boolean getUserBooleanDecision(String message) {
        boolean validInput = false;
        String input = "";

        System.out.print(message +  " ('yes' or 'no'): ");

        while(!validInput){
            input = scanner.nextLine().toLowerCase();
            validInput = validator.isValidBooleanAnswer(input);
            if (!validInput){
                System.out.println("That wasn't 'yes' or 'no'.");
            }
        }

        return (input.substring(0, 1).equals("y"));
    }

    @Override
    public GridCoordinates getGridCoordinates(int rowSize, int colSize) {
        boolean validCoordinate = false;
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

                validCoordinate = validator.isValidCoordinate(row, col, rowSize, colSize);

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

    @Override
    public CellState getCellStateChoice() {
        boolean validInput = false;
        String input = "";

        System.out.print("What state do you want to set the cell to? ('alive' or 'dead'): ");

        while(!validInput){
            input = scanner.nextLine().toLowerCase();
            validInput = validator.isValidCellStateAnswer(input);
            if (!validInput){
                System.out.println("Please choose alive or dead");
            }

        }

        return input.equals("alive") ? CellState.ALIVE : CellState.DEAD;
    }

    @Override
    public int getGridDimension(String targetDimension) {
        int dimension = 0;
        boolean validDimension = false;

        System.out.print("Enter " + targetDimension + " size (greater than 0): ");

        while(!validDimension){
            try{
                String input = scanner.nextLine();
                dimension = Integer.valueOf(input);
                validDimension = validator.isValidDimension(dimension);

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
}
