package ru.job4j.design.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ConsoleUI {
    private  Input input;
    private Consumer<String> con;
    private Map<String, Node> nodes;

    public ConsoleUI(Input input) {
        this.input = input;
        this.con = System.out::print;
    }

    public ConsoleUI(Input input, Consumer<String> con) {
        this.input = input;
        this.con = con;
    }

    public void init(List<Node> nodeList) {
          nodes = Node.linkNodes(nodeList);
          showMenu();
    }
    private void showMenu() {
        var run = true;
        while (run) {
            con.accept(nodes.get("").toString());
            con.accept("[exit] - exit the program");
            con.accept(System.lineSeparator());
            var key = input.askString("Enter id: ");
            if (nodes.containsKey(key)) {
                nodes.get(key).act(input);
            } else if (key.equals("exit")) {
                run = false;
            } else {
                con.accept("try again");
            }
        }
    }
    public static void main(String[] args) {
        ConsoleUI cui = new ConsoleUI(new ConsoleInput());
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node("", null, "Menu", new ShowDirectoryAction()));
        nodeList.add(new Node("1.", "", "First topic", new ShowDirectoryAction()));
        nodeList.add(new Node("1.1.", "1.", "Task 1.1.", new ShowDirectoryAction()));
        nodeList.add(new Node("1.1.1.", "1.1.", "Task 1.1.1.", new DescribeAction()));
        nodeList.get(nodeList.size() - 1).setDescription("task 1.1.1. description");
        nodeList.add(new Node("1.1.2.", "1.1.", "Task 1.1.2.", new DescribeAction()));
        nodeList.get(nodeList.size() - 1).setDescription("task 1.1.2. description");
        nodeList.add(new Node("1.2.", "1.", "Task 1.2", new DescribeAction()));
        nodeList.get(nodeList.size() - 1).setDescription("task 1.2 description");
        nodeList.add(new Node("2.", "", "Second topic", new ShowDirectoryAction()));
        cui.init(nodeList);

    }
}
