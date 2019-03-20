package Game;

public enum CellState{
    ALIVE {
        public String toString() {
            return "alive";
        }
    },
    DEAD {
        public String toString() {
            return "dead";
        }
    }
}
