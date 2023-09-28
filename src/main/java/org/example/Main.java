package org.example;

public class Main {
    public static void main(String[] args) {
        Adventure game = new Adventure();
        game.start();
        UserInterface ui = new UserInterface(game);
        ui.start();
    }
}