import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void cellIsAliveAndHasTwoNeighboursRemainsAlive() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(2);
        assertTrue(cell.isAlive());
    }

    @Test
    void cellIsAliveAndHasFourNeighboursDies() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(4);
        assertFalse(cell.isAlive());
    }

    @Test
    void cellIsAliveAndHasOneNeighbourDies() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(1);
        assertFalse(cell.isAlive());
    }

    @Test
    void cellIsDeadAndHasThreeNeighboursComesBackToLife() {
        Cell cell = new Cell(Cell.DEAD);
        cell.transform(3);
        assertTrue(cell.isAlive());
    }

    @Test
    void cellIsDeadAndHasTwoNeighboursRemainsDead() {
        Cell cell = new Cell(Cell.DEAD);
        cell.transform(2);
        assertFalse(cell.isAlive());
    }
}