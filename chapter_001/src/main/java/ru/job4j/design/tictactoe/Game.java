package ru.job4j.design.tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * class with tictactoe logic
 * @author mbardakov
 * @since  07.04.2020
 */
public class Game {
    private Sign[][] table;
    private int winCount;

    public Game() {
        this(3, 3);
    }
    public Game(int fieldSize, int winCount) {
                table = new Sign[fieldSize][fieldSize];
                for (int i = 0; i < fieldSize; i++) {
                    for (int j = 0; j < fieldSize; j++) {
                       table[j][i] = Sign.NONE;
                    }
                }
                this.winCount = winCount;
    }

    public boolean mark(int x, int y, Sign sign) {
        var rsl = false;
        if (table[y][x] == Sign.NONE) {
            table[y][x] = sign;
            rsl = true;
        }
        return rsl;
    }

    public Map<String, Integer> gapCoordinates() {
        Map<String, Integer> rsl = new HashMap<>();
        var run = true;
        for (int y = 0; y < table.length && run; y++) {
            for (int x = 0; x < table.length && run; x++) {
                if (table[y][x] == Sign.NONE) {
                    rsl.put("x", x);
                    rsl.put("y", y);
                    run = false;
                }
            }
        }
        return rsl;
    }

    public boolean hasGap() {
        return Arrays.stream(table).flatMap(Arrays::stream).map(x -> x == Sign.NONE)
                .reduce((x, y) -> x || y).orElse(true);
    }

    public void displayTable(Output output) {
        output.displayTable(table);
    }

    /**
     * checks if someone wins
     * @param sign - X or O
     * @return win/doesn't win
     */
    public boolean checkWin(Sign sign) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (checkLine(i, j, 1, 0, winCount, sign)) {
                    return true;
                }
                if (checkLine(i, j, 1, 1, winCount, sign)) {
                    return true;
                }
                if (checkLine(i, j, 0, 1, winCount, sign)) {
                    return true;
                }
                if (checkLine(i, j, 1, -1, winCount, sign)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *
     * @param x - start x coordinate
     * @param y - start y coordinate
     * @param vx - delta x
     * @param vy - delta y
     * @param len - length to win
     * @param sign - X or O
     * @return if there is win
     */
    private boolean checkLine(int x, int y, int vx, int vy, int len, Sign sign) {
        final int far_x = x + (len - 1) * vx;
        final int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (table[y + i * vy][x + i * vx] != sign) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param x - x coordinate
     * @param y - y coordinate
     * @return valid cell/ non valid cell
     */
    public boolean isValidCell(int x, int y) {
        return (x < table.length && y < table.length) && (x >= 0 && y >= 0);
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Game currentGame = new Game();
        Player player1 = new HumanPlayer("Player1", Sign.X, new ConsoleInput(), currentGame);
        Player player2 = new HumanPlayer("Player2", Sign.O, new ConsoleInput(), currentGame);
        player1.setName(input.getString("Enter first player name: "));
        player2.setName(input.getString("Enter second player name: "));
        Player currentPlayer = player1;
        boolean run = true;
        int turn = 1;
        while (run) {
            currentGame.displayTable(output);
            currentPlayer = turn % 2 != 0 ?  player1 : player2;
            output.displayString(currentPlayer.getName() + " turn" + System.lineSeparator());
            if (currentPlayer.mark()) {
                if (currentGame.checkWin(currentPlayer.getSign()) || !currentGame.hasGap()) {
                    run = false;
                } else {
                    turn++;
                }
            }
        }
        currentGame.displayTable(output);
        if (currentGame.checkWin(currentPlayer.getSign())) {
            output.displayString(currentPlayer.getName() + " win");
        } else {
            output.displayString("game over");
        }
    }

}
