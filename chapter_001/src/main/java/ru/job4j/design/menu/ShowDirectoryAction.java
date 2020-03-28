package ru.job4j.design.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ShowDirectoryAction implements Action {
    private Consumer<String> con;

    public ShowDirectoryAction() {
        this.con = System.out::print;
    }

    public ShowDirectoryAction(Consumer<String> con) {
        this.con = con;
    }
    /**
     * show subdirectory of menu
     * @param node - root of subdirectory
     * @param input - input
     */
    @Override
    public void doAction(Node node, Input input) {
        var run = true;
        Map<String, Node> showMap = flatten(node, new HashMap<>());
        var tempParent = node.getParent();
        node.setParent(null);
        while (run) {
            con.accept(node.toString());
            con.accept("[b] - return to main menu");
            con.accept(System.lineSeparator());
            var key = input.askString("Enter id: ");
            if (showMap.containsKey(key)) {
                showMap.get(key).act(input);
            } else if (key.equals("b")) {
                run = false;
            } else {
                con.accept("try again");
                con.accept(System.lineSeparator());
            }
        }
        node.setParent(tempParent);
    }

    /**
     * makes new model map to show
     * @param node - root node
     * @param flatMap - map to fill with nodes
     * @return filled map
     */
    private static Map<String, Node> flatten(Node node,  Map<String, Node> flatMap) {
        if (node != null) {
            flatMap.put(node.getId(), node);
        }
        assert node != null;
        List<Node> children = node.getChildren();
        for (Node child : children) {
            if (child.getChildren() != null) {
                flatten(child, flatMap);
            }
        }
        return flatMap;
    }
}
