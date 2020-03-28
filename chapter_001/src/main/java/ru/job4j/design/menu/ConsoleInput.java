package ru.job4j.design.menu;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String askString(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
