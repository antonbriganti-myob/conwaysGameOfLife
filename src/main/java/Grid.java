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

        for(int row = 0; row < rowCount; row++)  {
            grid.add(new ArrayList<Cell>());
            for(int col = 0; col < columnCount; col++)  {
                grid.get(row).add(new Cell(CellState.DEAD));
            }
        }
        return grid;
    }

    public void updateBoard(){
        ArrayList<UpdateCellGridCoordinates> cellUpdateList = getCellsToBeUpdated();
        for(UpdateCellGridCoordinates cellToBeUpdated:cellUpdateList){
            setCellState(cellToBeUpdated.getRow(), cellToBeUpdated.getCol(), cellToBeUpdated.getNextState());
        }

    }

    public ArrayList<UpdateCellGridCoordinates> getCellsToBeUpdated() {
        int neighbours;
        CellState nextState;
        ArrayList<UpdateCellGridCoordinates> cellUpdateList = new ArrayList<>();


        for(int row = 0; row < rowCount; row++)  {
            for(int col = 0; col < columnCount; col++)  {
                neighbours = findAliveNeighbours(row, col);
                nextState = grid.get(row).get(col).determineNextState(neighbours);
                if(nextState != grid.get(row).get(col).getCurrentState()){
                    cellUpdateList.add(new UpdateCellGridCoordinates(row, col, nextState));
                }
            }
        }

        return cellUpdateList;
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
            grid.get(row).get(column).updateCurrentState(nextState);
        }
    }

    private boolean inRange(int row, int column){
        return (0 <= row && row < rowCount) && (0 <= column && column < columnCount);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < rowCount; i++)  {
            result += (grid.get(i)) + "\n";
        }
        return result;
    }
}

class UpdateCellGridCoordinates{
    private int row;
    private int col;
    private CellState nextState;

    public UpdateCellGridCoordinates(int row, int col, CellState nextState) {
        this.row = row;
        this.col = col;
        this.nextState = nextState;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getNextState() {
        return nextState;
    }


}
