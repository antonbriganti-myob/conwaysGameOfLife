package Cell;

public class Cell {
    private CellState currentState;

    public Cell(CellState currentState) {
        this.currentState = currentState;
    }

    public void updateCurrentState(CellState nextState) {
        this.currentState = nextState;
    }


    public CellState determineNextState(int aliveNeighbours){
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

    public boolean isAlive(){
        return this.currentState.equals(CellState.ALIVE);
    }

    @Override
    public String toString() {
        return currentState.toString();
    }
}
