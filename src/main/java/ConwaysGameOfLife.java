import java.util.Random;

public class ConwaysGameOfLife {
    private Grid world;
    private InputParser parser;

    public ConwaysGameOfLife() {
        parser = new InputParser();
    }

    void playGame(){
        createWorld();
        simulateWorld();
    }

    private void createWorld(){
        GridCoordinates gridCoord;
        InputParser parser = new InputParser();

        int rowSize = parser.getGridDimension("row");
        int colSize = parser.getGridDimension("column");

        world = new Grid(rowSize, colSize);
        printWorld();

        if (parser.getUserBooleanDecision("Would you like to randomise the board?")){
            randomiseWorld();
            printWorld();
        }

        while(parser.getUserBooleanDecision("Do you want change a cell to alive?")){
            gridCoord = parser.getGridCoordinates(rowSize, colSize);
            world.setCellState(gridCoord, CellState.ALIVE);
            printWorld();
        }
    }

    private void simulateWorld(){
        while(parser.getUserBooleanDecision("Continue simulation?")){
            world.updateGrid();
            printWorld();
        }
    }

    private void randomiseWorld(){
        int row;
        int col;
        Random randomNumberGenerator = new Random();

        int numberOfCellsToRandom = randomNumberGenerator.nextInt(world.getRowCount() * world.getColumnCount());
        System.out.println(numberOfCellsToRandom);

        for(int cell = 0; cell <= numberOfCellsToRandom; cell++){
            row = randomNumberGenerator.nextInt(world.getRowCount());
            col = randomNumberGenerator.nextInt(world.getColumnCount());
            world.setCellState(row, col, CellState.ALIVE);
        }

    }

    private void printWorld(){
        System.out.println("This is the current state of your world:");
        System.out.println(world.toString());
    }
}
