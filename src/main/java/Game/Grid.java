package Game;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private int rowCount;
    private int columnCount;
    private List<List<Cell>> grid;

    public Grid(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.grid = createGrid();
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    private ArrayList<List<Cell>> createGrid(){
        ArrayList<List<Cell>> grid = new ArrayList<List<Cell>>();

        for(int row = 0; row < rowCount; row++)  {
            grid.add(new ArrayList<>());

            for(int col = 0; col < columnCount; col++)  {
                grid.get(row).add(new Cell(CellState.DEAD));
            }

        }
        return grid;
    }

    public void updateGrid(){
        int neighbours;
        CellState nextState;
        ArrayList<List<Cell>> nextGrid = createGrid();

        for(int row = 0; row < rowCount; row++)  {
            for(int col = 0; col < columnCount; col++)  {
                neighbours = findAliveNeighbours(row, col);
                nextState = grid.get(row).get(col).determineNextState(neighbours);
                nextGrid.get(row).get(col).updateCurrentState(nextState);
            }
        }

        grid = nextGrid;
    }


    public int findAliveNeighbours(int cellRow, int cellColumn){
        int count = 0;

        for(int row = cellRow-1; row<=cellRow+1; row++){
            for(int col = cellColumn-1; col<=cellColumn+1; col++){
                if (!isSameCell(cellRow, cellColumn, row, col)){
                    if(isCurrentCellAlive(row, col)){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    boolean isSameCell(int cellRow, int cellColumn, int row, int col) {
        return row == cellRow && col==cellColumn;
    }

    public boolean isCurrentCellAlive(int row, int col) {
        boolean cellIsAlive;

        if(inRange(row, col)){
            cellIsAlive = grid.get(row).get(col).isAlive();

        }
        else{
            GridCoordinates wrapAroundCoord = determineWrapAroundCoordinate(row, col);
            cellIsAlive = grid.get(wrapAroundCoord.row).get(wrapAroundCoord.col).isAlive();
        }

        return cellIsAlive;
    }


    public void setCellState(int row, int column, CellState nextState){
        if (inRange(row, column)){
            grid.get(row).get(column).updateCurrentState(nextState);
        }
    }

    public void setCellState(GridCoordinates gridCoordinate, CellState nextState){
        setCellState(gridCoordinate.getRow(), gridCoordinate.getCol(), nextState);
    }

    private boolean inRange(int row, int column){
        return (0 <= row && row < rowCount) && (0 <= column && column < columnCount);
    }

    private GridCoordinates determineWrapAroundCoordinate(int row, int column){
        int newRow = row;
        int newCol = column;

        if(row >= rowCount){
            newRow = 0;
        }
        else if(row < 0){
            newRow = rowCount-1;
        }

        if(column >= columnCount){
            newCol = 0;
        }
        else if(column < 0){
            newCol = columnCount-1;
        }

        return new GridCoordinates(newRow, newCol);
    }


    @Override
    public String toString() {
        int i;
        StringBuilder result = new StringBuilder();
        for(i = 0; i < rowCount-1; i++)  {
            result.append((grid.get(i))).append("\n");
        }
        result.append((grid.get(i)));

        return result.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }
}
