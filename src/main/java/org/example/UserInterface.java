package org.example;

import java.util.Scanner;

public class UserInterface {

    String direction;
    String showLoot;
    String currentRoom;


    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    if (adventure.current.getNorth() != null) {
                        adventure.setCurrentRoom(adventure.current.getNorth());
                        System.out.println("You went north to " + adventure.current.getName());
                        System.out.println();
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.current.getSouth() != null) {
                        adventure.setCurrentRoom(adventure.current.getSouth());
                        System.out.println("You went south to " + adventure.current.getName());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "e", "east":
                    if (adventure.current.getEast() != null) {
                        adventure.setCurrentRoom(adventure.current.getEast());
                        System.out.println("You went east to " + adventure.current.getName());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.current.getWest() != null) {
                        adventure.setCurrentRoom(adventure.current.getWest());
                        System.out.println("You went west to " + adventure.current.getName());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "look":
                    System.out.println("You looked around and found nothing");
                    break;
            }
        }
        if (splitInput.length == 2) {
            switch (splitInput[0]) {
                case "go":
                    go(splitInput[1]);
                    break;
                case "look":
                    System.out.println("You looked " + splitInput[1] + " and found nothing");
                    break;

            }
        }
    }

    private void go(String direction) {
        switch (direction) {
            case "n", "north":
                System.out.println("You went north");
                break;
            case "s", "south":
                System.out.println("You went south");
                break;
            case "e", "east":
                System.out.println("You went east");
                break;
            case "w", "west":
                System.out.println("You went west");
                break;

        }
    }


    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
