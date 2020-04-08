package ru.job4j.design.tictactoe;

import java.util.function.Consumer;

/**
 * class for console output
 * @author mbardakov
 * @since  07.04.2020
 */
public class ConsoleOutput implements Output {
    private Consumer<String> con;

    public ConsoleOutput(Consumer<String> con) {
        this.con = con;
    }

    public ConsoleOutput() {
        this.con = System.out::print;
    }

    @Override
    public void displayTable(Sign[][] table) {
        for (Sign[] line : table) {
            for (Sign element : line) {
                String outputElement;
                switch (element) {
                    case X: outputElement = " X ";
                            break;
                    case O: outputElement = " O ";
                            break;
                    default: outputElement = " * ";
                            break;
                }
                con.accept(outputElement);
            }
            con.accept(System.lineSeparator() + System.lineSeparator());
        }
    }

    @Override
    public void displayString(String string) {
        con.accept(string);
    }
}
