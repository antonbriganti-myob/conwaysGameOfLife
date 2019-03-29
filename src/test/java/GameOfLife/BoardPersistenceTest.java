package GameOfLife;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardPersistenceTest {

    @Test
    void BoardPersistence_SaveWorld_SavesTextRepresentationOfCurrentStateToWorld() throws IOException {
        Grid world = new Grid(3,3);
        String testFileLocation = "./other/save_test";
        BoardPersistence.saveWorld(world,testFileLocation);
        String expectedResult = "3,3\n. . .\n. . .\n. . .";
        String actualResult;

        actualResult = readAllLinesFromFile(testFileLocation);

        assertEquals(expectedResult, actualResult);

        cleanUpAfterSaveTest(testFileLocation);
    }

    private String readAllLinesFromFile(String testFileLocation) throws IOException {
        String actualResult;
        try (Stream<String> stream = Files.lines(Paths.get(testFileLocation))) {
            actualResult = stream.map(Object::toString).collect(Collectors.joining("\n"));
        }
        return actualResult;
    }

    private void cleanUpAfterSaveTest(String testFileLocation){
        File file = new File(testFileLocation);
        file.delete();
    }

    @Test
    void BoardPersistence_LoadWorld_CreatesLoadedWorldFromTestFile() {
        String actualResult;
        String testFileLocation = "./other/load_test";
        String expectedResult = "x . x\n. x .\nx . x";

        Grid world = BoardPersistence.loadWorld(testFileLocation);
        actualResult = world.toString();

        assertEquals(expectedResult, actualResult);

    }
}