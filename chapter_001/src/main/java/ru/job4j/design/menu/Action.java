package ru.job4j.design.menu;

import java.util.function.Consumer;

public interface Action {
    void doAction(Node node, Input input);
}
