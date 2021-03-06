package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    void Grid_FindAliveNeighbours_TopLeftCorner_CountsVerticalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . .
         *   . . .
         *   x . .
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 0, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(0,0);


        assertEquals(expectedNeighbours, actualNeighbours);
    }


    @Test
    void Grid_FindAliveNeighbours_BottomLeftCorner_CountsVerticalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . .
         *   . . .
         *   x . .
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 0, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(2,0);


        assertEquals(expectedNeighbours, actualNeighbours);
    }

    @Test
    void Grid_FindAliveNeighbours_TopLeftCorner_CountsHorizontalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . x
         *   . . .
         *   . . .
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(0, 2, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(0,0);


        assertEquals(expectedNeighbours, actualNeighbours);
    }


    @Test
    void Grid_FindAliveNeighbours_TopRightCorner_CountsHorizontalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . x
         *   . . .
         *   . . .
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(0, 2, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(0,2);


        assertEquals(expectedNeighbours, actualNeighbours);
    }

    @Test
    void Grid_FindAliveNeighbours_TopLeftCorner__CountsVerticalAndHorizontalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . .
         *   . . .
         *   . . x
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(0,0);


        assertEquals(expectedNeighbours, actualNeighbours);
    }

    @Test
    void Grid_FindAliveNeighbours_BottomRightCorner_CountsVerticalAndHorizontalWrapAroundAsNeighbour() {
        /*
         *   Test State
         *   x . .
         *   . . .
         *   . . x
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(0, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);
        int expectedNeighbours = 1;


        int actualNeighbours = actualGrid.findAliveNeighbours(0,0);


        assertEquals(expectedNeighbours, actualNeighbours);
    }

    @Test
    void Grid_toString_ReturnsExpectedExpectedFormat() {
        /*
         *
         *  . . .
         *  . . .
         *  . . .
         *
         * */

        actualGrid = new Grid(3,3);
        String expectedOutput = ". . .\n. . .\n. . .";

        assertEquals(expectedOutput, actualGrid.toString());
    }

    @Test
    void Grid_toString_ReturnsExpectedExpectedFormat_AfterUpdate() {
        /*
         *
         *  . . .    x x x
         *  x x x -> x x x
         *  . . .    x x x
         *
         * */

        actualGrid = new Grid(3,3);
        actualGrid.setCellState(1,0, CellState.ALIVE);
        actualGrid.setCellState(1,1, CellState.ALIVE);
        actualGrid.setCellState(1,2, CellState.ALIVE);
        actualGrid.updateGrid();

        String expectedOutput = "x x x\nx x x\nx x x";

        assertEquals(expectedOutput, actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_AliveCellsWithTwoNeighbours_StayAlive() {
        /*
         *   Tub Pattern
         *   Base          Next
         *   . . . . .       . . . . .
         *   . . x . .       . . x . .
         *   . x . x .  ->   . x . x .
         *   . . x . .       . . x . .
         *   . . . . .       . . . . .
         *
         * */

        actualGrid.setCellState(1, 1, CellState.ALIVE);
        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);

        Grid expectedGrid = new Grid(5,5);
        expectedGrid.setCellState(1, 1, CellState.ALIVE);
        expectedGrid.setCellState(1, 2, CellState.ALIVE);
        expectedGrid.setCellState(2, 1, CellState.ALIVE);
        expectedGrid.setCellState(2, 2, CellState.ALIVE);


        actualGrid.updateGrid();


        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_AliveCellsWithThreeNeighbours_StayAlive() {
        /*
         *   Beehive Pattern
         *   Base          Next
         *   . . . . . .      . . . . . .
         *   . . x x . .      . . x x . .
         *   . x . . x . ->   . x . . x .
         *   . . x x . .      . . x x . .
         *   . . . . . .      . . . . . .
         *
         * */

        actualGrid = new Grid(5, 6);
        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(1, 3, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 4, CellState.ALIVE);
        actualGrid.setCellState(3, 2, CellState.ALIVE);
        actualGrid.setCellState(3, 3, CellState.ALIVE);

        Grid expectedGrid = new Grid(5, 6);
        expectedGrid.setCellState(1, 2, CellState.ALIVE);
        expectedGrid.setCellState(1, 3, CellState.ALIVE);
        expectedGrid.setCellState(2, 1, CellState.ALIVE);
        expectedGrid.setCellState(2, 4, CellState.ALIVE);
        expectedGrid.setCellState(3, 2, CellState.ALIVE);
        expectedGrid.setCellState(3, 3, CellState.ALIVE);


        actualGrid.updateGrid();


        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_BlockPattern_RemainsStill() {
        /*
         *
         *   Base          Next
         *   . . . . .       . . . . .
         *   . x x . .       . x x . .
         *   . x x . .  ->   . x x . .
         *   . . . . .       . . . . .
         *   . . . . .       . . . . .
         *
         * */

        actualGrid.setCellState(1, 1, CellState.ALIVE);
        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);

        Grid expectedGrid = new Grid(5,5);
        expectedGrid.setCellState(1, 1, CellState.ALIVE);
        expectedGrid.setCellState(1, 2, CellState.ALIVE);
        expectedGrid.setCellState(2, 1, CellState.ALIVE);
        expectedGrid.setCellState(2, 2, CellState.ALIVE);


        actualGrid.updateGrid();


        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_AliveCellsWithOneNeighbour_Dies() {
        /*
         *   Base          Next
         *   . . . . .       . . . . .
         *   . . x . .       . . . . .
         *   . . x . .  ->   . . . . .
         *   . . . . .       . . . . .
         *   . . . . .       . . . . .
         *
         * */

        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);


        Grid expectedGrid = new Grid(5,5);

        actualGrid.updateGrid();

        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_AliveCellWithFourNeighbours_Dies() {
        /*
         *   Test State
         *   . . . . .
         *   . x x x .
         *   . . x x .
         *   . . . . .
         *   . . . . .
         *
         * */

        actualGrid.setCellState(1, 1, CellState.ALIVE);
        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(1, 3, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);
        actualGrid.setCellState(2, 3, CellState.ALIVE);


        actualGrid.updateGrid();
        boolean cellIsAlive = actualGrid.isCellAlive(2, 2);


        assertFalse(cellIsAlive);
    }

    @Test
    void Grid_UpdateGrid_GridUpdates() {
        /*
         *
         *   Base          Next
         *   . . . . .       . . x . .
         *   . x x x .       . x . x .
         *   . x x x .  ->   x . . . x
         *   . x x x .       . x . x .
         *   . . . . .       . . x . .
         *
         * */

        actualGrid.setCellState(1, 1, CellState.ALIVE);
        actualGrid.setCellState(1, 2, CellState.ALIVE);
        actualGrid.setCellState(1, 3, CellState.ALIVE);

        actualGrid.setCellState(2,1, CellState.ALIVE);
        actualGrid.setCellState(2,2, CellState.ALIVE);
        actualGrid.setCellState(2,3, CellState.ALIVE);

        actualGrid.setCellState(3,1, CellState.ALIVE);
        actualGrid.setCellState(3,2, CellState.ALIVE);
        actualGrid.setCellState(3,3, CellState.ALIVE);

        Grid expectedGrid = new Grid(5,5);
        expectedGrid.setCellState(0, 2, CellState.ALIVE);
        expectedGrid.setCellState(1, 1, CellState.ALIVE);
        expectedGrid.setCellState(1, 3, CellState.ALIVE);
        expectedGrid.setCellState(2,0, CellState.ALIVE);
        expectedGrid.setCellState(2,4, CellState.ALIVE);
        expectedGrid.setCellState(3,1, CellState.ALIVE);
        expectedGrid.setCellState(3,3, CellState.ALIVE);
        expectedGrid.setCellState(4,2, CellState.ALIVE);


        actualGrid.updateGrid();


        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }

    @Test
    void Grid_UpdateGrid_GridUpdatesAccordingToWrapAround() {
        /*
         *
         *   Base          Next
         *   . . . . .       . . . . .
         *   . . . . .       . x . . .
         *   x x x . .  ->   x . x x .
         *   . x x . x       . . . . x
         *   x x x . .       x . x x .
         *
         * */

        actualGrid.setCellState(2, 0, CellState.ALIVE);
        actualGrid.setCellState(2, 1, CellState.ALIVE);
        actualGrid.setCellState(2, 2, CellState.ALIVE);
        actualGrid.setCellState(3,1, CellState.ALIVE);
        actualGrid.setCellState(3,2, CellState.ALIVE);
        actualGrid.setCellState(3,4, CellState.ALIVE);
        actualGrid.setCellState(4,0, CellState.ALIVE);
        actualGrid.setCellState(4,1, CellState.ALIVE);
        actualGrid.setCellState(4,2, CellState.ALIVE);

        Grid expectedGrid = new Grid(5,5);
        expectedGrid.setCellState(0, 1, CellState.ALIVE);
        expectedGrid.setCellState(1, 1, CellState.ALIVE);
        expectedGrid.setCellState(2, 0, CellState.ALIVE);
        expectedGrid.setCellState(2, 2, CellState.ALIVE);
        expectedGrid.setCellState(2, 3, CellState.ALIVE);
        expectedGrid.setCellState(3,4, CellState.ALIVE);
        expectedGrid.setCellState(4,0, CellState.ALIVE);
        expectedGrid.setCellState(4,2, CellState.ALIVE);
        expectedGrid.setCellState(4,3, CellState.ALIVE);

        actualGrid.updateGrid();

        assertEquals(expectedGrid.toString(), actualGrid.toString());
    }


}