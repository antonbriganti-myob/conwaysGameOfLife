import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {
    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(CellState.ALIVE);
    }

    @Test
    void Cell_AliveAndHasTwoNeighbours_RemainsAlive() {
        CellState nextState = cell.determineNextState(2);
        assertEquals(nextState, CellState.ALIVE);
    }

    @Test
    void Cell_AliveAndHasOneNeighbour_Dies() {
        CellState nextState = cell.determineNextState(1);
        assertEquals(nextState, CellState.DEAD);
    }

    @Test
    void Cell_AliveAndHasFourNeighbours_Dies() {
        CellState nextState = cell.determineNextState(4);
        assertEquals(nextState, CellState.DEAD);
    }

    @Test
    void Cell_DeadAndHasThreeNeighbours_BecomesAlive() {
        cell.updateCurrentState(CellState.DEAD);
        CellState nextState = cell.determineNextState(3);
        assertEquals(nextState, CellState.ALIVE);
    }

    @Test
    void Cell_DeadAndHasTwoNeighbours_RemainsDead() {
        cell.updateCurrentState(CellState.DEAD);
        CellState nextState = cell.determineNextState(2);
        assertEquals(nextState, CellState.DEAD);
    }
}