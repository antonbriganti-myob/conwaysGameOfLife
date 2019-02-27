public class Main {

    public static void main(String[] args) {
        GridCoordinates gridCoord;
        InputParser parser = new InputParser();
        int rowSize = parser.getGridDimension("row");
        int colSize = parser.getGridDimension("column");

        Grid playGrid = new Grid(rowSize, colSize);
        System.out.println(playGrid.toString());

        while(parser.getUserBooleanDecision("Do you want change a cell to alive?")){
            gridCoord = parser.getGridCoordinates(rowSize, colSize);
            playGrid.setCellState(gridCoord, CellState.ALIVE);
            System.out.println(playGrid.toString());
        }

        System.out.println(playGrid.toString());

        while(parser.getUserBooleanDecision("Continue simulation?")){
            playGrid.updateBoard();
            System.out.println(playGrid.toString());
        }

    }
}