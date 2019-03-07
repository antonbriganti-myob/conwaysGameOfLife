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
        GridCoordinates wrapAroundCoord;
        boolean cellIsAlive;

        for(int row = cellRow-1; row<=cellRow+1; row++){
            for(int col = cellColumn-1; col<=cellColumn+1; col++){
                if (!(row == cellRow & col==cellColumn)){
                    if(inRange(row, col)){
                        cellIsAlive = checkIfCellIsCurrentlyAlive(row, col);

                    }
                    else{
                        wrapAroundCoord = determineWrapAroundCoordinate(row, col);
                        cellIsAlive = checkIfCellIsCurrentlyAlive(wrapAroundCoord);
                    }

                    if(cellIsAlive){
                        count++;
                    }

                }
            }
        }
        return count;
    }

    boolean checkIfCellIsCurrentlyAlive(int row, int col) {
        return grid.get(row).get(col).isTransformed() != grid.get(row).get(col).isAlive();
    }

    boolean checkIfCellIsCurrentlyAlive(GridCoordinates coordinates) {
        return checkIfCellIsCurrentlyAlive(coordinates.getRow(), coordinates.getCol());
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
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rowCount; i++)  {
            result.append((grid.get(i))).append("\n");
        }
        return result.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
    }
}
