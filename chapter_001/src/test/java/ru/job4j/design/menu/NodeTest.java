package ru.job4j.design.menu;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class NodeTest {

    @Test
    public void whenPrintOneNodeWithTwoSubnodes() {
        var node = new Node("1", null, "First", null);
        var subnode1 = new Node("1.1.", null, "Second", null);
        subnode1.setParent(node);
        var subnode2 = new Node("1.2.", null, "Third", null);
        subnode2.setParent(node);
        node.addChild(subnode1);
        node.addChild(subnode2);
        String expected = new StringBuilder().append("1 First").append(System.lineSeparator())
                          .append("---1.1. Second").append(System.lineSeparator())
                          .append("---1.2. Third").append(System.lineSeparator()).toString();
        String result = node.toString();
        assertThat(result, is(expected));
    }
}