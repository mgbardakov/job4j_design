package ru.job4j.design.tictactoe;

/**
 * input interface
 * @author mbardakov
 * @since  07.04.2020
 */
public interface Input {
    /**
     *
     * @param question output string     *
     * @return string value
     */
    String getString(String question);

    /**
     *
     * @param question output string
     * @return int value
     */
    int getInt(String question);
}
