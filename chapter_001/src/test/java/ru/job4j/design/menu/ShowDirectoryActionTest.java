package ru.job4j.design.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShowDirectoryActionTest {

    @Test
    public void whenShowsSubdirectory() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        Input input = new StubInput(ps::print, new String[]{"b"});
        Action action = new ShowDirectoryAction(ps::print);
        var nodeList = new ArrayList<Node>();
        nodeList.add(new Node("", null, "Menu", null));
        nodeList.add(new Node("1.", "", "One", null));
        nodeList.add(new Node("1.1.", "1.", "One one", null));
        nodeList.add(new Node("2.", "", "Two", null));
        var nodes = Node.linkNodes(nodeList);
        String expected = new StringBuilder().append("1. One").append(System.lineSeparator())
                                             .append("---1.1. One one").append(System.lineSeparator())
                                             .append("[b] - return to main menu").append(System.lineSeparator())
                                             .append("Enter id: ").toString();
        action.doAction(nodes.get("1."), input);
        assertThat(bos.toString(), is(expected));
    }
}