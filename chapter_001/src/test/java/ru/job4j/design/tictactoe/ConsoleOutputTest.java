package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleOutputTest {

    @Test
    public void whenDisplaysTableWithXon00Yon01() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        Output output = new ConsoleOutput(printStream::print);
        Sign[][] param = new Sign[][]{
                {Sign.X, Sign.NONE, Sign.NONE},
                {Sign.O, Sign.NONE, Sign.NONE},
                {Sign.NONE, Sign.NONE, Sign.NONE},
        };
        output.displayTable(param);
        String expected = new StringBuilder().append(" X  *  * ").append(System.lineSeparator())
                                             .append(System.lineSeparator())
                                             .append(" O  *  * ").append(System.lineSeparator())
                                             .append(System.lineSeparator())
                                             .append(" *  *  * ").append(System.lineSeparator())
                                             .append(System.lineSeparator()).toString();
        assertThat(bos.toString(), is(expected));
    }
}