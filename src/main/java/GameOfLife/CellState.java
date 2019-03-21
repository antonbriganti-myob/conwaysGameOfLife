package GameOfLife;

public enum CellState{
    ALIVE("x"),
    DEAD(".");

    private final String value;

    CellState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
