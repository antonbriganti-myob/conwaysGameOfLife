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
            grid.add(new ArrayList<Cell>());
            for(int col = 0; col < columnCount; col++)  {
                grid.get(row).add(new Cell(CellState.DEAD));
            }
        }
        return grid;
    }

    public void updateGrid(){
        int neighbours;
        CellState nextState;

        for(int row = 0; row < rowCount; row++)  {
            for(int col = 0; col < columnCount; col++)  {

                grid.get(row).get(col).setTransformed(false);

                neighbours = findAliveNeighbours(row, col);
                nextState = grid.get(row).get(col).determineNextState(neighbours);

                if (nextState != grid.get(row).get(col).getCurrentState()){
                    grid.get(row).get(col).updateCurrentState(nextState);
                    grid.get(row).get(col).setTransformed(true);
                }

            }
        }
    }


    public int findAliveNeighbours(int cellRow, int cellColumn){
        int count = 0;

        for(int row = cellRow-1; row<=cellRow+1; row++){

            for(int col = cellColumn-1; col<=cellColumn+1; col++){
                if (inRange(row, col) && !(row == cellRow & col==cellColumn)){
                    boolean cellIsAlive = grid.get(row).get(col).isTransformed() != grid.get(row).get(col).isAlive();
                    if(cellIsAlive){
                        count++;
                    }
                }
            }
        }
        return count;
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


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rowCount; i++)  {
            result.append((grid.get(i))).append("\n");
        }
        return result.toString();
    }
}
