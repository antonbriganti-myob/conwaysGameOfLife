package Game;

import Cell.CellState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BoardPersistance {

    public static boolean loadSuccessful;

    public static void saveWorld(Grid world){

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
        }
    }

    public static Grid loadWorld(){
        Grid world = null;
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

        return world;
    }


}
