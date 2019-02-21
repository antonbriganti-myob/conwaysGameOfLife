import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void cellIsAliveAndHasTwoNeighboursRemainsAlive() {
        Cell cell = new Cell(Cell.ALIVE);
        cell.transform(2);
        assertTrue(cell.isAlive() == true);
    }
}