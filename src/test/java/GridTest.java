import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {
    private Grid actualGrid;

    @BeforeEach
    void setUp() {
        actualGrid = new Grid(5,5);
    }

    @Test
    void Grid_FindAliveNeighbours_ReturnsCorrectNeighbourCount() {
        Grid actualGrid = new Grid(5,5);

        actualGrid.setCellState(0,0, CellState.ALIVE);
        actualGrid.setCellState(0,1, CellState.ALIVE);
        actualGrid.setCellState(0,2, CellState.ALIVE);
        actualGrid.setCellState(1,0, CellState.ALIVE);
        actualGrid.setCellState(1,2, CellState.ALIVE);
        actualGrid.setCellState(2, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);

        int expectedNeighbours = 8;
        int actualNeighbours = actualGrid.findAliveNeighbours(1,1);


        assertEquals(expectedNeighbours, actualNeighbours);
    }


    @Test
    void Grid_FindAliveNeighbours_ReturnsNoAliveCount() {
        //grid is initialised as all dead, so no setup required

        int expectedNeighbours = 0;
        int actualNeighbours = actualGrid.findAliveNeighbours(1,1);

        assertEquals(expectedNeighbours, actualNeighbours);
    }

    @Test
    void Grid_UpdateGrid_GridUpdates() {
        /*
        *
        *   Base          Next
        *   . . . . .       . . . . .
        *   . . . . .       . x . . .
        *   x x x . .  ->   x . x . .
        *   x x x . .       . . . x .
        *   x x x . .       x . x . .
        *
        * */

        actualGrid.setCellState(2, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);
        actualGrid.setCellState(3,0, CellState.ALIVE);
        actualGrid.setCellState(3,1, CellState.ALIVE);
        actualGrid.setCellState(3,2, CellState.ALIVE);
        actualGrid.setCellState(4,0, CellState.ALIVE);
        actualGrid.setCellState(4,1, CellState.ALIVE);
        actualGrid.setCellState(4,2, CellState.ALIVE);

        Grid expectedGrid = new Grid(5,5);
        expectedGrid.setCellState(1, 1, CellState.ALIVE);
        expectedGrid.setCellState(2, 0, CellState.ALIVE);
        expectedGrid.setCellState(2, 2, CellState.ALIVE);
        expectedGrid.setCellState(3,3, CellState.ALIVE);
        expectedGrid.setCellState(4,0, CellState.ALIVE);
        expectedGrid.setCellState(4,2, CellState.ALIVE);

        actualGrid.updateGrid();

        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

}