import java.util.Random;

public class ConwaysGameOfLife {
    private Grid world;
    private InputParser parser;

    public ConwaysGameOfLife() {
        parser = new InputParser();
    }

    void playGame(){
        showOpeningMessage();
        createWorld();
        simulateWorld();
    }

    private void showOpeningMessage() {
        System.out.println("=====================================");
        System.out.println();
        System.out.println("Let's dive deep into a cellular world");
        System.out.println("               in...                 ");
        System.out.println("       CONWAY'S GAME OF LIFE!        ");
        System.out.println();
        System.out.println("=====================================");
    }

    private void createWorld(){
        CellState cellState;
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

        while(parser.getUserBooleanDecision("Do you want update a cell?")){
            gridCoord = parser.getGridCoordinates(rowSize, colSize);
            cellState = parser.getCellStateChoice();
            world.setCellState(gridCoord, cellState);
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
