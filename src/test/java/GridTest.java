import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private Grid actualGrid;

    @BeforeEach
    void setUp() {
        actualGrid = new Grid(5,5);
    }

    @Test
    void Grid_FindAliveNeighbours_ReturnsCorrectNeighbourCount() {
        actualGrid.setCellState(0,0, CellState.ALIVE);
        actualGrid.setCellState(0,1, CellState.ALIVE);
        actualGrid.setCellState(0,2, CellState.ALIVE);
        actualGrid.setCellState(1,0, CellState.ALIVE);
        actualGrid.setCellState(1,2, CellState.ALIVE);
        actualGrid.setCellState(2, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);

        int neighbours = actualGrid.findAliveNeighbours(1,1);

        assertEquals(8, neighbours);
    }

    void foo() {
        //given


        //when


        //then

    }

    @Test
    void Grid_FindAliveNeighbours_ReturnsNoAliveCount() {
//        actualGrid.setCellState(0,0, CellState.DEAD);
//        actualGrid.setCellState(0,1, CellState.DEAD);
//        actualGrid.setCellState(0,2, CellState.DEAD);
//        actualGrid.setCellState(1,0, CellState.DEAD);
//        actualGrid.setCellState(1,2, CellState.DEAD);
//        actualGrid.setCellState(2, 0, CellState.DEAD);
//        actualGrid.setCellState(2, 1, CellState.DEAD);
//        actualGrid.setCellState(2, 2, CellState.DEAD);

        int neighbours = actualGrid.findAliveNeighbours(1,1);

        assertEquals(0, neighbours);
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

        actualGrid.updateBoard();

        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

}