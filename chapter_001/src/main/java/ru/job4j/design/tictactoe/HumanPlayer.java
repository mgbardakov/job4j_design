package ru.job4j.design.tictactoe;

/**
 * player class
 * @author mbardakov
 * @since  07.04.2020
 */
public class HumanPlayer implements Player {
    private String name;
    private Sign sign;
    private Input input;
    private Game game;

    public HumanPlayer(String name, Sign sign, Input input, Game game) {
        this.name = name;
        this.sign = sign;
        this.input = input;
        this.game = game;
    }

    /**
     * sets certain mark on game table
     * @return successful/unsuccessful
     */
    @Override
    public boolean mark() {
        var result = false;
        var x = input.getInt("Enter x coordinate: ");
        var y = input.getInt("Enter y coordinate: ");
        if (game.isValidCell(x, y)) {
           result = game.mark(x, y, sign);
        }
        return result;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Sign getSign() {
        return sign;
    }
}

