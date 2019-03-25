package IO;

import GameOfLife.CellState;
import GameOfLife.GridCoordinates;
import Validation.InputValidator;

import java.util.Scanner;

public class ConsoleInputOutput implements UserInputOutput {
    private Scanner scanner;
    private InputValidator validator;

    public ConsoleInputOutput() {
        scanner = new Scanner(System.in);
        validator = new InputValidator();
    }

    @Override
    public void sendOutput(String output) {
        sendOutput(output);
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
                sendOutput("That wasn't 'yes' or 'no'.");
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
            sendOutput("Enter coordinate as per the following: row,col (e.g. 6,7)");
            sendOutput("x's valid values are between 0 and " + (rowSize-1));
            sendOutput("y's valid values are between 0 and " + (colSize-1));

            try{
                String[] input = scanner.nextLine().split(",");
                row = Integer.valueOf(input[0]);
                col = Integer.valueOf(input[1]);

                validCoordinate = validator.isValidCoordinate(row, col, rowSize, colSize);

                if (!validCoordinate){
                    sendOutput("Out of range, please enter coordinate again");
                }

            }
            catch (NumberFormatException e){
                sendOutput("Invalid input, please enter coordinate again.");
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
                sendOutput("Please choose alive or dead");
            }

        }

        return CellState.valueOf(input.toUpperCase());
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
                    sendOutput("Please enter a number greater than 0");
                }

            }
            catch (NumberFormatException e){
                sendOutput("Invalid input, please enter dimension again: ");
            }
        }

        return dimension;
    }
}
