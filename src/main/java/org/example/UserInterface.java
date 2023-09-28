package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        System.out.println("Welcome to the The Game currently know as Adventure Game");
        System.out.println("Write 'help' to show commands");
        System.out.println("You've entered:  " + adventure.getStartingRoom().getName());
        System.out.println(adventure.getStartingRoom().getDescription());
        while (true) {
            handleInput();
        }
    }
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.trim().toLowerCase().split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    if (adventure.movePlayer("north")) {
                        System.out.println("You went north to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.movePlayer("south")) {
                        System.out.println("You went south to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        System.out.println(adventure.getPlayerPosition().getDescription());
                        break;
                    }
                case "e", "east":
                    if (adventure.movePlayer("east")) {
                        System.out.println("You went east to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.movePlayer("west")) {
                        System.out.println("You went west to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "look":
                    showLoot(adventure.getCurrentRoomLoot());
                    System.out.println("\n" + adventure.getCurrentRoomDescription());
                    break;

                case "help":
                    help();
                    break;
                case "inv", "inventory":
                    showInventory(adventure.getPlayerInventory());
                    break;

            }
        } else {
            switch(splitInput[0]) {
                case "take":
                    if(adventure.takeItem(splitInput[1])) {
                        System.out.println("Added " + splitInput[1] + " to inventory.");
                    } else {
                        System.out.println(splitInput[1] +" doesn't exist");
                    }
                    break;
                case "drop":
                    if(adventure.dropItem(splitInput[1])) {
                        System.out.println("Dropped " + splitInput[1] + " in " + adventure.getCurrentRoomName());
                    } else {
                        System.out.println("You don't have that item");
                    }
                    break;
            }
        }
    }

    public void help() {
        System.out.println("You use the following commands to play the game;");
        System.out.println("To move in a direction enter 'N', 'S', 'E', 'W' to move north, south, east or west.");
        System.out.println("Enter 'look' to look around at your current location");
        System.out.println("Enter 'inv' or 'inventory' to open your inventory");
        System.out.println("Enter 'take NAME' to pick-up item");
        System.out.println("Enter 'drop NAME' to drop item");
    }


    public void showLoot(ArrayList<Item> loot) {
        if (!loot.isEmpty()) {
            System.out.println("You found: ");
            if (loot.size() == 1) {
                System.out.print(loot.get(0).getLongName());
            } else {
                for (Item item : loot) {
                    System.out.print(item.getLongName() + ", ");
                }
            }
        } else {
            System.out.print("You found nothing.");
        }

    }

    public void showInventory(ArrayList<Item> inventory) {
        if (!inventory.isEmpty()) {
            System.out.println("You are carrying: ");
            if (inventory.size() == 1) {
                System.out.print(inventory.get(0).getLongName());
            } else {
                for (Item item : inventory) {
                    System.out.print(item.getLongName() + ", ");
                }
            }
        } else {
            System.out.print("You have nothing.");
        }

    }

}
