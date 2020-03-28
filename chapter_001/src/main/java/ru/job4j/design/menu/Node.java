package ru.job4j.design.menu;

import java.util.*;

/**
 * this class describes element od menu
 * @author mbardakov
 * @since 27.03.2020
 */
public class Node {
    private String id;
    private String parentId;
    private String name;
    private Node parent;
    private List<Node> children;
    Action action;
    String description;

    public Node(String id, String parentId, String name, Action action) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.children = new ArrayList<>();
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    /**
     * method performs specific action for element
     * @param input - input
     */
    public void act(Input input) {
        action.doAction(this, input);
    }

    public void addChild(Node child) {
        if (!this.children.contains(child) && child != null) {
            this.children.add(child);
        }
    }

    /**
     * method counts how much parents/grandparents has this element
     * @return number of parents
     */
    private int countParents() {
        var res = 0;
        var currentParent = parent;
        while (currentParent != null) {
            res++;
            currentParent = currentParent.getParent();
        }
        return res;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Map<String, Node> linkNodes(List<Node> nodeList) {
        var nodes = new HashMap<String, Node>();
        for (Node n : nodeList) {
            nodes.put(n.getId(), n);
        }
        for (Node n : nodeList) {
            var parentId = n.getParentId();
            if (parentId != null) {
                var parent = nodes.get(parentId);
                if (parent != null) {
                    n.setParent(parent);
                    parent.addChild(n);
                    nodes.put(parentId, parent);
                    nodes.put(n.getId(), n);
                }
            }
        }
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(id, node.id)
                && Objects.equals(parentId, node.parentId)
                && Objects.equals(name, node.name)
                && Objects.equals(parent, node.parent)
                && Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, parent, children);
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        result.append(id).append(" ").append(name).append(System.lineSeparator());
        for (Node n : children) {
            result.append("---".repeat(n.countParents())).append(n.toString());
        }
        return result.toString();
    }
}
