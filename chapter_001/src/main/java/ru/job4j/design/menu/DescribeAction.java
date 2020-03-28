package ru.job4j.design.menu;

public class DescribeAction implements Action  {
    @Override
    public void doAction(Node node, Input input) {
        System.out.println(node.getDescription());
        var run = true;
        while (run) {
            var line = input.askString("[b] - return to previous menu ");
            if (line.equals("b")) {
                run = false;
            }
        }
    }
}
