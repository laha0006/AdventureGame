package ui;

import game.Enemy;
import item.Consumable;
import messages.*;
import game.Adventure;
import item.Item;
import ui.table.Row;
import ui.table.Table;


import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Adventure adventure;
    Scanner scanner;


    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.scanner = new Scanner(System.in);
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
        String input = scanner.nextLine();

        String[] splitInput = input.trim().toLowerCase().split(" ");

        if (splitInput.length == 1) {
            switch (splitInput[0]) {
                case "n", "north":
                    move(Direction.NORTH);
                    break;

                case "s", "south":
                    move(Direction.SOUTH);
                    break;

                case "e", "east":
                    move(Direction.EAST);
                    break;

                case "w", "west":
                    move(Direction.WEST);
                    break;

                case "up":
                    move(Direction.UP);
                    break;

                case "down":
                    move(Direction.DOWN);
                    break;

                case "look":
                    look();
                    System.out.println("\n" + adventure.getCurrentRoomDescription());
                    break;

                case "help":
                    help();
                    break;

                case "inv", "inventory":
                    inventory();
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

    private void look() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Item(s)");
        columns.add("Foe(s)");
        Table table = new Table(adventure.getCurrentRoomName(),columns,true );
        ArrayList<Item> loot = adventure.getCurrentRoomLoot();
        ArrayList<Enemy> foes = adventure.getCurrentRoomEnemies();
        int lootSize = loot.size();
        int foeSize = foes.size();
        int count = Math.max(lootSize,foeSize);
        for (int i = 0; i < count; i++) {
            String item = "";
            String foe = "";
            if ( i < lootSize) {
                item = loot.get(i).getLongName();
            }
            if ( i < foeSize) {
                foe = foes.get(i).getLongName();
            }
            table.addRow(new Row().addCell(item).addCell(foe));

        }
        System.out.println(table.getTableString());

    }
    private void inventory() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Weapons");
        columns.add("Consumables");
        columns.add("Items");
        Table table = new Table("Inventory",columns,true );
        ArrayList<Item> weapons = adventure.getPlayerWeapons();
        ArrayList<Item> consumables = adventure.getPlayerConsumables();
        ArrayList<Item> items = adventure.getPlayerItems();
        int weaponsSize = weapons.size();
        int consumablesSize = consumables.size();
        int itemsSize = items.size();
        int count = Math.max(weaponsSize,
                Math.max(consumablesSize,itemsSize));
        for (int i = 0; i < count; i++) {
            String weapon = "";
            String consumable = "";
            String item = "";
            if ( i < weaponsSize) {
                weapon = weapons.get(i).getLongName();
            }
            if ( i < consumablesSize) {
                consumable = consumables.get(i).getLongName();
            }
            if (i < itemsSize) {
                item = items.get(i).getLongName();
            }
            table.addRow(new Row().addCell(weapon).addCell(consumable).addCell(item));

        }
        System.out.println(table.getTableString());

    }

    private void move(Direction direction) {
        if (adventure.movePlayer(direction)) {
            String cardinalDirection = direction.toString().toLowerCase();
            System.out.println("You went " + cardinalDirection + " to " + adventure.getCurrentRoomName());
            System.out.println(adventure.getCurrentRoomDescription());
        } else {
            System.out.println("You can't go that way.");
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
        System.out.println("Enter 'equip {weapon}' to equip a weapon");
        System.out.println("Enter 'attack [anything]' to attack an enemy");
    }
}
