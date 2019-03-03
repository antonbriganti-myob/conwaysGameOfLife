public class InputValidator {

    public boolean isValidBooleanAnswer(String input) {
        return input.equals("yes")||input.equals("no")||input.equals("y")||input.equals("n");
    }

    public boolean isValidCellStateAnswer(String input) {
        return input.equals("alive")||input.equals("dead");
    }

    public boolean isValidDimension(int dimension) {
        return dimension > 0;
    }

    public boolean isValidCoordinate(int row, int col, int rowSize, int colSize) {
        boolean validRow;
        boolean validCol;
        boolean validCoordinate;

        validRow = ((row>=0)&(row < rowSize));
        validCol = ((col>=0)&(col < colSize));
        validCoordinate = validCol&&validRow;

        return validCoordinate;
    }
}
