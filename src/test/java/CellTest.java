import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void Cell_AliveAndHasTwoNeighbours_RemainsAlive() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(2);
        assertTrue(cell.isAlive());
    }

    @Test
    void Cell_AliveAndHasFourNeighbours_Dies() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(4);
        assertFalse(cell.isAlive());
    }

    @Test
    void Cell_AliveAndHasOneNeighbour_Dies() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(1);
        assertFalse(cell.isAlive());
    }

    @Test
    void Cell_DeadAndHasThreeNeighbours_BecomesAlive() {
        Cell cell = new Cell(Cell.DEAD);
        cell.transform(3);
        assertTrue(cell.isAlive());
    }

    @Test
    void Cell_DeadAndHasTwoNeighbours_RemainsDead() {
        Cell cell = new Cell(Cell.DEAD);
        cell.transform(2);
        assertFalse(cell.isAlive());
    }
}