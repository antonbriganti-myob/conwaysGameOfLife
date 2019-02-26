public class Main {

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        int rowSize = parser.getGridDimension("row");
        int colSize = parser.getGridDimension("column");

        Grid playGrid = new Grid(rowSize, colSize);
        System.out.println(playGrid.toString());
    }
}