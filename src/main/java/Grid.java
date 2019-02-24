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

    private ArrayList<List<Cell>> createGrid(){
        ArrayList<List<Cell>> grid = new ArrayList<List<Cell>>();

        for(int i = 0; i < rowCount; i++)  {
            grid.add(new ArrayList<Cell>());
            for(int j = 0; j < columnCount; j++)  {
                grid.get(i).add(new Cell(CellState.ALIVE));
            }
        }
        return grid;
    }

    public int findAliveNeighbours(int cellRow, int cellColumn){
        int count = 0;
        for(int row = cellRow-1; row<=cellRow+1; row++){

            for(int col = cellColumn-1; col<=cellColumn+1; col++){
                if (inRange(row, col) && !(row == cellRow & col==cellColumn)){
                    if (grid.get(row).get(col).getCurrentState() == CellState.ALIVE)
                        count+=1;
                }
            }
        }
        return count;
    }

    public void setCellState(int row, int column, CellState nextState){
        if (inRange(row, column)){
            grid.get(row).get(column).setCurrentState(nextState);
        }
    }

    private boolean inRange(int row, int column){
        return (0 <= row && row < rowCount) && (0 <= column && column < columnCount);
    }


}
