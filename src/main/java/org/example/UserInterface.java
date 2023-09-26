package org.example;

import java.util.Scanner;

public class UserInterface {

    String direction;
    String showLoot;
    String currentRoom;


    public void handleInput(Adventure adventure) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    if (adventure.current.getNorth() != null) {
                        adventure.setCurrentRoom(adventure.current.getNorth());
                        System.out.println("You went north to " + adventure.current.getName());
                        System.out.println(adventure.current.getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.current.getSouth() != null) {
                        adventure.setCurrentRoom(adventure.current.getSouth());
                        System.out.println("You went south to " + adventure.current.getName());
                        System.out.println(adventure.current.getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        System.out.println(adventure.current.getDescription());
                        break;
                    }
                case "e", "east":
                    if (adventure.current.getEast() != null) {
                        adventure.setCurrentRoom(adventure.current.getEast());
                        System.out.println("You went east to " + adventure.current.getName());
                        System.out.println(adventure.current.getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.current.getWest() != null) {
                        adventure.setCurrentRoom(adventure.current.getWest());
                        System.out.println("You went west to " + adventure.current.getName());
                        System.out.println(adventure.current.getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "look":
                    System.out.println("You looked around and found nothing new");
                    System.out.println(adventure.current.getDescription());
                    break;

                case "help":
                    help();

            }

        }
    }
    public void help() {
        System.out.println("You use the following commands to play the game;");
        System.out.println("To move in a direction enter 'N', 'S', 'E', 'W' to move north, south, east or west.");
        System.out.println("Enter 'look' to look around at your current location");
    }


    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
