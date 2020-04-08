package ru.job4j.design.tictactoe;

/**
 * fake input class
 * @author mbardakov
 * @since  07.04.2020
 */
public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String getString(String question) {
        return answers[position++];
    }

    @Override
    public int getInt(String question) {
        return Integer.parseInt(getString(question));
    }
}
