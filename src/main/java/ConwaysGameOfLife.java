import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ConwaysGameOfLife {
    private Grid world;
    private InputParser parser;

    public ConwaysGameOfLife() {
        parser = new InputParser();
    }

    void playGame(){
        boolean worldLoaded = false;
        showOpeningMessage();

        if (parser.getUserBooleanDecision("Would you like to load the state of the world?")){
            worldLoaded = loadWorld();
        }

        if (!worldLoaded)
        {
            System.out.println("World was not loaded, creating");
            createWorld();

        }

        customiseWorld();
        simulateWorld();

        if (parser.getUserBooleanDecision("Would you like to save the state of the world?")){
            saveWorld();
        }
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
        int rowSize = parser.getGridDimension("row");
        int colSize = parser.getGridDimension("column");

        world = new Grid(rowSize, colSize);
        printWorld();

        if (parser.getUserBooleanDecision("Would you like to randomise the board?")){
            randomiseWorld();
            printWorld();
        }

        customiseWorld();
    }

    private void customiseWorld() {
        GridCoordinates gridCoord;
        CellState cellState;

        int rowSize = world.getRowCount();
        int colSize = world.getColumnCount();

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

        for(int cell = 0; cell < numberOfCellsToRandom; cell++){
            row = randomNumberGenerator.nextInt(world.getRowCount());
            col = randomNumberGenerator.nextInt(world.getColumnCount());
            world.setCellState(row, col, CellState.ALIVE);
        }

    }

    boolean saveWorld(){
        boolean saveSuccessful = true;

        File file = new File("./other/save");
        file.getParentFile().mkdirs();

        String worldState = world.toString();
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(world.getRowCount() + "," + world.getColumnCount());
            out.print(worldState);
            System.out.println("Saved!");

        }
        catch (FileNotFoundException e){
            System.out.println("Error, unable to save to file. Save failed!");
            saveSuccessful = false;
        }
        return saveSuccessful;

    }

    private boolean loadWorld(){
        boolean loadSuccessful;
        CellState cellState;
        String[] row;
        int currentRow = 0;

        File file = new File("./other/save");

        try (Scanner sc = new Scanner(file)) {
            String[] rowAndCol = sc.nextLine().split(",");
            int rowCount = Integer.valueOf(rowAndCol[0]);
            int colCount = Integer.valueOf(rowAndCol[1]);

            world = new Grid(rowCount, colCount);

            while (sc.hasNextLine()){
                row = sc.nextLine().split(" ");
                for(int currentCol=0; currentCol < colCount; currentCol++){
                    cellState = row[currentCol].equals("x") ? CellState.ALIVE : CellState.DEAD;
                    world.setCellState(currentRow, currentCol, cellState);
                }
                currentRow++;
            }

            loadSuccessful = true;

        }
        catch (FileNotFoundException e){
            System.out.println("Error, unable to load file. Load failed!");
            loadSuccessful = false;
        }

        return loadSuccessful;
    }

    private void printWorld(){
        System.out.println("This is the current state of your world:");
        System.out.println(world.toString());
    }
}
