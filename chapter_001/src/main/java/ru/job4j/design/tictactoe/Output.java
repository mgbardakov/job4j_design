package ru.job4j.design.tictactoe;

/**
 * output interface
 * @author mbardakov
 * @since  07.04.2020
 */
public interface Output {
    /**
     *
     * @param table game table for output
     */
    void displayTable(Sign[][]table);

    /**
     *
     * @param string output string
     */
    void displayString(String string);
}
