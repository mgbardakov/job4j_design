package ru.job4j.design.tictactoe;

public interface Player {
    boolean mark();
    String getName();
    void setName(String name);
    Sign getSign();
}
