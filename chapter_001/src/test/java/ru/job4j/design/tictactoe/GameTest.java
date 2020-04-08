package ru.job4j.design.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void whenXWinInColumn() {
        Game testGame = new Game(6, 4);
        testGame.mark(3, 0, Sign.X);
        testGame.mark(3, 1, Sign.X);
        testGame.mark(3, 2, Sign.X);
        testGame.mark(3, 3, Sign.X);
        assertThat(testGame.checkWin(Sign.X), is(true));
    }

    @Test
    public void whenXWinInRow() {
        Game testGame = new Game(6, 4);
        testGame.mark(1, 1, Sign.X);
        testGame.mark(2, 1, Sign.X);
        testGame.mark(3, 1, Sign.X);
        testGame.mark(4, 1, Sign.X);
        assertThat(testGame.checkWin(Sign.X), is(true));
    }

    @Test
    public void whenXWinOnDiagonal() {
        Game testGame = new Game(6, 4);
        testGame.mark(1, 1, Sign.X);
        testGame.mark(2, 2, Sign.X);
        testGame.mark(3, 3, Sign.X);
        testGame.mark(4, 4, Sign.X);
        assertThat(testGame.checkWin(Sign.X), is(true));
    }

    @Test
    public void whenXWinOnBackDiagonal() {
        Game testGame = new Game(6, 4);
        testGame.mark(1, 4, Sign.X);
        testGame.mark(2, 3, Sign.X);
        testGame.mark(3, 2, Sign.X);
        testGame.mark(4, 1, Sign.X);
        assertThat(testGame.checkWin(Sign.X), is(true));
    }

}