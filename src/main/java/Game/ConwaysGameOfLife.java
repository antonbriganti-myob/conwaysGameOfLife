package Game;

import IO.UserInputOutput;

import java.util.Random;

public class ConwaysGameOfLife {
    private Grid world;
    private UserInputOutput io;

    public ConwaysGameOfLife(UserInputOutput inputOutputImpl) {
        io = inputOutputImpl;
    }

    public void playGame(){
        BoardPersistance boardPersistance = new BoardPersistance();
        showOpeningMessage();

        if (io.getUserBooleanDecision("Would you like to load the state of the world?")){
            world = boardPersistance.loadWorld();
        }

        if (!boardPersistance.isLoadSuccessful())
        {
            io.sendOutput("World was not loaded, creating");
            createWorld();
        }

        printWorld();

        customiseWorld();
        simulateWorld();

        if (io.getUserBooleanDecision("Would you like to save the state of the world?")){
            boardPersistance.saveWorld(world);
        }
    }

    private void showOpeningMessage() {
        io.sendOutput("=====================================");
        io.sendOutput("");
        io.sendOutput("Let's dive deep into a cellular world");
        io.sendOutput("               in...                 ");
        io.sendOutput("       CONWAY'S GAME OF LIFE!        ");
        io.sendOutput("");
        io.sendOutput("=====================================");
    }

    private void createWorld(){
        int rowSize = io.getGridDimension("row");
        int colSize = io.getGridDimension("column");

        world = new Grid(rowSize, colSize);
        printWorld();

        if (io.getUserBooleanDecision("Would you like to randomise the board?")){
            randomiseWorld();
            printWorld();
        }

//        customiseWorld();
    }

    private void customiseWorld() {
        GridCoordinates gridCoord;
        CellState cellState;

        int rowSize = world.getRowCount();
        int colSize = world.getColumnCount();

        while(io.getUserBooleanDecision("Do you want update a cell?")){
            gridCoord = io.getGridCoordinates(rowSize, colSize);
            cellState = io.getCellStateChoice();
            world.setCellState(gridCoord, cellState);
            printWorld();
        }
    }

    private void simulateWorld(){
        while(io.getUserBooleanDecision("Continue simulation?")){
            world.updateGrid();
            printWorld();
        }
    }

    private void randomiseWorld(){
        int row;
        int col;
        Random randomNumberGenerator = new Random();

        int numberOfCellsToRandom = randomNumberGenerator.nextInt(world.getRowCount() * world.getColumnCount());

        for(int cell = 0; cell < numberOfCellsToRandom; cell++){
            row = randomNumberGenerator.nextInt(world.getRowCount());
            col = randomNumberGenerator.nextInt(world.getColumnCount());
            world.setCellState(row, col, CellState.ALIVE);
        }

    }

    private void printWorld(){
        io.sendOutput("This is the current state of your world:");
        io.sendOutput(world.toString());
    }
}
