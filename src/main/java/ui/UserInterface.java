package ui;

import data.*;
import game.Adventure;
import item.Item;


import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
        System.out.println("Welcome to the The Game currently know as game.Adventure Game");
        System.out.println("Write 'help' to show commands");
        System.out.println("You've entered:  " + adventure.getCurrentRoomName());
        System.out.println(adventure.getCurrentRoomDescription());
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
                    if (adventure.movePlayer(Direction.NORTH)) {
                        System.out.println("You went north to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "s", "south":
                    if (adventure.movePlayer(Direction.SOUTH)) {
                        System.out.println("You went south to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    }
                case "e", "east":
                    if (adventure.movePlayer(Direction.EAST)) {
                        System.out.println("You went east to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }
                case "w", "west":
                    if (adventure.movePlayer(Direction.WEST)) {
                        System.out.println("You went west to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "up":
                    if (adventure.movePlayer(Direction.UP)) {
                        System.out.println("You went up to " + adventure.getCurrentRoomName());
                        System.out.println(adventure.getCurrentRoomDescription());
                        break;
                    } else {
                        System.out.println("You can't go that way.");
                        break;
                    }

                case "down":
                    if (adventure.movePlayer(Direction.DOWN)) {
                        System.out.println("You went down to " + adventure.getCurrentRoomName());
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
                case "consumables":
                    showInventory(adventure.getPlayerConsumables());
                    break;
                case "hp":
                    System.out.println("You currently have " + adventure.getPlayerHealthPoints() +"HP");
                    break;

            }
        } else {
            switch(splitInput[0]) {
                case "take":
                    Item itemToTake = adventure.takeItem(splitInput[1]);
                    if(itemToTake != null) {
                        System.out.println("Added " + itemToTake.getLongName() + " to inventory.");
                    } else {
                        System.out.println(splitInput[1] +" doesn't exist");
                    }
                    break;
                case "drop":
                    Item itemToDrop = adventure.dropItem(splitInput[1]);
                    if(itemToDrop != null) {
                        System.out.println("Dropped " + itemToDrop.getLongName() + " in " + adventure.getCurrentRoomName());
                    } else {
                        System.out.println("You don't carry " + splitInput[1]);
                    }
                    break;
                case "consume", "eat", "inject", "sniff", "drink":
                    ReturnConsumable itemToConsume = adventure.consumeItem(splitInput[1]);

                    switch(itemToConsume.getStatus()) {
                        case CONSUMABLE:
                            System.out.println("Consumed " + itemToConsume.getOutputText() + " and gained " + itemToConsume.getItemHealthGain() + "HP");
                            System.out.println("Your current health is now " + adventure.getPlayerHealthPoints() + "/" + adventure.getPlayerMaxHealthPoints() + "HP");
                            if(itemToConsume.getEffect() != 0) {
                                System.out.println("You gained " + itemToConsume.getEffect() + " attack power.");
                            }
                            break;
                        case NON_CONSUMABLE:
                            System.out.println("You can't consume " + itemToConsume.getOutputText());
                            break;
                        case MISSING:
                            System.out.println("You don't carry " + splitInput[1]);
                            break;

                    }
                    break;
                case "equip":
                    Status itemToEquip = adventure.equip(splitInput[1]);

                    switch (itemToEquip){
                        case EQUIPPABLE:
                            System.out.println("You equipped " + splitInput[1]);
                            break;
                        case NON_EQUIPPABLE:
                            System.out.println(splitInput[1] + " is not equippable ");
                            break;
                        case BROKEN:
                            System.out.println(splitInput[1] + " is broken");
                            break;
                        case MISSING:
                            System.out.println(splitInput[1] + " is missing ");
                            break;

                    }
                    break;
                case "attack":
                    ReturnAttack attack = adventure.attack(splitInput[1]);

                    switch(attack.getStatus()) {
                        case MISSING:
                            System.out.println("You flail your fists in the air awkwardly dealing no damage. ");
                            break;
                        case NO_ENEMY:
                            System.out.println("You swing your " + attack.getOutputText() + " and hit nothing but air.");
                            break;
                        case BROKEN:
                            System.out.println(attack.getOutputText() + " is broken.");
                            break;
                        case SUCCESS:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getOutputText() + " and deal " + attack.getPlayerDamage() + " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " has " + attack.getEnemyHealthPoints() + "HP left.");
                            System.out.println(attack.getEnemyName() + " hit you for " + attack.getEnemyDamage() + " damage, and you have " + adventure.getPlayerHealthPoints() + "/" + adventure.getPlayerMaxHealthPoints() + "HP");
                            if(attack.getLostEffect() != 0) {
                                System.out.println("You feel normal.");
                            }
                            if (attack.isBroken())
                                System.out.println("Your " + attack.getOutputText() + " broke.");
                            break;
                        case ENEMY_DEAD:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getOutputText() + " and deal " + attack.getPlayerDamage() + " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " falls over and dies.");
                            if(attack.getLostEffect() != 0) {
                                System.out.println("You feel normal.");
                            }
                            if (attack.isBroken())
                                System.out.println("Your " + attack.getOutputText() + " broke.");
                            break;
                        case PLAYER_DEAD:
                            System.out.println("You attack " + attack.getEnemyName() + " with " + attack.getOutputText() + " and deal " + attack.getPlayerDamage() + " damage. Good job!");
                            System.out.println(attack.getEnemyName() + " hit you for " + attack.getEnemyDamage() + " damage, and you die.");
                            System.exit(0);
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
        System.out.println("Enter 'take [item]' to pick-up item");
        System.out.println("Enter 'drop [item]' to drop item");
        System.out.println("Enter 'consumables' for a list of your consumables");
        System.out.println("Enter 'consume [consumable]' to gain it's effect");
        System.out.println("Enter 'HP' to show current healthpoints");
        System.out.println("Enter 'equip' to equip a weapon");
        System.out.println("Enter 'attack [anything]' to attack an enemy");
    }


    public void showLoot(ArrayList<Item> loot) {
        if (!loot.isEmpty()) {
            System.out.println("You discover: ");
            if (loot.size() == 1) {
                System.out.print(loot.get(0).getLongName());
            } else {
                for (Item item : loot) {
                    System.out.print(item.getLongName() + ", ");
                }
            }
        } else {
            System.out.print("You discover nothing.");
        }

    }

    public void showInventory(ArrayList<Item> inventory) {
        if (!inventory.isEmpty()) {
            System.out.println("You are carrying: ");
            int count = 1;
            if (inventory.size() == 1) {
                System.out.print(inventory.get(0).getLongName()+"\n");
            } else {
                for (Item item : inventory) {
                    if(count != inventory.size()) {
                        System.out.print(item.getLongName() + ", ");
                        count++;
                    } else {
                        System.out.print("and " + item.getLongName() + ".\n");
                    }
                }
            }
        } else {
            System.out.print("You carry nothing.");
        }

    }

}