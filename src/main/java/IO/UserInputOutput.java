package IO;

import Cell.CellState;
import Game.GridCoordinates;

public interface UserInputOutput {

    public void sendOutput(String output);
    public boolean getUserBooleanDecision(String message);
    public GridCoordinates getGridCoordinates(int rowSize, int colSize);
    public CellState getCellStateChoice();
    public int getGridDimension(String targetDimension);

}
