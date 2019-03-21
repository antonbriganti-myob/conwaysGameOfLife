package Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {

    private InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    @Test
    void Validator_isValidBooleanAnswer_yes_ReturnsTrue() {
        boolean actualResult = validator.isValidBooleanAnswer("yes");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidBooleanAnswer_no_ReturnsTrue() {
        boolean actualResult = validator.isValidBooleanAnswer("no");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidBooleanAnswer_y_ReturnsTrue() {
        boolean actualResult = validator.isValidBooleanAnswer("y");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidBooleanAnswer_n_ReturnsTrue() {
        boolean actualResult = validator.isValidBooleanAnswer("n");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidCellStateAnswer_alive_ReturnsTrue() {
        boolean actualResult = validator.isValidCellStateAnswer("alive");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidCellStateAnswer_dead_ReturnsTrue() {
        boolean actualResult = validator.isValidCellStateAnswer("dead");
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidDimension_ReturnsTrueOnValidDimension() {
        boolean actualResult = validator.isValidDimension(10);
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidDimension_ReturnsFalseOnZero() {
        boolean actualResult = validator.isValidDimension(0);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsTrueOnValidRowAndCol() {
        int rowCount = 5;
        int colCount = 6;
        int row = 4;
        int col = 5;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertTrue(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidRow_LowerThanZero() {
        int rowCount = 5;
        int colCount = 6;
        int row = -1;
        int col = 5;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidRow_EqualToRowCount() {
        int rowCount = 5;
        int colCount = 6;
        int row = 5;
        int col = 5;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidRow_HigherThanRowCount() {
        int rowCount = 5;
        int colCount = 6;
        int row = 6;
        int col = 5;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidCol_LowerThanZero() {
        int rowCount = 5;
        int colCount = 6;
        int row = 4;
        int col = -1;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidCol_EqualToColCount() {
        int rowCount = 5;
        int colCount = 6;
        int row = 4;
        int col = 6;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }

    @Test
    void Validator_isValidCoordinate_ReturnsFalseOnInvalidCol_HigherThanColCount() {
        int rowCount = 5;
        int colCount = 6;
        int row = 4;
        int col = 7;

        boolean actualResult = validator.isValidCoordinate(row, col, rowCount, colCount);
        assertFalse(actualResult);
    }
}