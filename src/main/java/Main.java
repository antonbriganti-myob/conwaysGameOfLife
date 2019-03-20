import Game.ConwaysGameOfLife;
import IO.ConsoleInputOutput;

public class Main {

    public static void main(String[] args) {
        ConwaysGameOfLife gameOfLife = new ConwaysGameOfLife(new ConsoleInputOutput());
        gameOfLife.startGame();
    }

}