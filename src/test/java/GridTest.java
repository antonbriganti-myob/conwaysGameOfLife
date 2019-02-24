import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void Grid_FindAliveNeighbours_ReturnsCorrectNeighbourCount() {
        Grid grid = new Grid(5,5);
        int neighbours = grid.findAliveNeighbours(1,1);
        assertEquals(8, neighbours);
    }

    @Test
    void Grid_FindAliveNeighbours_ReturnsNoAliveCount() {
        Grid grid = new Grid(5,5);
        grid.setCellState(0,0, CellState.DEAD);
        grid.setCellState(0,1, CellState.DEAD);
        grid.setCellState(0,2, CellState.DEAD);
        grid.setCellState(1,0, CellState.DEAD);
        grid.setCellState(1,2, CellState.DEAD);
        grid.setCellState(2, 0, CellState.DEAD);
        grid.setCellState(2, 1, CellState.DEAD);
        grid.setCellState(2, 2, CellState.DEAD);

        int neighbours = grid.findAliveNeighbours(1,1);

        assertEquals(8, neighbours);
    }

}