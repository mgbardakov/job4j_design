package ru.job4j.design.tictactoe;

import java.util.Scanner;

/**
 * class for console input
 * @author mbardakov
 * @since  07.04.2020
 */
public class ConsoleInput implements Input {
    Scanner sc = new Scanner(System.in);
    Output output;

    public ConsoleInput(Output output) {
        this.output = output;
    }

    public ConsoleInput() {
        output = new ConsoleOutput();
    }

    @Override
    public String getString(String question) {
        output.displayString(question);
        return sc.nextLine();
    }

    @Override
    public int getInt(String question) {
        return Integer.parseInt(getString(question));
    }
}
