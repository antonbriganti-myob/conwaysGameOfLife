enum CellState{
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

public class Cell {
    private CellState currentState;
    private CellState lastState;


    public Cell(CellState currentState) {
        this.currentState = currentState;
        this.lastState = currentState;
    }

    public CellState getCurrentState() {
        return currentState;
    }

    public void updateCurrentState(CellState currentState) {
        this.lastState = this.currentState;
        this.currentState = currentState;

    }

    public CellState getLastState() {
        return lastState;
    }

    CellState determineNextState(int aliveNeighbours){
        CellState nextState;

        if(isAlive()){
            if (aliveNeighbours == 2 || aliveNeighbours == 3){
                nextState = CellState.ALIVE;
            }
            else{
                nextState = CellState.DEAD;
            }
        }
        else{
            if (aliveNeighbours == 3){
                nextState = CellState.ALIVE;
            }
            else{
                nextState = CellState.DEAD;
            }
        }

        return nextState;
    }

    boolean isAlive(){
        return this.currentState.equals(CellState.ALIVE);
    }

    @Override
    public String toString() {
        String result = currentState==CellState.ALIVE ? "x" : ".";
        return result;
    }
}
