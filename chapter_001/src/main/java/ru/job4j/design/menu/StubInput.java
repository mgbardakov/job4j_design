package ru.job4j.design.menu;

import java.util.function.Consumer;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;
    private Consumer<String> con;

    public StubInput(Consumer<String> con, String[] answers) {
        this.con = con;
        this.answers = answers;
    }

    @Override
    public String askString(String question) {
        con.accept(question);
        return answers[position++];
    }
}
