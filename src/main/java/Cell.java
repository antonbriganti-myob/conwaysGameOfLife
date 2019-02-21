public class Cell {
    public static final String ALIVE = "alive";
    public static final String DEAD = "dead";
    String status;

    public Cell(String status) {
        this.status = status;
    }

    void transform(int neighbourCount){
        if (isAlive()){
            if (neighbourCount == 2 || neighbourCount == 3){
                this.status = ALIVE;
            }
            else{
                this.status = DEAD;
            }
        }
        else{
            if (neighbourCount == 3){
                this.status = ALIVE;
            }
        }


    }

    boolean isAlive(){
        return this.status.equals(ALIVE);
    }
}
