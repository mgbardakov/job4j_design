package ru.job4j.design.tictactoe;

import java.util.Arrays;

public class BotPlayer implements Player {
    private String name;
    private Sign sign;
    private Game game;

    public BotPlayer(String name, Sign sign, Game game) {
        this.name = name;
        this.sign = sign;
        this.game = game;
    }

    @Override
    public boolean mark() {
        var rsl = false;
        var coordinated = game.gapCoordinates();
        if (!coordinated.isEmpty()) {
            game.mark(coordinated.get("x"), coordinated.get("y"), sign);
            rsl = true;
        }
        return rsl;
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
