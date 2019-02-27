public class ConwaysGameOfLife {
    private Grid world;
    private InputParser parser;

    public ConwaysGameOfLife() {
        InputParser parser = new InputParser();
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

    private void printWorld(){
        System.out.println("This is the current state of your world:");
        System.out.println(world.toString());
    }
}
