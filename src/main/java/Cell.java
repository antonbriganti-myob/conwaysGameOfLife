public class Cell {
    public static final String ALIVE = "alive";
    String status;

    public Cell(String status) {
        this.status = status;
    }

    void transform(int neighbourCount){
        if (neighbourCount == 2 || neighbourCount == 3){
            this.status = ALIVE;
        }
    }

    boolean isAlive(){
        return this.status.equals(ALIVE);
    }
}
