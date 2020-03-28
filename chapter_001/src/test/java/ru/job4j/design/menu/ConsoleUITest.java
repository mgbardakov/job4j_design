package ru.job4j.design.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConsoleUITest {

    @Test
    public void whenMenuShowedThenExit() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        Input input = new StubInput(ps::print, new String[]{"exit"});
        ConsoleUI cui = new ConsoleUI(input, ps::print);
        var nodeList = new ArrayList<Node>();
        nodeList.add(new Node("", null, "Menu", null));
        nodeList.add(new Node("1.", "", "One", null));
        nodeList.add(new Node("1.1.", "1.", "One one", null));
        nodeList.add(new Node("2.", "", "Two", null));
        cui.init(nodeList);
        String expected = new StringBuilder().append(" Menu").append(System.lineSeparator())
                                             .append("---1. One").append(System.lineSeparator())
                                             .append("------1.1. One one").append(System.lineSeparator())
                                             .append("---2. Two").append(System.lineSeparator())
                                             .append("[exit] - exit the program").append(System.lineSeparator())
                                             .append("Enter id: ").toString();
        assertThat(bos.toString(), is(expected));
    }



}