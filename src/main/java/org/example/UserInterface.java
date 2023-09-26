package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    if (adventure.getCurrentRoom().getNorth() != null) {
                        adventure.setCurrentRoom(adventure.getCurrentRoom().getNorth());
                        System.out.println("You went north to " + adventure.getCurrentRoom().getName());
                        System.out.println(adventure.getCurrentRoom().getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.getCurrentRoom().getSouth() != null) {
                        adventure.setCurrentRoom(adventure.getCurrentRoom().getSouth());
                        System.out.println("You went south to " + adventure.getCurrentRoom().getName());
                        System.out.println(adventure.getCurrentRoom().getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        System.out.println(adventure.getCurrentRoom().getDescription());
                        break;
                    }
                case "e", "east":
                    if (adventure.getCurrentRoom().getEast() != null) {
                        adventure.setCurrentRoom(adventure.getCurrentRoom().getEast());
                        System.out.println("You went east to " + adventure.getCurrentRoom().getName());
                        System.out.println(adventure.getCurrentRoom().getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.getCurrentRoom().getWest() != null) {
                        adventure.setCurrentRoom(adventure.getCurrentRoom().getWest());
                        System.out.println("You went west to " + adventure.getCurrentRoom().getName());
                        System.out.println(adventure.getCurrentRoom().getDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "look":
                    showLoot(adventure.getCurrentRoom().getLoot());
                    System.out.println("\n" + adventure.getCurrentRoom().getDescription());
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


    public void showLoot(ArrayList<String> loot) {
        if (!loot.isEmpty()) {
            System.out.println("You found: ");
            if (loot.size() == 1) {
                System.out.print(loot.get(0));
            } else {
                for (String item : loot) {
                    System.out.print(item + ", ");
                }
            }
        } else {
            System.out.print("You found nothing.");
        }

    }

}
